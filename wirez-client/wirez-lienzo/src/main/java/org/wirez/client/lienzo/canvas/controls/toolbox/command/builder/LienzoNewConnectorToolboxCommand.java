package org.wirez.client.lienzo.canvas.controls.toolbox.command.builder;

import com.ait.lienzo.client.core.shape.Shape;
import org.wirez.core.client.ClientDefinitionManager;
import org.wirez.core.client.ShapeManager;
import org.wirez.core.client.animation.Deselect;
import org.wirez.core.client.animation.Select;
import org.wirez.core.client.animation.ShapeAnimation;
import org.wirez.core.client.animation.ShapeDeSelectionAnimation;
import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.canvas.controls.builder.EdgeBuilderControl;
import org.wirez.core.client.canvas.controls.toolbox.command.builder.NewConnectorCommand;
import org.wirez.core.client.components.drag.ConnectorDragProxyFactory;
import org.wirez.core.client.components.glyph.DefinitionGlyphTooltip;
import org.wirez.core.client.service.ClientFactoryServices;
import org.wirez.core.graph.processing.index.bounds.GraphBoundsIndexer;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class LienzoNewConnectorToolboxCommand extends NewConnectorCommand<Shape<?>> {

    protected LienzoNewConnectorToolboxCommand() {
        this( null, null, null, null, null, null, null, null, null );
    }

    @Inject
    public LienzoNewConnectorToolboxCommand(final ClientDefinitionManager clientDefinitionManager,
                                            final ClientFactoryServices clientFactoryServices,
                                            final ShapeManager shapeManager,
                                            final DefinitionGlyphTooltip<?> glyphTooltip,
                                            final GraphBoundsIndexer graphBoundsIndexer,
                                            final ConnectorDragProxyFactory<AbstractCanvasHandler> connectorDragProxyFactory,
                                            final EdgeBuilderControl<AbstractCanvasHandler> edgeBuilderControl,
                                            final @Select ShapeAnimation selectionAnimation,
                                            final @Deselect ShapeDeSelectionAnimation deSelectionAnimation ) {
        super( clientDefinitionManager, clientFactoryServices, shapeManager, glyphTooltip,
                graphBoundsIndexer, connectorDragProxyFactory, edgeBuilderControl,
                selectionAnimation, deSelectionAnimation );
    }
    
}