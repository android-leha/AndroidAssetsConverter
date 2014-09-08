package ru.zapiataia.converter;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;

/**
* Created by Alexey on 09.09.2014.
*/
class ScreenDensity {

    private static final String PREFIX = "ic_";

    private static int baseSize = 16;

    private final int ratio;

    public static void setBaseSize(int baseSize) {
        ScreenDensity.baseSize = baseSize;
    }

    /**
     *
     * @param ratio Ratio for this density
     */
    public ScreenDensity(int ratio) {
        this.ratio = ratio;
    }

    /**
     * Return image size for this screen density
     *
     * @param baseSize Base size of image
     * @return Size
     */
    private int getSize(int baseSize) {
        return ratio * baseSize;
    }

    /**
     * Save resize image in destination dir
     *
     * @param file File to resize
     * @param destinationDirName Name of destination dir
     * @return true on success ir false on error
     */
    public boolean resize(File file, String destinationDirName) {
        try {
            File destinationDir = new File(destinationDirName);
            if (!destinationDir.exists()) {
                destinationDir.mkdir();
            }
            Thumbnails.of(file)
                    .size(getSize(baseSize), getSize(baseSize))
                    .toFiles(destinationDir, new Rename() {
                        @Override
                        public String apply(String name, ThumbnailParameter param) {
                            return PREFIX + name;
                        }
                    });
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
