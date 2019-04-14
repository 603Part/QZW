package com.example.qzw.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsUtils {
    public static String getFromAssets(String fileName,Context appContext){
        String result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(appContext.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            StringBuilder Result= new StringBuilder();
            while((line = bufReader.readLine()) != null)
                Result.append(line);
            return Result.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
