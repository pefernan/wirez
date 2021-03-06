package org.wirez.core.graph.processing.traverse.tree;

import org.wirez.core.graph.Edge;
import org.wirez.core.graph.Graph;
import org.wirez.core.graph.Node;

public abstract class AbstractTreeTraverseCallback<G extends Graph, N extends Node, E extends Edge> 
        implements TreeTraverseCallback<G, N, E> {
    
    @Override
    public void startGraphTraversal(final G graph) {
        
    }

    @Override
    public boolean startNodeTraversal(final N node) {
        return false;
    }

    @Override
    public boolean startEdgeTraversal(final E edge) {
        return false;
    }

    @Override
    public void endNodeTraversal(final N node) {

    }

    @Override
    public void endEdgeTraversal(final E edge) {

    }

    @Override
    public void endGraphTraversal() {

    }
}
