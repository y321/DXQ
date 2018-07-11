package com.zucc.yxr31501359.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.zucc.yxr31501359.ICallback.ICallBack;
import com.zucc.yxr31501359.R;

import com.zucc.yxr31501359.Task.UsersTask;
import com.zucc.yxr31501359.Util.DBHelper;
import com.zucc.yxr31501359.entity.Users;

public class LoginActivity extends AppCompatActivity implements ICallBack {
    private EditText name_login;
    private EditText password_reg;
    private EditText pwd_reg;

    private Button button_login;
    private Button button_reg;
    private String idvalue;
    private String passwordvalue;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        name_login=(EditText) findViewById(R.id.reg_name);
        password_reg = (EditText) findViewById(R.id.reg_password);
        pwd_reg =(EditText) findViewById(R.id.reg_rePwd);
        button_reg = (Button) findViewById(R.id.reg_button);
        dbHelper = new DBHelper(LoginActivity.this);
        db=dbHelper.getReadableDatabase();

        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(password_reg.getText().toString().equals(pwd_reg.getText().toString())){
                   Users users = new Users();
                   users.setUsername(name_login.getText().toString());
                   users.setPassword(password_reg.getText().toString());


                   UsersTask usersTask = new UsersTask(db);
                   usersTask.execute(users);
//                   UserService userService = new UserService(LoginActivity.this);
//                   userService.register(users);

                   Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "密码不一致请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onFinished(String result) {

    }
}
