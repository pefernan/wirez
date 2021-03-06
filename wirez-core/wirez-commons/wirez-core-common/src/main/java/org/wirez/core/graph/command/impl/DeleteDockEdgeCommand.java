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

package org.wirez.core.graph.command.impl;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.uberfire.commons.validation.PortablePreconditions;
import org.wirez.core.command.CommandResult;
import org.wirez.core.graph.Edge;
import org.wirez.core.graph.Node;
import org.wirez.core.graph.command.GraphCommandExecutionContext;
import org.wirez.core.graph.command.GraphCommandResultBuilder;
import org.wirez.core.graph.content.relationship.Dock;
import org.wirez.core.rule.RuleViolation;

import java.util.List;

/**
 * Deletes a dock edge ( the outgoing edge from the candidate that targets the parent ).
 */
@Portable
public final class DeleteDockEdgeCommand extends AbstractGraphCommand {

    private Node parent;
    private Node candidate;

    public DeleteDockEdgeCommand(@MapsTo("parent") Node parent,
                                 @MapsTo("candidate") Node candidate) {
        this.parent = PortablePreconditions.checkNotNull( "parent",
                parent );
        this.candidate = PortablePreconditions.checkNotNull( "candidate",
                                                             candidate );
    }
    
    @Override
    public CommandResult<RuleViolation> allow(final GraphCommandExecutionContext context) {
        return check( context );
    }

    @Override
    public CommandResult<RuleViolation> execute(final GraphCommandExecutionContext context) {
        final CommandResult<RuleViolation> results = check( context );
        if ( !results.getType().equals( CommandResult.Type.ERROR ) ) {
            final Edge<Dock, Node> edge = getEdgeForTarget();
            if ( null != edge ) {
                edge.setSourceNode(null);
                edge.setTargetNode(null);
                parent.getInEdges().remove( edge );
                candidate.getOutEdges().remove( edge );
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    private Edge<Dock, Node> getEdgeForTarget() {
        final List<Edge<?, Node>> outEdges = parent.getInEdges();
        if ( null != outEdges && !outEdges.isEmpty() ) {
            for ( Edge<?, Node> outEdge : outEdges ) {
                if ( outEdge.getContent() instanceof Dock ) {
                    final Node source = outEdge.getSourceNode();
                    if ( null != source && source.equals( candidate )) {
                        return (Edge<Dock, Node>) outEdge;
                    }
                }
            }
        }
        
        return null;
    }

    protected CommandResult<RuleViolation> doCheck(final GraphCommandExecutionContext context) {
        return GraphCommandResultBuilder.RESULT_OK;
    }

    @Override
    public CommandResult<RuleViolation> undo(GraphCommandExecutionContext context) {
        final AddDockEdgeCommand undoCommand = new AddDockEdgeCommand( parent, candidate );
        return undoCommand.execute( context );
    }

    @Override
    public String toString() {
        return "DeleteDockEdgeCommand [parent=" + parent.getUUID() + ", candidate=" + candidate.getUUID() + "]";
    }
}
