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

import org.wirez.core.api.command.CommandManager;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Graph;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.processing.handler.GraphHandler;
import org.wirez.core.api.graph.processing.visitor.GraphVisitor;
import org.wirez.core.api.rule.RuleManager;
import org.wirez.core.client.canvas.CanvasHandler;
import org.wirez.core.client.canvas.command.CanvasCommandViolation;
import org.wirez.core.client.canvas.control.ConnectionAcceptor;
import org.wirez.core.client.canvas.control.ContainmentAcceptor;
import org.wirez.core.client.canvas.impl.WiresCanvasHandler;

public class WiresCanvasSettingsBuilderImpl implements WiresCanvasSettingsBuilder {

    private WiresCanvasSettingsImpl settings;
    
    public WiresCanvasSettingsBuilderImpl() {
        settings = new WiresCanvasSettingsImpl();
    }


    @Override
    public WiresCanvasSettingsBuilder graphHandler(final GraphHandler<?, ?, ?> handler) {
        settings.setHandler((GraphHandler<Graph, Node, Edge>) handler);
        return this;
    }

    @Override
    public WiresCanvasSettingsBuilder graphVisitor(final GraphVisitor<?, ?, ?> visitor) {
        settings.setVisitor((GraphVisitor<Graph, Node, Edge>) visitor);
        return this;
    }

    @Override
    public WiresCanvasSettingsBuilder commandManager(final CommandManager<WiresCanvasHandler, CanvasCommandViolation> commandManager) {
        settings.setCommandManager(commandManager);
        return this;
    }

    @Override
    public WiresCanvasSettingsBuilder ruleManager(final RuleManager ruleManager) {
        settings.setRuleManager(ruleManager);
        return this;
    }

    @Override
    public WiresCanvasSettingsBuilder connectionAcceptor(final ConnectionAcceptor connectionAcceptor) {
        settings.setConnectionAcceptor(connectionAcceptor);
        return this;
    }

    @Override
    public WiresCanvasSettingsBuilder containmentAcceptor(final ContainmentAcceptor connectionAcceptor) {
        settings.setContainmentAcceptor(connectionAcceptor);
        return this;
    }

    @Override
    public WiresCanvasSettings build() {
        return settings;
    }
}
