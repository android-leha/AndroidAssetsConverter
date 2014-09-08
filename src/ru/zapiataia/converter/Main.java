package ru.zapiataia.converter;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        ScreenDensity.setBaseSize(16);

        Map<String, ScreenDensity> screenDensityList = new HashMap<String, ScreenDensity>(4);

        screenDensityList.put("drawable-mdpi", new ScreenDensity(2));
        screenDensityList.put("drawable-hdpi", new ScreenDensity(3));
        screenDensityList.put("drawable-xhdpi", new ScreenDensity(4));
        screenDensityList.put("drawable-xxhdpi", new ScreenDensity(6));

        File dir = new File(".");
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().matches("(.+)\\.png$");
            }
        });

        /**
         *
         * Launcher icons base = 16
         *
         */


        for (File file : files) {
            for (String key : screenDensityList.keySet()) {
                if (screenDensityList.get(key).resize(file, key)) {
                    System.out.println("OK " + key + ": " + file.getName());
                } else {
                    System.out.println("Error " + key + ": " + file.getName());
                }
            }
        }

    }
}
