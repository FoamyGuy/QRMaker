package com.makemyandroidapp.qrmaker;

import java.net.URL;

import android.os.AsyncTask;

public class GetQRTask extends AsyncTask<URL, Integer, Long> {
    protected Long doInBackground(URL... urls) {
/*        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {
            totalSize += Downloader.downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }*/
        return 0l;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
        //showDialog("Downloaded " + result + " bytes");
    }
}