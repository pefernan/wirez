/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wirez.client.workbench.screens;

import com.google.gwt.user.client.ui.IsWidget;
import org.uberfire.client.annotations.*;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.events.ChangeTitleWidgetEvent;
import org.uberfire.client.workbench.widgets.common.ErrorPopupPresenter;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnOpen;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.Menus;
import org.wirez.client.widgets.palette.lienzo.LienzoDefinitionSetPaletteWidget;
import org.wirez.core.client.ShapeManager;
import org.wirez.core.client.ShapeSet;
import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.canvas.CanvasHandler;
import org.wirez.core.client.canvas.controls.event.BuildCanvasShapeEvent;
import org.wirez.core.client.components.palette.model.PaletteDefinitionBuilder;
import org.wirez.core.client.components.palette.model.definition.DefinitionSetPalette;
import org.wirez.core.client.components.palette.model.definition.DefinitionSetPaletteBuilder;
import org.wirez.core.client.service.ClientRuntimeError;
import org.wirez.core.client.session.CanvasSession;
import org.wirez.core.client.session.event.SessionDisposedEvent;
import org.wirez.core.client.session.event.SessionOpenedEvent;
import org.wirez.core.client.session.event.SessionPausedEvent;
import org.wirez.core.client.session.event.SessionResumedEvent;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import static org.uberfire.commons.validation.PortablePreconditions.checkNotNull;

@Dependent
@WorkbenchScreen(identifier = LienzoDefinitionSetPaletteScreen.SCREEN_ID )
public class LienzoDefinitionSetPaletteScreen {

    public static final String SCREEN_ID = "LienzoDefinitionSetPaletteScreen";

    @Inject
    LienzoDefinitionSetPaletteWidget paletteWiget;

    @Inject
    DefinitionSetPaletteBuilder definitionSetPaletteBuilder;

    @Inject
    ErrorPopupPresenter errorPopupPresenter;
    
    @Inject
    PlaceManager placeManager;
    
    @Inject
    ShapeManager shapeManager;

    @Inject
    Event<ChangeTitleWidgetEvent> changeTitleNotification;
    
    @Inject
    Event<BuildCanvasShapeEvent> buildCanvasShapeEvent;
    
    private PlaceRequest placeRequest;
    private String shapeSetId;
    private CanvasHandler canvasHandler;
    private Menus menu = null;

    @OnStartup
    public void onStartup(final PlaceRequest placeRequest) {
        this.placeRequest = placeRequest;
        // this.menu = makeMenuBar();
        this.shapeSetId = placeRequest.getParameter( "shapeSetId", "" );
        paletteWiget.setMaxWidth( 300 );

        open();
    }
    
    @OnOpen
    public void onOpen() {
        open();
    }

    @OnClose
    public void onClose() {
        close();
    }

    private void open() {

        if ( null != shapeSetId && shapeSetId.trim().length() > 0 ) {

            paletteWiget.getView().showEmptyView( false );

            final String definitionSetId = getShapeSet( shapeSetId ).getDefinitionSetId();

            definitionSetPaletteBuilder
                    .build( definitionSetId, new PaletteDefinitionBuilder.Callback<DefinitionSetPalette, ClientRuntimeError>() {

                @Override
                public void onSuccess(final DefinitionSetPalette palette) {

                    paletteWiget
                            .onItemDrop((definition, factory, x, y) ->
                                        buildCanvasShapeEvent.fire( new BuildCanvasShapeEvent((AbstractCanvasHandler) canvasHandler,
                                            definition, factory, x, y ) ))
                            .bind( palette );

                }

                @Override
                public void onError(final ClientRuntimeError error) {
                    showError( error.toString() );
                }

            });

        }

    }
    
    private void close() {
        paletteWiget.unbind();
        paletteWiget.getView().showEmptyView( true );
    }

    @WorkbenchMenu
    public Menus getMenu() {
        return menu;
    }

    private void showError(final String message) {
        errorPopupPresenter.showMessage(message);
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Definition Set Palette";
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        return paletteWiget.getView();
    }
    
    @WorkbenchContextId
    public String getMyContextRef() {
        return "lienzoDefinitionSetPaletteScreenContext";
    }

    void onCanvasSessionOpened(@Observes SessionOpenedEvent sessionOpenedEvent) {
        checkNotNull("sessionOpenedEvent", sessionOpenedEvent);
        doOpenSession( sessionOpenedEvent.getSession() );
        
    }

    void onCanvasSessionResumed(@Observes SessionResumedEvent sessionResumedEvent) {
        checkNotNull("sessionResumedEvent", sessionResumedEvent);
        doOpenSession( sessionResumedEvent.getSession() );
    }
    
    void onCanvasSessionDisposed(@Observes SessionDisposedEvent sessionDisposedEvent) {
        checkNotNull("sessionDisposedEvent", sessionDisposedEvent);
        doDisposeSession();
    }

    void onCanvasSessionPaused(@Observes SessionPausedEvent sessionPausedEvent) {
        checkNotNull("sessionPausedEvent", sessionPausedEvent);
        doDisposeSession();
    }

    private void doOpenSession(final CanvasSession canvasSession) {
        this.canvasHandler = canvasSession.getCanvasHandler();

        final String _shapeSetId = canvasHandler.getDiagram().getSettings().getShapeSetId();
        final ShapeSet shapeSet = getShapeSet(_shapeSetId);

        // changeTitleNotification.fire(new ChangeTitleWidgetEvent(placeRequest, shapeSet.getName() + " Palette"));
        this.shapeSetId = shapeSet.getId();
        open();

    }

    private void doDisposeSession() {
        // Close the current palette.
        // changeTitleNotification.fire(new ChangeTitleWidgetEvent(placeRequest, "Palette"));
        this.shapeSetId = null;
        close();
    }
    
    private ShapeSet getShapeSet(final String id) {
        for (final ShapeSet set : shapeManager.getShapeSets()) {
            if (set.getId().equals(id)) {
                return set;
            }
        }
        return null;
    }
}
