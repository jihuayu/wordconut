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

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        loadJar("./libs");
        File dir = new File("libs");
        if(!dir.exists()){
            dir.mkdir();
        }
        for(File i : dir.listFiles()) {
            if(i.isDirectory())continue;
            Plugin instance = null;
            Class<?> clazz = Class.forName(getFileNameNoEx(i.getName()));
            if (Plugin.class.isAssignableFrom(clazz))
                instance = (Plugin) clazz.newInstance();
            if(instance!=null) {
                if(instance.getCommandName()==null){
                    continue;
                }
                pluginMap.put("-" + instance.getCommandName(), instance);
            }
        }
        if(args.length==0){
            if(pluginMap.containsKey("-")){
                pluginMap.get("-").loadCommand(new String[]{});
            }
        }
        for(int i=0;i<args.length;i++){
            if(args[i].startsWith("-")){
                if(pluginMap.containsKey(args[i])){
                    pluginMap.get(args[i]).loadCommand(args[++i].split(";"));
                }
            }
        }
        new WordCount();
        EventBus.getDefault().post(new ReadyEvent());
    }
    private static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        // 从URLClassLoader类中获取类所在文件夹的方法，jar也可以认为是一个文件夹
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        // 获取方法的访问权限以便写回
        boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = jarFile.toURI().toURL();
            method.invoke(classLoader, url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.setAccessible(accessible);
        }
    }
    private static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u0000-\u007f]");
    }
    private static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
