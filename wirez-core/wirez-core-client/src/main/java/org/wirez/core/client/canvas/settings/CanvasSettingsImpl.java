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

package org.wirez.core.client.canvas.settings;

import org.wirez.core.api.definition.DefinitionSet;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Graph;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.processing.handler.GraphHandler;
import org.wirez.core.api.graph.processing.visitor.GraphVisitor;
import org.wirez.core.client.ShapeSet;
import org.wirez.core.client.canvas.settings.CanvasSettings;

public abstract class CanvasSettingsImpl<G extends Graph, N extends Node, E extends Edge> implements CanvasSettings<G, N, E> {

    private GraphHandler<G, N, E> handler;
    private GraphVisitor<G, N, E> visitor;

    public CanvasSettingsImpl() {
    }

    public CanvasSettingsImpl(final GraphHandler<G, N, E> handler,
                              final GraphVisitor<G, N, E> visitor) {
        this.handler = handler;
        this.visitor = visitor;
    }

    @Override
    public GraphHandler<G, N, E> getGraphHandler() {
        return handler;
    }

    @Override
    public GraphVisitor<G, N, E> getGraphVisitor() {
        return visitor;
    }

    public void setHandler(GraphHandler<G, N, E> handler) {
        this.handler = handler;
    }

    public void setVisitor(GraphVisitor<G, N, E> visitor) {
        this.visitor = visitor;
    }
}