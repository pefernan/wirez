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
import org.wirez.bpmn.definition.property.simulation.CatchEventAttributes;
import org.wirez.core.definition.annotation.definition.Category;
import org.wirez.core.definition.annotation.definition.Labels;
import org.wirez.core.definition.annotation.definition.Property;
import org.wirez.core.definition.annotation.definition.PropertySet;
import org.wirez.core.definition.annotation.morph.MorphBase;
import org.wirez.core.definition.builder.Builder;

import java.util.HashSet;
import java.util.Set;

@MorphBase( defaultType = StartNoneEvent.class, targets = { BaseEndEvent.class } )
public abstract class BaseStartEvent implements BPMNDefinition {

    @Category
    public static final transient String category = Categories.EVENTS;

    @PropertySet
    @FieldDef( label = "BPMN General Settings")
    protected BPMNGeneral general;

    @PropertySet
    @FieldDef( label = "Background Settings")
    protected BackgroundSet backgroundSet;

    @PropertySet
    protected FontSet fontSet;

    @PropertySet
    protected CatchEventAttributes catchEventAttributes;

    @Property
    @FieldDef(label = "Radius", property = "value")
    protected Radius radius;

    @Labels
    protected final Set<String> labels = new HashSet<String>() {{
        add( "all" );
        add( "Startevents_all" );
        add( "sequence_start" );
        add( "choreography_sequence_start" );
        add( "to_task_event" );
        add( "from_task_event" );
        add( "fromtoall" );
        add( "StartEventsMorph" );
    }};

    @NonPortable
    static abstract class BaseStartEventBuilder<T extends BaseStartEvent> implements Builder<T> {

        public static final transient String COLOR = "#cae294";
        public static final Double BORDER_SIZE = 1d;
        public static final String BORDER_COLOR = "#000000";
        public static final Double RADIUS = 15d;

    }

    public BaseStartEvent() {

    }

    public BaseStartEvent(@MapsTo("general") BPMNGeneral general,
                          @MapsTo("backgroundSet") BackgroundSet backgroundSet,
                          @MapsTo("fontSet") FontSet fontSet,
                          @MapsTo("catchEventAttributes") CatchEventAttributes catchEventAttributes,
                          @MapsTo("radius") Radius radius) {
        this.general = general;
        this.backgroundSet = backgroundSet;
        this.fontSet = fontSet;
        this.catchEventAttributes = catchEventAttributes;
        this.radius = radius;
    }

    public String getCategory() {
        return category;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public Radius getRadius() {
        return radius;
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

    public CatchEventAttributes getCatchEventAttributes() {
        return catchEventAttributes;
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

    public void setCatchEventAttributes( CatchEventAttributes catchEventAttributes ) {
        this.catchEventAttributes = catchEventAttributes;
    }

    public void setRadius( Radius radius ) {
        this.radius = radius;
    }
}
