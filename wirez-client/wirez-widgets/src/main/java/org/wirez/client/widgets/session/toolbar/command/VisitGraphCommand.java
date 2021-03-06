package org.wirez.client.widgets.session.toolbar.command;

import org.gwtbootstrap3.client.ui.constants.IconType;
import org.wirez.client.widgets.session.toolbar.ToolbarCommandCallback;
import org.wirez.core.client.session.impl.DefaultCanvasReadOnlySession;
import org.wirez.core.client.util.CanvasHighlightVisitor;
import org.wirez.core.client.util.ShapeStateUtils;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class VisitGraphCommand extends AbstractToolbarCommand<DefaultCanvasReadOnlySession> {

    ShapeStateUtils shapeStateUtils;
    
    @Inject
    public VisitGraphCommand( final ShapeStateUtils shapeStateUtils ) {
        this.shapeStateUtils = shapeStateUtils;
    }

    @Override
    protected boolean getState() {
        // Always active.
        return true;
    }

    @Override
    public IconType getIcon() {
        return IconType.AUTOMOBILE;
    }

    @Override
    public String getCaption() {
        return null;
    }

    @Override
    public String getTooltip() {
        return "Visit";
    }

    @Override
    public <T> void execute(final ToolbarCommandCallback<T> callback) {

        new CanvasHighlightVisitor()
                .run(session.getCanvasHandler(), shapeStateUtils, () -> {
                    if (null != callback) {
                        callback.onCommandExecuted(null);
                    }
                });

    }

}
