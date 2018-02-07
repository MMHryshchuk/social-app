package com.clearsoft.welivre.core.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;


import com.clearsoft.welivre.R;

import java.io.File;

/**

 */
public class CachedFilesRepository {
    public final String ROOT_DIR;
    public static final String AUDIO_CACHE_DIR = "audios";
    public static final String IMAGE_CACHE_DIR = "images";
    public static final String FILES_CACHE_DIR = "files";

    private static CachedFilesRepository ourInstance;

    private File mStorageDir;
    private File mRootCacheDir;
    private File mAudioCacheDir;
    private File mImageCacheDir;
    private File mFileCacheDir;

    private CachedFilesRepository(Context context) {
        ROOT_DIR = "." + context.getString(R.string.app_name);
    }

    public static synchronized CachedFilesRepository getInstance(Context context, boolean privateCacheDirs) {
        if (ourInstance == null)
            ourInstance = new CachedFilesRepository(context);

        initTruePathToStorage(privateCacheDirs, context);
        ourInstance.createCacheDirsIfNeed();
        return ourInstance;
    }

    public File getAudioCacheDir() {
        return mAudioCacheDir;
    }

    public File getFileCacheDir() {
        return mFileCacheDir;
    }

    public File getImageCacheDir() {
        return mImageCacheDir;
    }

    public File createAudioFile(String fileName) {
        return new File(mAudioCacheDir, fileName + ".mp3");
    }

    public File createImageFile(String fileName) {
        return new File(mImageCacheDir, fileName + ".png");
    }

    public File createSimpleFile(String fileName) {
        return new File(mAudioCacheDir, fileName + ".mp3");
    }

    public File createTempImage() {
        return new File(mImageCacheDir, "temp_image" + ".png");
    }


    public void clearAllCache() {
        File[] files = mAudioCacheDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    private void createCacheDirsIfNeed() {
        try {
            mRootCacheDir = new File(mStorageDir, ROOT_DIR);
            if (!mRootCacheDir.exists())
                mRootCacheDir.mkdir();

            mAudioCacheDir = new File(mRootCacheDir, AUDIO_CACHE_DIR);
            if (!mAudioCacheDir.exists())
                mAudioCacheDir.mkdir();


            mFileCacheDir = new File(mRootCacheDir, FILES_CACHE_DIR);
            if (!mFileCacheDir.exists())
                mFileCacheDir.mkdir();

            mImageCacheDir = new File(mRootCacheDir, IMAGE_CACHE_DIR);
            if (!mImageCacheDir.exists()) {
                mImageCacheDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //    / Checks if external storage is available for read and write /
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private static void initTruePathToStorage(boolean privateCacheDirs, Context
            context) {
        if (Environment.isExternalStorageEmulated() && isExternalStorageWritable()) {
            if (!privateCacheDirs) {
                ourInstance.mStorageDir = Environment.getExternalStorageDirectory();
            } else {
                ourInstance.mStorageDir = context.getExternalCacheDir();
            }
        } else {
            if (!privateCacheDirs) {
                ourInstance.mStorageDir = context.getDir(ourInstance.ROOT_DIR, Context.MODE_PRIVATE);
                if (!ourInstance.mStorageDir.exists()) {
                    ourInstance.mStorageDir.mkdir();
                }
            } else {


            }
        }
    }
}

