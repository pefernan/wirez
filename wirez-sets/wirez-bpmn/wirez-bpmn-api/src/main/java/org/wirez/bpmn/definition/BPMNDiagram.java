/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 * Â 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * Â 
 * Â Â Â http://www.apache.org/licenses/LICENSE-2.0
 * Â 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wirez.bpmn.definition;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.NonPortable;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.workbench.common.forms.metaModel.FieldDef;
import org.wirez.bpmn.definition.property.Height;
import org.wirez.bpmn.definition.property.Width;
import org.wirez.bpmn.definition.property.background.BackgroundSet;
import org.wirez.bpmn.definition.property.diagram.DiagramSet;
import org.wirez.bpmn.definition.property.font.FontSet;
import org.wirez.bpmn.definition.property.general.BPMNGeneral;
import org.wirez.bpmn.definition.property.variables.ProcessData;
import org.wirez.bpmn.shape.proxy.BPMNDiagramShapeProxy;
import org.wirez.core.definition.annotation.Description;
import org.wirez.core.definition.annotation.Shape;
import org.wirez.core.definition.annotation.definition.*;
import org.wirez.core.definition.builder.Builder;
import org.wirez.core.factory.graph.NodeFactory;
import org.wirez.core.rule.annotation.CanContain;
import org.wirez.shapes.factory.BasicShapesFactory;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Portable
@Bindable
@Definition( graphFactory = NodeFactory.class, builder = BPMNDiagram.BPMNDiagramBuilder.class )
@CanContain( roles = { "all" } )
@Shape( factory = BasicShapesFactory.class, proxy = BPMNDiagramShapeProxy.class )
public class BPMNDiagram implements BPMNDefinition {

    @Category
    public static final transient String category = Categories.LANES;

    @Title
    public static final transient String title = "BPMN Diagram";

    @Description
    public static final transient String description = "BPMN Diagram";

    @PropertySet
    @FieldDef( label = "General Settings", position = 0)
    @Valid
    private BPMNGeneral general;

    @PropertySet
    @FieldDef( label = "Diagram Settings", position = 1)
    @Valid
    private DiagramSet diagramSet;

    @PropertySet
    @FieldDef( label = "Data", position = 2)
    @Valid
    protected ProcessData processData;

    @PropertySet
    @FieldDef( label = "Background Settings", position = 3)
    @Valid
    private BackgroundSet backgroundSet;

    @PropertySet
    private FontSet fontSet;

    @Property
    private Width width;

    @Property
    private Height height;

    @Labels
    private final Set<String> labels = new HashSet<String>() {{
        add( "canContainArtifacts" );
        add( "diagram" );
    }};

    @NonPortable
    public static class BPMNDiagramBuilder implements Builder<BPMNDiagram> {

        public static final transient String COLOR = "#FFFFFF";
        public static final transient String BORDER_COLOR = "#000000";
        public static final Double BORDER_SIZE = 1d;
        public static final Double WIDTH = 950d;
        public static final Double HEIGHT = 950d;

        @Override
        public BPMNDiagram build() {
            return new BPMNDiagram(  new BPMNGeneral( "Diagram" ),
                    new DiagramSet(),
                    new ProcessData(),
                    new BackgroundSet( COLOR, BORDER_COLOR, BORDER_SIZE ),
                    new FontSet(),
                    new Width( WIDTH ),
                    new Height( HEIGHT ) );
        }

    }

    public BPMNDiagram() {

    }

    public BPMNDiagram(@MapsTo("general") BPMNGeneral general,
            @MapsTo("diagramSet") DiagramSet diagramSet,
            @MapsTo("processData") ProcessData processData,
            @MapsTo("backgroundSet") BackgroundSet backgroundSet,
            @MapsTo("fontSet") FontSet fontSet,
            @MapsTo("width") Width width,
            @MapsTo("height") Height height) {
        this.general = general;
        this.diagramSet = diagramSet;
        this.processData = processData;
        this.backgroundSet = backgroundSet;
        this.fontSet = fontSet;
        this.width = width;
        this.height = height;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public DiagramSet getDiagramSet() {
        return diagramSet;
    }

    public BPMNGeneral getGeneral() {
        return general;
    }

    public Height getHeight() {
        return height;
    }

    public Width getWidth() {
        return width;
    }

    public ProcessData getProcessData() {
        return processData;
    }

    public BackgroundSet getBackgroundSet() {
        return backgroundSet;
    }

    public FontSet getFontSet() {
        return fontSet;
    }

    public void setGeneral( BPMNGeneral general ) {
        this.general = general;
    }

    public void setDiagramSet( DiagramSet diagramSet ) {
        this.diagramSet = diagramSet;
    }

    public void setProcessData( ProcessData processData) {
        this.processData = processData;
    }

    public void setBackgroundSet( BackgroundSet backgroundSet ) {
        this.backgroundSet = backgroundSet;
    }

    public void setFontSet( FontSet fontSet ) {
        this.fontSet = fontSet;
    }

    public void setWidth( Width width ) {
        this.width = width;
    }

    public void setHeight( Height height ) {
        this.height = height;
    }
}
