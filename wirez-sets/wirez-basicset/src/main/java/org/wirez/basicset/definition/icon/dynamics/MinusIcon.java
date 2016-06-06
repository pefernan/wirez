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

package org.wirez.basicset.definition.icon.dynamics;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.NonPortable;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.wirez.basicset.definition.Categories;
import org.wirez.basicset.definition.property.Height;
import org.wirez.basicset.definition.property.Name;
import org.wirez.basicset.definition.property.Width;
import org.wirez.basicset.definition.property.background.BackgroundAndBorderSet;
import org.wirez.basicset.shape.proxy.icon.dynamics.MinusIconProxy;
import org.wirez.core.definition.annotation.Description;
import org.wirez.core.definition.annotation.Shape;
import org.wirez.core.definition.annotation.definition.*;
import org.wirez.core.definition.factory.Builder;
import org.wirez.core.graph.Node;
import org.wirez.shapes.factory.BasicShapesFactory;

import java.util.HashSet;
import java.util.Set;

@Portable
@Bindable
@Definition( type = Node.class, builder = MinusIcon.MinusIconBuilder.class )
@Shape( factory = BasicShapesFactory.class, proxy = MinusIconProxy.class )
public class MinusIcon implements DynamicIcon {

    @Category
    public static final transient String category = Categories.ICONS;

    @Title
    public static final transient String title = "Minus Icon";

    @Description
    public static final transient String description = "Minus Icon";
    
    @Property
    private Name name;

    @PropertySet
    private BackgroundAndBorderSet backgroundSet;

    @Property
    private Width width;

    @Property
    private Height height;

    @Labels
    private final Set<String> labels = new HashSet<String>() {{
        add( "all" );
    }};

    @NonPortable
    public static class MinusIconBuilder implements Builder<MinusIcon> {

        public static final String COLOR = DynamicIcon.COLOR;
        public static final Double WIDTH = DynamicIcon.WIDTH;
        public static final Double HEIGHT = DynamicIcon.HEIGHT;
        public static final Double BORDER_SIZE = DynamicIcon.BORDER_SIZE;
        public static final String BORDER_COLOR = "#000000";

        @Override
        public MinusIcon build() {
            return new MinusIcon( new Name( "Minus" ),
                    new BackgroundAndBorderSet( COLOR, BORDER_COLOR, BORDER_SIZE ),
                    new Width( WIDTH ),
                    new Height( HEIGHT ) );
        }

    }

    public MinusIcon() {

    }

    public MinusIcon(@MapsTo("name") Name name,
                     @MapsTo("backgroundSet") BackgroundAndBorderSet backgroundSet,
                     @MapsTo("width") Width width,
                     @MapsTo("height") Height height) {
        this.name = name;
        this.backgroundSet = backgroundSet;
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

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public BackgroundAndBorderSet getBackgroundSet() {
        return backgroundSet;
    }

    public void setBackgroundSet(BackgroundAndBorderSet backgroundSet) {
        this.backgroundSet = backgroundSet;
    }

    public Width getWidth() {
        return width;
    }

    public void setWidth(Width width) {
        this.width = width;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

}