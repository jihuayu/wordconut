package com.jihuayu.wordcount;

import com.jihuayu.wordcount.api.Plugin;
import com.jihuayu.wordcount.api.event.ReadyEvent;
import org.greenrobot.eventbus.EventBus;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static Map<String,Plugin> pluginMap = new HashMap<>();

    public static void main(String[] args) {
       loadJars();
        if(args.length==0&&(pluginMap.containsKey("-"))){
            pluginMap.get("-").doCommand(new String[]{});
        }
        for(int i=0;i<args.length;i++){
            if(args[i].startsWith("-")&&pluginMap.containsKey(args[i])){
                    pluginMap.get(args[i]).doCommand(args[++i].split(";"));
            }
        }
        new WordCount();
        EventBus.getDefault().post(new ReadyEvent());
    }
    private static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = jarFile.toURI().toURL();
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.setAccessible(accessible);
        }
    }
//    private static boolean isChineseChar(char c) {
//        return String.valueOf(c).matches("[\u0000-\u007f]");
//    }
    private static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
    private static void loadJars(){
        loadJar("./libs");
        File dir = new File("libs");
        if(!dir.exists()){
            dir.mkdir();
        }
        for(File i : dir.listFiles()) {
            if(i.isDirectory())continue;
            Plugin instance = null;
            Class<?> clazz = null;
            try {
                clazz = Class.forName(getFileNameNoEx(i.getName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return ;
            }
            if (Plugin.class.isAssignableFrom(clazz)) {
                try {
                    instance = (Plugin) clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            if(instance!=null) {
                if(instance.getCommandName()==null){
                    continue;
                }
                pluginMap.put("-" + instance.getCommandName(), instance);
            }
        }
    }
}
