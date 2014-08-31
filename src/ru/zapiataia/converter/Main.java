package ru.zapiataia.converter;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File dir = new File(".");
        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().matches("(.+)\\.png$");
            }
        });

        /**
         * drawable-xxhdpi    96   216
         * drawable-xhdpi     64   144
         * drawable-hdpi      48    94
         * drawable-mdpi      32    63
         */

        for (File file : files) {
            resize(file, "drawable-xxhdpi", 96);
            resize(file, "drawable-xhdpi", 64);
            resize(file, "drawable-hdpi", 48);
            resize(file, "drawable-mdpi", 32);
        }

    }

    private static BufferedImage resize(File file, String dirName, int size) {
        try {

            File destinationDir = new File(dirName);
            if (!destinationDir.exists()) {
                destinationDir.mkdir();
            }
            Thumbnails.of(file)
                    .size(size, size)
                    .toFiles(destinationDir, new Rename() {
                        @Override
                        public String apply(String name, ThumbnailParameter param) {
                            return "ic_" + name;
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
