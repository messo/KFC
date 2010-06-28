package hu.sch.kfc.shared;

public class LikeEvent extends Event {

    private int liked;

    public LikeEvent() {
    }
    
    public LikeEvent(int liked) {
        this.liked = liked;
    }
    
    public int getLiked() {
        return liked;
    }
    
}
