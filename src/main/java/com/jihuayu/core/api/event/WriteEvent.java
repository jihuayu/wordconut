package com.jihuayu.core.api.event;

public class WriteEvent {
    private String Path;
    private String text;

    public WriteEvent(String path, String text) {
        Path = path;
        this.text = text;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
