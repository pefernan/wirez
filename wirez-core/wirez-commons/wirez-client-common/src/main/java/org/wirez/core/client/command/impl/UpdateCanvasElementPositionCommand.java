package org.wirez.core.client.command.impl;

import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.command.AbstractCanvasGraphCommand;
import org.wirez.core.client.command.CanvasViolation;
import org.wirez.core.client.shape.MutationContext;
import org.wirez.core.command.Command;
import org.wirez.core.command.CommandResult;
import org.wirez.core.graph.Element;
import org.wirez.core.graph.command.GraphCommandExecutionContext;
import org.wirez.core.graph.command.impl.UpdateElementPositionCommand;
import org.wirez.core.rule.RuleViolation;

public final class UpdateCanvasElementPositionCommand extends AbstractCanvasGraphCommand {

    protected Element element;

    Double x;
    Double y;
    
    public UpdateCanvasElementPositionCommand(final Element element,
                                              final Double x,
                                              final Double y) {
        this.element = element;
        this.x = x;
        this.y = y;
    }

    @Override
    public CommandResult<CanvasViolation> doExecute(final AbstractCanvasHandler context) {
        context.updateElementPosition(element, MutationContext.STATIC);
        return buildResult();
    }

    @Override
    public CommandResult<CanvasViolation> doUndo(final AbstractCanvasHandler context) {
        return doExecute( context );
    }

    @Override
    protected Command<GraphCommandExecutionContext, RuleViolation> buildGraphCommand(final AbstractCanvasHandler context) {
        return new UpdateElementPositionCommand( element, x, y );
    }

}
