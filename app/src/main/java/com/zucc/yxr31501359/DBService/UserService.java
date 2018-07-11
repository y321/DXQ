package com.zucc.yxr31501359.DBService;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.zucc.yxr31501359.entity.Users;

public class UserService {

    private SQLiteDatabase sdb;
    public UserService(SQLiteDatabase db){
        this.sdb=db;
    }


    //登录用
    public String login(String username,String password){

        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return "login successful";
        }
        return "login fail";
    }
    //注册用
    public String register(Users users){

        String sql="insert into users(username,password) values(?,?)";
        Object obj[]={users.getUsername(),users.getPassword()};
        sdb.execSQL(sql, obj);

         /*sql="select * from users where username=? ";
        Cursor c=sdb.rawQuery(sql, new String[]{"e"});
        while (c.moveToNext()) {
            String name = c.getString(c.getColumnIndex("username"));
            String b=null;
            Log.v(b,  name);
        }
        c.close();*/

//        String tag=null;
//        Log.v(tag,"aaaa");
        return "register successful！";
    }

}
