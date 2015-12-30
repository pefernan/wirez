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

package org.wirez.basicset.client;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import com.ait.lienzo.client.core.types.LinearGradient;
import com.ait.lienzo.client.core.types.RadialGradient;
import org.wirez.basicset.api.property.bgset.*;
import org.wirez.core.api.definition.Definition;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Node;
import org.wirez.core.client.canvas.CanvasHandler;
import org.wirez.core.client.impl.BaseShape;
import org.wirez.core.client.mutation.MutationContext;

public abstract class BaseBasicShape<W extends Definition> extends BaseShape<W> {

    public BaseBasicShape(MultiPath path, Group group, WiresManager manager) {
        super(path, group, manager);
    }

    @Override
    public void applyElementProperties(Node<W, Edge> element, CanvasHandler wirezCanvas, MutationContext mutationContext) {
        super.applyElementProperties(element, wirezCanvas, mutationContext);

        // Fill color.
        _applyFillColor(element);

        // Fill gradient colors.
        _applyFillGradientColor(element);

        // Apply border styles.
        _applyBorders(element);

        // Apply font styles.
        _applyFont(element);
        
    }

    protected BaseBasicShape<W> _applyFillColor(Node<W, Edge> element) {
        final String color = (String) element.getProperties().get(BgColorBuilder.PROPERTY_ID);
        if (color != null && color.trim().length() > 0) {
            getShape().setFillColor(color);
        }
        return this;
    }

    protected BaseBasicShape<W> _applyFillGradientColor(Node<W, Edge> element) {
        _applyFillLinearGradientColor(element);
        return this;
    }

    protected BaseBasicShape<W> _applyFillLinearGradientColor(Node<W, Edge> element) {
        final String start = (String) element.getProperties().get(BgGradiendStartColorBuilder.PROPERTY_ID);
        final String end = (String) element.getProperties().get(BgGradiendEndColorBuilder.PROPERTY_ID);
        if ( start != null && start.trim().length() > 0 && end != null && end.trim().length() > 0 ) {
            final LinearGradient linearGradient = new LinearGradient(0, -50, 0, 50);
            linearGradient.addColorStop(1, end );
            linearGradient.addColorStop(0, start );
            getShape().setFillGradient(linearGradient);

        }
        return this;
    }

    protected BaseBasicShape<W> _applyFillRadialGradientColor(Node<W, Edge> element) {
        final String start = (String) element.getProperties().get(BgGradiendStartColorBuilder.PROPERTY_ID);
        final String end = (String) element.getProperties().get(BgGradiendEndColorBuilder.PROPERTY_ID);
        if ( start != null && start.trim().length() > 0 && end != null && end.trim().length() > 0 ) {
            final RadialGradient radialGradient = new RadialGradient(0, 0, 0, 0, 0, 40);
            radialGradient.addColorStop(1.0, end);
            radialGradient.addColorStop(0.0, start);
            getShape().setFillGradient(radialGradient);
        }
        return this;
    }

    protected BaseBasicShape<W> _applyBorders(Node<W, Edge> element) {
        final String color = (String) element.getProperties().get(BorderColorBuilder.PROPERTY_ID);
        final Integer width = (Integer) element.getProperties().get(BorderSizeBuilder.PROPERTY_ID);
        if (color != null && color.trim().length() > 0) {
            getShape().setStrokeColor(color);
        }
        if (width != null) {
            getShape().setStrokeWidth(width);
        }
        return this;
    }

    protected BaseBasicShape<W> _applyFont(Node<W, Edge> element) {
        // TODO
        /*final Text text = super.getText();
        if ( null != text ) {
            final String color = (String) element.getProperties().get(FontColorBuilder.PROPERTY_ID);
            final Integer size = (Integer) element.getProperties().get(FontSizeBuilder.PROPERTY_ID);
            final Integer borderSize = (Integer) element.getProperties().get(FontBorderSizeBuilder.PROPERTY_ID);
            if (color != null && color.trim().length() > 0) {
                text.setStrokeColor(color);
            }
            if (size != null && size > 0) {
                text.setFontSize(size);
            }
            if (borderSize != null && borderSize > 0) {
                text.setStrokeWidth(borderSize);
            }

        }*/

        return this;
    }
    
}
