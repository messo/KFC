package hu.sch.kfc.shared.event;

import com.google.gwt.event.shared.EventHandler;

public interface LikeEventHandler extends EventHandler {

    void onLikeEvent(LikeEvent event);
    
}
