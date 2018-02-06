package com.lzw.library.utils;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

public class RecordUtil {
    private static final int SAMPLE_RATE_IN_HZ = 8000;
    private MediaRecorder recorder = new MediaRecorder();
    private String mPath;
    public static final int MAX_TIME = 60;// 最长录音时间
    public static final int MIN_TIME = 2;// 最短录音时间

    public static final int RECORD_NO = 0; // 不在录音
    public static final int RECORD_ING = 1; // 正在录音
    public static final int RECORD_ED = 2; // 完成录音

    public RecordUtil(String path) {
        mPath = path;
    }

    @SuppressWarnings("deprecation")
    public void start() throws IOException {
        String state = android.os.Environment.getExternalStorageState();
        if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
            throw new IOException("SD Card is not mounted,It is  " + state
                    + ".");
        }
        File directory = new File(mPath).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Path to file could not be created");
        }
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setAudioSamplingRate(SAMPLE_RATE_IN_HZ);
        recorder.setOutputFile(mPath);
        recorder.prepare();
        recorder.start();
    }

    public void stop() throws IOException {
        recorder.stop();
        recorder.release();
    }

    public double getAmplitude() {
        if (recorder != null) {
            return (recorder.getMaxAmplitude());
        }
        return 0;
    }

    public static int getVoiceDuration(String path) {
        int duration = 0;
        try {
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(path);
            player.prepare();
            duration = player.getDuration() / 1000;
        } catch (Exception e) {
            duration = 0;
        }
        return duration;
    }
}
