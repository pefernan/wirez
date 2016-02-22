package org.wirez.core.api.graph.processing.traverse.content;

import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.content.view.View;

public interface FullContentTraverseCallback<N extends Node<View, Edge>, E extends Edge<Object, Node>> 
    extends ContentTraverseCallback<Object, N, E> {

    void startViewEdgeTraversal(E edge);

    void endViewEdgeTraversal(E edge);

    void startChildEdgeTraversal(E edge);

    void endChildEdgeTraversal(E edge);
    
    void startParentEdgeTraversal(E edge);

    void endParentEdgeTraversal(E edge);
    
}
