package hu.sch.kfc.shared;

import java.io.Serializable;

public class Liked implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3898657171582677266L;
    private int like;

    public Liked() {
        // TODO Auto-generated constructor stub
    }

    public Liked(int like) {
        this.like = like;
    }

    public int getValue() {
        return like;
    }
}
