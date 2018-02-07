package com.clearsoft.welivre.core.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.domain.repository.PreferenceRepository;

import java.io.File;


/**
 * Created by vladimir on 29.07.16.
 */
public class CachedFilesBank {
    public static final String ROOT_CACHE_DIR = ".AndroidSysData";
    public static final String AUDIO_CACHE_DIR = "audios";
    public static final String IMAGE_CACHE_DIR = "images";
    private static CachedFilesBank ourInstance = new CachedFilesBank();
    private File mRootDir;
    private File mRootCacheDir;
    private File mAudioCacheDir;
    private File mImageCacheDir;

    public static CachedFilesBank getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new CachedFilesBank();

        if (Environment.isExternalStorageEmulated() && isExternalStorageWritable()){
            File path = Environment.getExternalStorageDirectory();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                File[] files = context.getExternalFilesDirs(null);
                if (files != null){

                    if (files.length == 1){
                        path = files[0];
                    } else {
                        PreferenceRepository preferenceRepository = App.getApp(context)
                                .getAppComponent()
                                .getPreferenceRepository();

                        if (preferenceRepository.isSavedInExternalStorage()){
                            path = files[1];
                        } else {
                            path = files[0];
                            if (path.getPath().contains("/Android/")){
                                String filePath = path.getPath();
                                path = new File(filePath.substring(0, filePath.indexOf("/Android/")));
                            }
                        }
                    }
                }
            }

            ourInstance.mRootDir = path;
        } else {
            ourInstance.mRootDir = context.getDir(".DataDir", Context.MODE_PRIVATE);
            if (!ourInstance.mRootDir.exists()){
                ourInstance.mRootDir.mkdirs();
            }
        }

        ourInstance.createCacheDirsIfNeed();
        return ourInstance;
    }

    public File getAudioCacheDir() {
        return mAudioCacheDir;
    }

    public boolean isAudioCacheFileDownloading(long audioId){
        return createAudioCacheFile(audioId).exists();
    }

    public boolean isAudioCacheFileDownloaded(long audioId){
        return new File(mAudioCacheDir, audioId+".mp3.cache").exists();
    }

    public File createAudioCacheFile(long audioId){
        return new File(mAudioCacheDir, audioId+".mp3");
    }

    public void clearAll(){
        File[] files = mAudioCacheDir.listFiles();
        if (files != null){
            for (File file : files){
                file.delete();
            }
        }
    }

    private void createCacheDirsIfNeed(){
        mRootCacheDir = new File(mRootDir, ROOT_CACHE_DIR);
        if (!mRootCacheDir.exists())
            mRootCacheDir.mkdir();

        mAudioCacheDir = new File(mRootCacheDir, AUDIO_CACHE_DIR);
        if (!mAudioCacheDir.exists())
            mAudioCacheDir.mkdir();

        mImageCacheDir = new File(mRootCacheDir, IMAGE_CACHE_DIR);
        if (!mImageCacheDir.exists()) {
            mImageCacheDir.mkdir();
        }
    }

    public File createImageCacheFile(){
        return new File(mImageCacheDir,"img"+ System.currentTimeMillis()+".png");
    }


    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
                return true;
        }
        return false;
    }
}
