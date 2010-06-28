package hu.sch.kfc.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LikeEvent extends GwtEvent<LikeEventHandler> {

    public static final Type<LikeEventHandler> TYPE = new Type<LikeEventHandler>();
    
    public LikeEvent() {
    }
    
    @Override
    protected void dispatch(LikeEventHandler handler) {
        handler.onLikeEvent(this);
    }

    @Override
    public Type<LikeEventHandler> getAssociatedType() {
        return TYPE;
    }

}
