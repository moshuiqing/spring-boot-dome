package com.jinge.one.entity;

import java.util.List;

public class ImgUrl{

    private List<Shuffling> shufflings;


    public List<Shuffling> getShufflings() {
        return shufflings;
    }

    public void setShufflings(List<Shuffling> shufflings) {
        this.shufflings = shufflings;
    }


    public ImgUrl(List<Shuffling> shufflings) {
        this.shufflings = shufflings;
    }

    public ImgUrl() {
    }
    
}

