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
import org.wirez.client.widgets.explorer.tree.TreeExplorer;
import org.wirez.client.widgets.property.PropertiesEditor;
import org.wirez.client.workbench.event.CanvasScreenStateChangedEvent;
import org.wirez.core.api.definition.property.defaults.Name;
import org.wirez.core.api.graph.Element;
import org.wirez.core.api.graph.content.ViewContent;
import org.wirez.core.api.util.ElementUtils;
import org.wirez.core.client.ShapeSet;
import org.wirez.core.client.canvas.impl.WiresCanvasHandler;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import static org.uberfire.commons.validation.PortablePreconditions.checkNotNull;

@Dependent
@WorkbenchScreen(identifier = TreeExplorerScreen.SCREEN_ID )
public class TreeExplorerScreen {

    public static final String SCREEN_ID = "TreeExplorerScreen";

    @Inject
    TreeExplorer treeExplorer;
    
    @Inject
    ErrorPopupPresenter errorPopupPresenter;
    
    private PlaceRequest placeRequest;
    
    @PostConstruct
    public void init() {
        
    }

    @OnStartup
    public void onStartup(final PlaceRequest placeRequest) {
        this.placeRequest = placeRequest;
    }
    
    @OnOpen
    public void onOpen() {

    }

    @OnClose
    public void onClose() {
        treeExplorer.clear();
    }


    @WorkbenchMenu
    public Menus getMenu() {
        return null;
    }

    private void showError(final String message) {
        errorPopupPresenter.showMessage(message);
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Explorer";
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        return treeExplorer.asWidget();
    }
    
    @WorkbenchContextId
    public String getMyContextRef() {
        return "TreeExplorerScreenContext";
    }

    void onCanvasScreenStateChanged(@Observes CanvasScreenStateChangedEvent canvasScreenStateChangedEvent) {
        checkNotNull("canvasScreenStateChangedEvent", canvasScreenStateChangedEvent);
        final CanvasScreen.CanvasScreenState state = canvasScreenStateChangedEvent.getState();

        if (CanvasScreen.CanvasScreenState.ACTIVE.equals(state)) {
            treeExplorer.show(canvasScreenStateChangedEvent.getCanvasHandler());
        } else {
           treeExplorer.clear();
        }

    }
    
}