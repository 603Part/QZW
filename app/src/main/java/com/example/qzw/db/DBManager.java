package com.example.qzw.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.qzw.bean.QianZiWen;
import com.example.qzw.bean.User;
import com.example.qzw.utils.SharedPreferencesUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class DBManager {
    private static String DB_NAME = "qzwdb.db";
    private Context mContext;
    private static SQLiteDatabase manager;
    private static final String TAG = "DBManager";
    public static void copyDb(Context mContext,String tab_name) {
        InputStream in = null;
        FileOutputStream out = null;

        String path = "/data/data/"+mContext.getPackageName()+"/databases";
        File file = new File(path + "/" +DB_NAME);
        try{
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                in = mContext.getAssets().open(DB_NAME);
                out = new FileOutputStream(file);
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = in.read(buf)) != -1) {
                    out.write(buf,0,length);
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {

            }
        }
    }


    public static SQLiteDatabase dbManager(Context mContext) {
        String dbPath = "/data/data/" + mContext.getPackageName()
                + "/databases/" + DB_NAME;
        manager = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
        return manager;
    }

    public static void initData(Context mContext, final List<QianZiWen.DataBean> mList) {
        try {
            for (QianZiWen.DataBean dataBean : mList) {
                manager.execSQL("INSERT INTO qzw VALUES" +
                        "(NULL," +
                        "'"+dataBean.getContent()+"','" +
                        ""+dataBean.getTranslate()+"'," +
                        "'"+dataBean.getTips()+"')");
            }
            SharedPreferencesUtil.getInstance(mContext).putSP("init", "true");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<QianZiWen.DataBean> query() {
        List<QianZiWen.DataBean> data = new ArrayList<>();
        Cursor cursor = manager.rawQuery("SELECT * FROM qzw",null);
        while(cursor.moveToNext()){
            QianZiWen.DataBean dataBean = new QianZiWen.DataBean();
            String content = cursor.getString(1);
            String translate= cursor.getString(2);
            String tips = cursor.getString(3);
            dataBean.setContent(content);
            dataBean.setTips(tips);
            dataBean.setTranslate(translate);
            data.add(dataBean);
        }
        cursor.close();
        return data;
    }

    public static List<QianZiWen.DataBean> query(String str) {
        List<QianZiWen.DataBean> data = new ArrayList<>();
        Cursor cursor = manager.rawQuery("SELECT * FROM qzw WHERE content LIKE('%"+str+"%')",null);
        while(cursor.moveToNext()){
            QianZiWen.DataBean dataBean = new QianZiWen.DataBean();
            String content = cursor.getString(1);
            String translate= cursor.getString(2);
            String tips = cursor.getString(3);
            dataBean.setContent(content);
            dataBean.setTips(tips);
            dataBean.setTranslate(translate);
            data.add(dataBean);
        }
        cursor.close();
        return data;
    }


    public static User queryUser(String username,String password) {
        User user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM user WHERE username = '"+username+"' AND password = '"+password+"'",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String pwd = cursor.getString(2);
            user = new User(name, pwd);
        }
        cursor.close();
        return user;
    }

    public static int insertNewUser(User user) {
        // 先判断有无此用户
        Cursor cursor = manager.rawQuery("select 1 from user where username = '" + user.getName() + "' limit 1;", null);
        boolean b = false; // 默认无
        while (cursor.moveToNext()) {
            b = true; // 有此用户
        }
        if (b) {
            return 1; //用户存在
        }
        try {
            manager.execSQL("INSERT INTO user values(NULL,'" + user.getName() + "','" + user.getPwd() + "')");
        } catch (Exception e) {
            e.printStackTrace();
            return 2; // 用户不存在但是插入失败
        }

        return 0; // success
    }

}
