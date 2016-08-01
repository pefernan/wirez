/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.wirez.bpmn.definition;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.NonPortable;
import org.kie.workbench.common.forms.metaModel.FieldDef;
import org.wirez.bpmn.definition.property.Radius;
import org.wirez.bpmn.definition.property.background.BackgroundSet;
import org.wirez.bpmn.definition.property.font.FontSet;
import org.wirez.bpmn.definition.property.general.BPMNGeneral;
import org.wirez.core.definition.annotation.definition.Category;
import org.wirez.core.definition.annotation.definition.Labels;
import org.wirez.core.definition.annotation.definition.Property;
import org.wirez.core.definition.annotation.definition.PropertySet;
import org.wirez.core.definition.annotation.morph.MorphBase;
import org.wirez.core.definition.builder.Builder;

import java.util.HashSet;
import java.util.Set;

@MorphBase( defaultType = ParallelGateway.class )
public abstract class BaseGateway implements BPMNDefinition {

    @Category
    public static final transient String category = Categories.GATEWAYS;

    @PropertySet
    protected BPMNGeneral general;

    @PropertySet
    protected BackgroundSet backgroundSet;

    @PropertySet
    protected FontSet fontSet;

    @Property
    @FieldDef(label = "Radius", property = "value")
    protected Radius radius;

    @Labels
    protected final Set<String> labels = new HashSet<String>() {{
        add( "all" );
        add( "sequence_start" );
        add( "sequence_end" );
        add( "choreography_sequence_start" );
        add( "choreography_sequence_end" );
        add( "fromtoall" );
        add( "GatewaysMorph" );
    }};

    @NonPortable
    static abstract class BaseGatewayBuilder<T extends BaseGateway> implements Builder<T> {

        public static final transient String COLOR = "#f2ea9e";
        public static final transient String ICON_COLOR = "#ae8104";
        public static final transient String BORDER_COLOR = "#000000";
        public static final Double BORDER_SIZE = 1d;
        public static final Double RADIUS = 20d;


    }

    public BaseGateway() {

    }

    public BaseGateway(@MapsTo("general") BPMNGeneral general,
                       @MapsTo("backgroundSet") BackgroundSet backgroundSet,
                       @MapsTo("fontSet") FontSet fontSet,
                       @MapsTo("radius") Radius radius) {
        this.general = general;
        this.backgroundSet = backgroundSet;
        this.fontSet = fontSet;
        this.radius = radius;
    }

    public String getCategory() {
        return category;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public BPMNGeneral getGeneral() {
        return general;
    }

    public BackgroundSet getBackgroundSet() {
        return backgroundSet;
    }

    public FontSet getFontSet() {
        return fontSet;
    }

    public Radius getRadius() {
        return radius;
    }

    public void setGeneral( BPMNGeneral general ) {
        this.general = general;
    }

    public void setBackgroundSet( BackgroundSet backgroundSet ) {
        this.backgroundSet = backgroundSet;
    }

    public void setFontSet( FontSet fontSet ) {
        this.fontSet = fontSet;
    }

    public void setRadius( Radius radius ) {
        this.radius = radius;
    }
}
