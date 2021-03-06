package org.wirez.core.client.session;

import org.wirez.core.client.canvas.Canvas;
import org.wirez.core.client.canvas.CanvasHandler;

public interface CanvasSessionManager<C extends Canvas, H extends CanvasHandler> 
        extends CanvasSessionProducer<C, H> {

    CanvasSession<C, H> getCurrentSession();
    
    void open(CanvasSession<C, H> session);

    void pause();

    void resume(CanvasSession<C, H> session);

    void dispose();
    
}
