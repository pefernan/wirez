package org.wirez.core.graph.processing.traverse.tree;

import org.wirez.core.graph.Edge;
import org.wirez.core.graph.Graph;
import org.wirez.core.graph.Node;

public interface TreeTraverseCallback<G extends Graph, N extends Node, E extends Edge> {

    void startGraphTraversal(G graph);

    boolean startNodeTraversal(N node);

    boolean startEdgeTraversal(E edge);

    void endNodeTraversal(N node);

    void endEdgeTraversal(E edge);
    
    void endGraphTraversal();
    
    
    
}
