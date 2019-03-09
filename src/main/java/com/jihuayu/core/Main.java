package com.jihuayu.core;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public Main(){
        EventBus.getDefault().register(this);
    }
    public static void main(String[] args){
        new Main();

    }
}
