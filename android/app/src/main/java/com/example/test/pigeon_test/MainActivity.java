package com.example.test.pigeon_test;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.MyApplication;
import com.example.pigeon.Message.Book;
import com.example.pigeon.Message.BookApi;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.plugin.common.BinaryMessenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends FlutterActivity {

    private static final String TAG = "MainActivity";

    private static class MyApi implements BookApi {

        public List<Book> search(String keyword) {
            Log.i(TAG, "search");
            Book result = new Book();
            result.setAuthor(keyword);
            result.setTitle(String.format("%s's Life", keyword));
            ArrayList<Book> books = new ArrayList<>();
            books.add(result);
            return books;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        BookApi.setup(getFlutterEngine().getDartExecutor().getBinaryMessenger(), new MyApi());
    }
}
