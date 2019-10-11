package com.eaeducation.ixuexue.net;

import android.content.res.AssetManager;
import android.text.TextUtils;

import com.eaeducation.ixuexue.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by cuihao on 2017/4/22.
 */

public class TestApiDataFactory {

    private static final String TAG = "TestApiDataFactory";


    public static String getThingsDevice() {
        String data = readString("api_get_devices");
        if (TextUtils.isEmpty(data)) {
            return "";
        }
        return data;
    }

    public static String getQuestionData() {
        String fileName = readString("Question.json");
        if (TextUtils.isEmpty(fileName)) {
            return "";
        }
        return fileName;
    }

    private static String readString(String fileName) {
        InputStreamReader in = null; // 读取的内容（输入流）
        InputStream is = null;
        AssetManager assetManager = MyApplication.getApplication().getAssets();
        try {
            is = assetManager.open(fileName);
            in = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(in);
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
