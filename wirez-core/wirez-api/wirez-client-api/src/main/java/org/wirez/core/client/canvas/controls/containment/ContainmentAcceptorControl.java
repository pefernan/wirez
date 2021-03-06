package org.wirez.core.client.canvas.controls.containment;

import org.wirez.core.graph.Node;
import org.wirez.core.client.canvas.CanvasHandler;
import org.wirez.core.client.canvas.controls.CanvasControl;

public interface ContainmentAcceptorControl<H extends CanvasHandler> extends CanvasControl<H> {
    
    boolean allow(Node parent, Node child);

    boolean accept(Node parent, Node child);
    
}
