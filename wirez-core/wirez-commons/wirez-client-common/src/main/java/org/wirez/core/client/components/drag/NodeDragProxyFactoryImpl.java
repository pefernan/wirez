package org.wirez.core.client.components.drag;

import org.wirez.core.client.canvas.Layer;
import org.wirez.core.graph.Edge;
import org.wirez.core.graph.Node;
import org.wirez.core.graph.content.view.View;
import org.wirez.core.graph.content.view.ViewConnector;
import org.wirez.core.client.canvas.AbstractCanvas;
import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.shape.EdgeShape;
import org.wirez.core.client.shape.GraphShape;
import org.wirez.core.client.shape.MutationContext;
import org.wirez.core.client.shape.Shape;
import org.wirez.core.client.shape.factory.ShapeFactory;
import org.wirez.core.client.shape.util.EdgeMagnetsHelper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class NodeDragProxyFactoryImpl implements NodeDragProxyFactory<AbstractCanvasHandler> {
    
    private AbstractCanvasHandler canvasHandler;

    ShapeDragProxyFactory<AbstractCanvas> shapeDragProxyFactory;
    EdgeMagnetsHelper magnetsHelper;
    
    @Inject
    public NodeDragProxyFactoryImpl( final ShapeDragProxyFactory<AbstractCanvas> shapeDragProxyFactory,
                                     final EdgeMagnetsHelper magnetsHelper ) {
        this.shapeDragProxyFactory = shapeDragProxyFactory;
        this.magnetsHelper = magnetsHelper;
    }

    @Override
    public DragProxyFactory<AbstractCanvasHandler, Item, NodeDragProxyCallback> proxyFor(final AbstractCanvasHandler context) {
        this.canvasHandler = context;
        this.shapeDragProxyFactory.proxyFor( context.getCanvas() );
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DragProxyFactory<AbstractCanvasHandler, Item, NodeDragProxyCallback> newInstance(final Item item,
                                                                                        final int x, 
                                                                                        final int y, 
                                                                                        final NodeDragProxyCallback callback) {

        final AbstractCanvas canvas = canvasHandler.getCanvas();

        final Layer layer = canvas.getLayer();

        final Node<View<?>, Edge> node = item.getNode();

        final ShapeFactory<Object, AbstractCanvasHandler, ?> nodeShapeFactory = item.getNodeShapeFactory();

        final Edge<View<?>, Node> inEdge = item.getInEdge();

        final Node<View<?>, Edge> inEdgeSourceNode = item.getInEdgeSourceNode();
        
        final ShapeFactory<Object, AbstractCanvasHandler, ?> edgeShapeFactory = item.getInEdgeShapeFactory();

        final Shape nodeShape = nodeShapeFactory.build( node.getContent().getDefinition(), canvasHandler );
        
        if ( nodeShape instanceof GraphShape) {

            ( (GraphShape) nodeShape ).applyProperties( node, MutationContext.STATIC );
            
        }
            
        ;
        final EdgeShape edgeShape = 
                (EdgeShape) edgeShapeFactory.build( inEdge.getContent().getDefinition(), canvasHandler );
        canvas.addTransientShape( edgeShape );
        edgeShape.applyProperties( inEdge, MutationContext.STATIC );
        
        final Shape<?> edgeSourceNodeShape = canvasHandler.getCanvas().getShape( inEdgeSourceNode.getUUID() );

        shapeDragProxyFactory.newInstance( nodeShape, x, y, new DragProxyCallback() {
            
            @Override
            public void onStart(final int x, final int y) {

                callback.onStart( x , y );

                drawEdge();
                
            }

            @Override
            public void onMove(final int x,
                               final int y) {

                callback.onMove( x , y );
                
                drawEdge();

            }

            @Override
            public void onComplete(final int x,
                                   final int y) {

                final int[] magnets = getMagnets();

                callback.onComplete( x, y );

                callback.onComplete( x, y, magnets[0], magnets[1] );
                
                canvas.deleteTransientShape( edgeShape );

                canvas.draw();

            }
            
            private void drawEdge() {


                if ( inEdge.getContent() instanceof ViewConnector) {

                    final int[] magnets = getMagnets();

                    final ViewConnector viewConnector = (ViewConnector) inEdge.getContent();

                    viewConnector.setSourceMagnetIndex( magnets[0] );
                    viewConnector.setTargetMagnetIndex( magnets[1] );

                }

                edgeShape.applyConnections( inEdge, 
                        edgeSourceNodeShape.getShapeView(), 
                        nodeShape.getShapeView(), 
                        MutationContext.STATIC );
                
                canvas.draw();
                
            }

            private int[] getMagnets() {

                return magnetsHelper.getDefaultMagnetsIndex( edgeSourceNodeShape.getShapeView(),
                        nodeShape.getShapeView() );

            }
            
        });
        
        return this;
    }

    @Override
    public void destroy() {
        
        this.canvasHandler = null;
        this.shapeDragProxyFactory.destroy();
        this.shapeDragProxyFactory = null;
        this.magnetsHelper = null;
        
    }

}
