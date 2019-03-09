package com.jihuayu.core.api;

import java.util.List;

public interface Plugin {
    void getCommandUsage(List<String> info);
    String getCommandName();
    void loadCommand(String[]args);
}
