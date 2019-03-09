package com.jihuayu.core.api.event;

public class ReadEvent extends Event{
    private String text;


    public ReadEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
