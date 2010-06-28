package hu.sch.kfc.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LikeEventHandler extends EventHandler {

    void onLikeEvent(LikeEvent event);
    
}
