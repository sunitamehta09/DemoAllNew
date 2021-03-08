package controllerAll.AppUtilsAll.CheckInternetSppedUtils;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import controllerAll.utilsAll.AppConstants;
import controllerAll.AppUtilsAll.MyLogger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckInternetSpeed {
    private Context mContext;
    private long startTime;
    private long endTime;
    private long fileSize;
    private InternetSpeedListener speedListener;

    public CheckInternetSpeed(Context mContext) {
        this.mContext = mContext;
        speedListener = (InternetSpeedListener) mContext;
    }

    public void internetSppedCall() {
        MyLogger.e("checkapp", "internetSppedCall");
        OkHttpClient client = new OkHttpClient();
        // bandwidth in kbps
        int POOR_BANDWIDTH = 150;
        int AVERAGE_BANDWIDTH = 550;
        int GOOD_BANDWIDTH = 2000;

        Request request = new Request.Builder()
                .url("https://cdn.editorji.com/settings/downloadTestImage.jpg")
                .build();
        startTime = System.currentTimeMillis();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Download url>>", "Time out in api");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    MyLogger.d("", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                InputStream input = response.body().byteStream();

                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];

                    if (input.read(buffer) != -1) {
                        bos.write(buffer);
                    }
                    //byte[] docBuffer = bos.toByteArray();
                    fileSize = bos.size();
                } finally {
                    input.close();
                }

                endTime = System.currentTimeMillis();
                // calculate how long it took by subtracting endtime from starttime
                double timeTakenMills = Math.floor(endTime - startTime);  // time taken in milliseconds
                double timeTakenSecs = timeTakenMills / 1000;  // divide by 1000 to get time in seconds
                final int kilobytePerSec = (int) Math.round(1024 / timeTakenSecs);

                if (kilobytePerSec <= AppConstants.MINIMUM_BANDWIDTH) {
                    // slow connection
                    MyLogger.e("Download url>>", "lowSpeed");
                    speedListener.lowSpeed();
                } else {
                    speedListener.okSpeed();
                    MyLogger.e("Download url>>", "okSpeed");
                }

                // get the download speed by dividing the file size by time taken to download
                int speed = ((int) (fileSize / timeTakenSecs));
                int speedinBit = speed * 8;
                if (speedinBit >= AppConstants.MINIMUM_BANDWIDTH) {

                } else {

                }
                MyLogger.e("Download url", "Time taken in secs: " + timeTakenSecs);
                MyLogger.e("Download url", "kilobyte per sec: " + kilobytePerSec);
                MyLogger.e("Download url", "Download Speed: " + speed);
                MyLogger.e("Download url", "File size: " + fileSize);
            }
        });
    }
}
