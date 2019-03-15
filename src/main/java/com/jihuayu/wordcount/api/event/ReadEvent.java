package com.jihuayu.wordcount.api.event;

public class ReadEvent extends Event{
    private String text;

    public ReadEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public ReadEvent setText(String text) {
        this.text = text;
        return this;
    }
}
