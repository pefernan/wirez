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

package org.wirez.client.widgets.wizard;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.Popover;
import org.gwtbootstrap3.client.ui.constants.Placement;
import org.uberfire.mvp.Command;

public class CanvasWizardView extends Composite implements CanvasWizard.View {


    interface ViewBinder extends UiBinder<Widget, CanvasWizardView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField
    Heading heading;
    
    @UiField
    FlowPanel emptyViewPanel;
    
    @UiField
    HorizontalPanel itemsPanel;
    
    @UiField
    Button actionButton;
    
    private CanvasWizard presenter;
    
    @Override
    public void init(final CanvasWizard presenter) {
        this.presenter = presenter;
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    @Override
    public CanvasWizard.View setHeading(final String title) {
        heading.setText(title);
        return this;
    }

    @Override
    public CanvasWizard.View addItem(final String name, 
                                     final String description, 
                                     final SafeUri thumbnailUri,
                                     final boolean isSelected,
                                     final Command clickHandler) {

        final VerticalPanel panel = new VerticalPanel();
        panel.setVerticalAlignment(HasAlignment.ALIGN_MIDDLE);
        panel.getElement().getStyle().setMargin(50, Style.Unit.PX);
        panel.setHeight("100%");

        final org.gwtbootstrap3.client.ui.Image image = new org.gwtbootstrap3.client.ui.Image(thumbnailUri);
        image.getElement().getStyle().setCursor(Style.Cursor.POINTER);
        final double alpha = isSelected ? 1 : 0.2;
        image.getElement().setAttribute("style", "filter: alpha(opacity=5);opacity: " + alpha);
        image.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                clickHandler.execute();
            }
        });

        final Popover popover = new Popover();
        popover.setTitle( name );
        popover.setContent( description );
        popover.setWidget(image);
        popover.setContainer("body");
        popover.setPlacement(Placement.BOTTOM);
        popover.setShowDelayMs(1000);

        final HTML label = new HTML(name);
        final HorizontalPanel labelPanel = new HorizontalPanel();
        labelPanel.setWidth("100%");
        labelPanel.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
        labelPanel.add(label);

        panel.add(popover);
        panel.add(labelPanel);
        itemsPanel.add(panel);
        
        return this;
    }

    @Override
    public CanvasWizard.View showEmptyView() {
        emptyViewPanel.setVisible(true);
        return this;
    }

    @Override
    public CanvasWizard.View setActionButtonText(final String caption) {
        actionButton.setText(caption);
        actionButton.setTitle(caption);
        return this;
    }

    @Override
    public CanvasWizard.View setActionButtonEnabled(final boolean isEnabled) {
        actionButton.setEnabled(isEnabled);
        return this;
    }

    @Override
    public CanvasWizard.View clear() {
        emptyViewPanel.setVisible(false);
        itemsPanel.clear();
        return this;
    }

    @UiHandler("actionButton")
    public void onActionbuttonClick(ClickEvent event) {
        presenter.onActionButtonClick();
    }
}
