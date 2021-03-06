package org.wirez.client.widgets.session.presenter.impl;

import com.google.gwt.user.client.ui.Widget;
import org.uberfire.client.workbench.widgets.common.ErrorPopupPresenter;
import org.wirez.client.widgets.session.presenter.CanvasSessionPresenter;
import org.wirez.core.client.canvas.AbstractCanvas;
import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.canvas.controls.AbstractCanvasHandlerRegistrationControl;
import org.wirez.core.client.canvas.controls.CanvasControl;
import org.wirez.core.client.canvas.controls.CanvasRegistationControl;
import org.wirez.core.client.service.ClientRuntimeError;
import org.wirez.core.client.session.CanvasSession;
import org.wirez.core.client.session.event.SessionDisposedEvent;
import org.wirez.core.client.session.event.SessionPausedEvent;
import org.wirez.core.client.shape.Shape;
import org.wirez.core.graph.Element;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import static org.uberfire.commons.validation.PortablePreconditions.checkNotNull;

public abstract class AbstractCanvasSessionPresenter<S extends CanvasSession<AbstractCanvas, AbstractCanvasHandler>>
        implements CanvasSessionPresenter<AbstractCanvas, AbstractCanvasHandler, S> {

    ErrorPopupPresenter errorPopupPresenter;
    View view;
    protected S session;

    @Inject
    public AbstractCanvasSessionPresenter(final ErrorPopupPresenter errorPopupPresenter,
                                          final View view) {
        this.errorPopupPresenter = errorPopupPresenter;
        this.view = view;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public void initialize(final S session, 
                           final int width, 
                           final int height) {
        this.session = session;

        // Create the canvas with a given size.
        session.getCanvas().initialize( width, height );

        // Initialize the canvas to handle.
        getCanvasHandler().initialize( session.getCanvas() );

        // Initialize the view.
        initializeView();
        
        doInitialize( session, width, height );

        afterInitialize( session, width, height );


    }

    protected void enableControl(final CanvasControl<AbstractCanvasHandler> control, final AbstractCanvasHandler handler) {

        if ( null!= control ) {

            control.enable( handler );
        }

    }

    protected void enableControl(final CanvasControl<AbstractCanvas> control, final AbstractCanvas handler) {

        if ( null!= control ) {

            control.enable( handler );
        }

    }

    protected void fireRegistrationListeners( final CanvasControl<AbstractCanvasHandler> control,
                                              final Element element,
                                              final boolean add ) {

        if ( null!= control && null != element && control instanceof CanvasRegistationControl ) {

            final CanvasRegistationControl<AbstractCanvasHandler, Element> registationControl =
                    ( CanvasRegistationControl<AbstractCanvasHandler, Element> ) control;

            if ( add ) {

                registationControl.register( element );

            } else {

                registationControl.deregister( element );

            }

        }

    }

    protected void fireRegistrationListeners( final CanvasControl<AbstractCanvas> control,
                                              final Shape shape,
                                              final boolean add ) {

        if ( null!= control && null != shape && control instanceof CanvasRegistationControl ) {

            final CanvasRegistationControl<AbstractCanvas, Shape> registationControl =
                    ( CanvasRegistationControl<AbstractCanvas, Shape> ) control;

            if ( add ) {

                registationControl.register( shape );

            } else {

                registationControl.deregister( shape );

            }

        }

    }

    protected void fireRegistrationUpdateListeners( final CanvasControl<AbstractCanvasHandler> control,
                                              final Element element ) {

        if ( null!= control && null != element && control instanceof AbstractCanvasHandlerRegistrationControl ) {

            final AbstractCanvasHandlerRegistrationControl  registationControl =
                    ( AbstractCanvasHandlerRegistrationControl ) control;

            registationControl.update( element );

        }

    }

    protected void fireRegistrationClearListeners( final CanvasControl<AbstractCanvasHandler> control ) {

        if ( null!= control && control instanceof AbstractCanvasHandlerRegistrationControl ) {

            final AbstractCanvasHandlerRegistrationControl  registationControl =
                    ( AbstractCanvasHandlerRegistrationControl ) control;

            registationControl.deregisterAll();

        }

    }


    protected void doInitialize(final S session,
                           final int width,
                           final int height) {
        
    }

    protected void afterInitialize(final S session,
                                   final int width,
                                   final int height) {

    }
    
    protected void initializeView() {

        view.setCanvas( session.getCanvas().getView().asWidget() );
        
    }

    @Override
    public AbstractCanvasHandler getCanvasHandler() {
        return session.getCanvasHandler();
    }

    @Override
    public Widget asWidget() {
        return view.asWidget();
    }

    protected void onCanvasSessionDisposed(@Observes SessionDisposedEvent sessionDisposedEvent) {
        checkNotNull("sessionDisposedEvent", sessionDisposedEvent);
        if ( null !=  session && session.equals( sessionDisposedEvent.getSession() ) ) {
            onDisposeSession();
        }
    }

    protected void onCanvasSessionPaused(@Observes SessionPausedEvent sessionPausedEvent) {
        checkNotNull("sessionPausedEvent", sessionPausedEvent);
        if ( null !=  session && session.equals( sessionPausedEvent.getSession() ) ) {
            onPauseSession();
        }
    }

    private void onDisposeSession() {
        
        // Implementations can clear its state here.
        disposeSession();
        
        // Destroy the view.
        this.view.destroy();
        
        
        // Nullify
        this.view = null;
        this.session = null;
        
    }

    private void onPauseSession() {
        pauseSession();
    }

    protected abstract void disposeSession();

    protected abstract void pauseSession();
    
    protected void showError(final ClientRuntimeError error) {
        final String message = error.getCause() != null ? error.getCause() : error.getMessage();
        showError(message);
    }

    protected void showError(final Throwable throwable) {
        errorPopupPresenter.showMessage( throwable != null ? throwable.getMessage() : "Error");
    }

    protected void showError(final String message) {
        errorPopupPresenter.showMessage(message);
    }

}
