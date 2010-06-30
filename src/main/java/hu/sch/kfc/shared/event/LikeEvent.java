package hu.sch.kfc.shared.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.rpc.IsSerializable;

public class LikeEvent extends GwtEvent<LikeEventHandler> implements IsSerializable {

    private int liked;
    public static final Type<LikeEventHandler> TYPE = new Type<LikeEventHandler>();

    public LikeEvent() {
    }
    
    public LikeEvent(int liked) {
        this.liked = liked;
    }
    
    public int getLiked() {
        return liked;
    }
    
    public void setLiked(int liked) {
        this.liked = liked;
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