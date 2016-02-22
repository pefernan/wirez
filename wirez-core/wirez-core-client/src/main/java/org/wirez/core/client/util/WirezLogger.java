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

package org.wirez.core.client.util;

import com.google.gwt.logging.client.LogConfiguration;
import org.wirez.core.api.command.CommandResult;
import org.wirez.core.api.graph.*;
import org.wirez.core.api.graph.content.Child;
import org.wirez.core.api.graph.content.view.View;
import org.wirez.core.api.graph.processing.traverse.content.AbstractFullContentTraverseCallback;
import org.wirez.core.api.graph.processing.traverse.content.FullContentTraverseCallback;
import org.wirez.core.api.graph.processing.traverse.content.FullContentTraverseProcessorImpl;
import org.wirez.core.api.graph.processing.traverse.tree.TreeWalkTraverseProcessorImpl;
import org.wirez.core.api.rule.RuleViolation;
import org.wirez.core.client.service.ClientRuntimeError;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Just for development use.
 */
public class WirezLogger {

    private static Logger LOGGER = Logger.getLogger("org.wirez.core.client.util.WirezLogger");

    public static String getErrorMessage(final ClientRuntimeError error) {
        final String message = error.getMessage();
        final Throwable t1 = error.getThrowable();
        final Throwable t2 = t1 != null ? t1.getCause() : null;

        if ( null != t2 ) {
            return t2.getMessage();
        } else if ( null != t1 ) {
            return t1.getMessage();
        } 
        
        return message;
    }
    
    public static void logCommandResults(final Iterable<CommandResult> results) {
        if (results == null) {
            log("Results is null");
        } else {
            for (CommandResult result : results) {
                logCommandResult(result);
            }
        }
    }

    public static void logCommandResult(final CommandResult result) {
        String message = result.getMessage();
        CommandResult.Type type = result.getType();
        log("Command Result [message=" + message + "] [type" + type.name());
        Iterable<RuleViolation> violations = result.getViolations();
        logRuleViolations(violations);
    }

    public static void logRuleViolations(final Iterable<RuleViolation> violations) {
        if (violations == null) {
            log("Violations is null");
        } else {
            for (RuleViolation result : violations) {
                logRuleViolation(result);
            }
        }
    }

    public static void logRuleViolation(final RuleViolation violation) {
        String message = violation.getMessage();
        RuleViolation.Type type = violation.getViolationType();
        log("Rule Violation [message=" + message + "] [type" + type.name());
    }
    
    private static final FullContentTraverseCallback<Node<View, Edge>, Edge<Object, Node>> TREE_TRAVERSE_CALLBACK = 
            new AbstractFullContentTraverseCallback<Node<View, Edge>, Edge<Object, Node>>() {
                
                private String indent = "";
                
                @Override
                public void startViewEdgeTraversal(final Edge<Object, Node> edge) {
                    log(indent + "(View) Edge UUID: " + edge.getUUID());
                    final View viewContent = (View) edge.getContent();
                    log(indent + "(View) Edge Id: " + viewContent.getDefinition().getId());

                    final Node outNode = (Node) edge.getTargetNode();
                    if (outNode == null) {
                        log(indent + "  No outgoing node found");
                    } else {
                        log(indent + "  Outgoing Node");
                        log(indent + "  ==============");
                    }
                }

                @Override
                public void startChildEdgeTraversal(final Edge<Object, Node> edge) {
                    log("(Child= Edge UUID: " + edge.getUUID());

                    final Node outNode = edge.getTargetNode();
                    if (outNode == null) {
                        log(indent + "  No outgoing node found");
                    } else {
                        log(indent + "  Outgoing Node");
                        log(indent + "  ==============");
                    }
                }

                @Override
                public void startParentEdgeTraversal(final Edge<Object, Node> edge) {

                }

                @Override
                public void startEdgeTraversal(final Edge<Object, Node> edge) {
                    log(indent + "Edge UUID: " + edge.getUUID());

                    final Node outNode = (Node) edge.getTargetNode();
                    if (outNode == null) {
                        log(indent + "  No outgoing node found");
                    } else {
                        log(indent + "  Outgoing Node");
                        log(indent + "  ==============");
                    }
                }

                @Override
                public void startGraphTraversal(final Graph<View, Node<View, Edge>> graph) {
                    if (graph == null) {
                        error("Graph is null!");
                    } else {
                        log(indent + "Graph UUID: " + graph.getUUID());
                        log(indent + "  Graph Starting nodes");
                        log(indent + "  ====================");
                    }
                }

                @Override
                public void startNodeTraversal(final Node<View, Edge> node) {

                    
                    log(indent + "(View) Node UUID: " + node.getUUID());
                    log(indent + "(View) Node Id: " + node.getContent().getDefinition().getId());

                    final Node parent = getParent(node);
                    if ( null != parent ) {
                        log(indent + "(View) Node Parent is: " + parent.getUUID());
                    }
                    
                    List<Edge> outEdges = (List<Edge>) node.getOutEdges();
                    if (outEdges == null || outEdges.isEmpty()) {
                        log(indent + "  No outgoing edges found");
                    } else {
                        log(indent + "  Outgoing edges");
                        log(indent + "  ==============");
                    }
                }

                @Override
                public void endGraphTraversal() {

                }
                
            };
    
    private static Node getParent(final Node node) {
        List<Edge> inEdges = node.getInEdges();
        if ( null != inEdges && !inEdges.isEmpty() ) {
            for ( final Edge edge : inEdges) {
                if (edge.getContent() instanceof Child) {
                    return edge.getSourceNode();
                }
            }
        }
        return null;
    }
    
    public static void log(final Graph graph) {
        new FullContentTraverseProcessorImpl(new TreeWalkTraverseProcessorImpl())
                .traverse(graph, TREE_TRAVERSE_CALLBACK);
    }

    public static void resume(final Graph graph) {
        new FullContentTraverseProcessorImpl(new TreeWalkTraverseProcessorImpl())
                .traverse(graph, TREE_TRAVERSE_CALLBACK);
    }

    private static void log(final String message) {
        if ( LogConfiguration.loggingIsEnabled() ) {
            LOGGER.log(Level.INFO, message);
        }
    }

    private static void error(final String message) {
        if ( LogConfiguration.loggingIsEnabled() ) {
            LOGGER.log(Level.SEVERE, message);
        }
    }

}
