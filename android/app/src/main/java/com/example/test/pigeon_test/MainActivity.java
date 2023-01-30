package com.example.test.pigeon_test;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.example.pigeon.Message.Book;
import com.example.pigeon.Message.BookApi;
import com.example.pigeon.Message.ColorApi;
import com.example.pigeon.Message.ColorApi.Reply;
import io.flutter.embedding.android.FlutterActivity;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FlutterActivity {

    private static final String TAG = "MainActivity";
    private static final int WIFI_REQUEST_CODE = 1;

    private ColorApi mColorApi;
    private WifiManager mWifiManager;

    private class MyApi implements BookApi {

        public List<Book> search(String keyword) {
            Log.i(TAG, "search");

            mColorApi.updateColor(11L, new Reply<Long>() {
                @Override
                public void reply(Long reply) {
                    Log.i(TAG, "ColorApi updateColor reply: " + reply);
                }
            });

            Book result = new Book();
            result.setAuthor(keyword);
            result.setTitle(String.format("%s's Life", keyword));
            ArrayList<Book> books = new ArrayList<>();
            books.add(result);
            return books;
        }

        @NonNull
        @Override
        public List<Book> find(@NonNull String keyword) {
            return null;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        BookApi.setup(getFlutterEngine().getDartExecutor().getBinaryMessenger(), new MyApi());
        mColorApi = new ColorApi(getFlutterEngine().getDartExecutor().getBinaryMessenger());

        mWifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                requestPermissions(new String[]{permission.ACCESS_FINE_LOCATION}, WIFI_REQUEST_CODE);
            }
        } else {
            printWifiConfigureData();
        }
        printWifiConnectivityInfo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (requestCode == WIFI_REQUEST_CODE) {
            printWifiConfigureData();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressLint("MissingPermission")
    private void printWifiConfigureData() {
        Log.i(TAG, "printWifiData");
        List<WifiConfiguration> configuredNetworks = mWifiManager.getConfiguredNetworks();

        if (configuredNetworks == null) {
            Log.i(TAG, "configuredNetworks is null");
            return;
        }
        if (configuredNetworks.size() == 0) {
            Log.i(TAG, "configuredNetworks size is zero");
        }
        for (WifiConfiguration configuration : configuredNetworks) {
            Log.i(TAG, configuration + "");
        }
    }

    private void printWifiConnectivityInfo() {
        WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
        if (VERSION.SDK_INT >= VERSION_CODES.S) {
            mWifiManager.getConnectionInfo().getCurrentSecurityType();
        }
        Log.i(TAG, "connectionInfo: " + connectionInfo);
    }
}
