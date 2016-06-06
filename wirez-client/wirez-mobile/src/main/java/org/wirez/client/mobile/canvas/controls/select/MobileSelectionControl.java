package org.wirez.client.mobile.canvas.controls.select;

import com.google.gwt.core.client.GWT;
import org.wirez.client.mobile.api.platform.Mobile;
import org.wirez.core.client.animation.Deselect;
import org.wirez.core.client.animation.Select;
import org.wirez.core.client.animation.ShapeAnimation;
import org.wirez.core.client.animation.ShapeDeSelectionAnimation;
import org.wirez.core.client.canvas.controls.select.AbstractSelectionControl;
import org.wirez.core.client.canvas.event.ShapeStateModifiedEvent;
import org.wirez.core.client.shape.Shape;
import org.wirez.core.client.shape.view.HasEventHandlers;
import org.wirez.core.client.shape.view.ShapeView;
import org.wirez.core.client.shape.view.event.TouchEvent;
import org.wirez.core.client.shape.view.event.TouchHandler;
import org.wirez.core.client.shape.view.event.ViewEventType;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Dependent
@Mobile
public final class MobileSelectionControl extends AbstractSelectionControl {

    @Inject
    public MobileSelectionControl(final Event<ShapeStateModifiedEvent> canvasShapeStateModifiedEvent,
                                  final @Select  ShapeAnimation selectionAnimation,
                                  final @Deselect ShapeDeSelectionAnimation deSelectionAnimation) {
        super( canvasShapeStateModifiedEvent, selectionAnimation, deSelectionAnimation );
    }

    /*
        **************************************************************
        *               CANVAS CONTROL METHODS
        ***************************************************************
     */

    @Override
    public void register(final Shape shape) {

        // Selection handling.
        final ShapeView shapeView = shape.getShapeView();
        
        if ( shapeView instanceof HasEventHandlers ) {

            final HasEventHandlers hasEventHandlers = (HasEventHandlers) shapeView;

            // Touch event.
            final TouchHandler touchHandler = new TouchHandler() {
                @Override
                public void start( final TouchEvent event ) {

                    log( "TouchStart", event );

                    final boolean isSelected = isSelected(shape);

                    MobileSelectionControl.super.handleSelection( shape, isSelected, !event.isShiftKeyDown() );
                    
                }

                @Override
                public void move( final TouchEvent event ) {

                    log( "TouchMove", event );
                    
                }

                @Override
                public void end( final TouchEvent event ) {

                    log( "TouchEnd", event );
                    
                }

                @Override
                public void cancel( final TouchEvent event ) {

                    log( "TouchCancel", event );
                    
                }

                @Override
                public void handle( final TouchEvent event ) {

                    log( "TouchHandle", event );
                    
                }
            };
            
            hasEventHandlers.addHandler( ViewEventType.TOUCH, touchHandler );

            registerHandler( shape.getUUID(), touchHandler );
            
            
        }
        
    }
    
    private void log( final String prefix, final TouchEvent event ) {

        GWT.log( prefix + " -> Touch [" + event.getTouchX() + ", " + event.getTouchY() + "] " );
        
        
    }
    
}