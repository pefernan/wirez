package org.wirez.core.client.canvas.command.impl;

import org.wirez.core.api.command.Command;
import org.wirez.core.api.command.CommandResult;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Graph;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.command.GraphCommandExecutionContext;
import org.wirez.core.api.graph.command.impl.AddEdgeCommand;
import org.wirez.core.api.graph.content.view.View;
import org.wirez.core.api.graph.processing.index.IncrementalIndexBuilder;
import org.wirez.core.api.graph.processing.index.Index;
import org.wirez.core.api.graph.processing.index.IndexBuilder;
import org.wirez.core.api.rule.RuleViolation;
import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.canvas.command.CanvasViolation;
import org.wirez.core.client.shape.MutationContext;
import org.wirez.core.client.shape.factory.ShapeFactory;
import org.wirez.core.client.shape.impl.AbstractConnector;

public final class AddCanvasEdgeCommand extends AddCanvasElementCommand<Edge>  {
    
    Node parent;
    
    public AddCanvasEdgeCommand(final Node parent, final Edge candidate, final ShapeFactory factory) {
        super(candidate, factory);
        this.parent = parent;
    }

    @Override
    protected void doRegister(final AbstractCanvasHandler context) {
        super.doRegister(context);
        final IndexBuilder<Graph<?, Node>, Node, Edge, Index<Node, Edge>> indexBuilder = context.getIndexBuilder();
        if ( indexBuilder instanceof IncrementalIndexBuilder) {
            ((IncrementalIndexBuilder) indexBuilder).addEdge(context.getGraphIndex(), candidate);
        }
    }

    @Override
    public CommandResult<CanvasViolation> execute(final AbstractCanvasHandler context) {
        CommandResult<CanvasViolation> result = super.execute(context);
        final String uuid = candidate.getUUID();
        AbstractConnector connector = (AbstractConnector) context.getCanvas().getShape(uuid);
        connector.applyConnections((Edge<View, Node>) candidate, context, MutationContext.STATIC);
        return result;
    }

    @Override
    public CommandResult<CanvasViolation> undo(final AbstractCanvasHandler context) {
        final DeleteCanvasEdgeCommand command = new DeleteCanvasEdgeCommand( candidate );
        return command.execute( context );
    }


    @Override
    protected Command<GraphCommandExecutionContext, RuleViolation> buildGraphCommand(final AbstractCanvasHandler context) {
        return new AddEdgeCommand( parent, candidate );
    }
    
}
