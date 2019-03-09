package com.example.zhangzhaoxiang.hmandroid;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Register extends Activity {
    private EditText edittext1,edittext2,edittext3;
    private Button button;
    private DBHelper datebaseHelper;
    //数据库名称
    private static final String DATABASE_NAME="test.db";
    //数据库版本号
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="zz";

    private SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        edittext1=(EditText)findViewById(R.id.editview1);
        edittext2=(EditText)findViewById(R.id.editview2);
        edittext3=(EditText)findViewById(R.id.editview3);
        button=(Button)findViewById(R.id.tijiao);
        button.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String namestring = edittext1.getText().toString();
                String passstring = edittext2.getText().toString();
                String repassstring=edittext3.getText().toString();
                if(namestring.equals("")||passstring.equals(""))
                {
                    new AlertDialog.Builder(Register.this).setTitle("错误")
                            .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                            .show();}
                else if(passstring.equals(repassstring))
                {
                    datebaseHelper =new DBHelper(Register.this,DATABASE_NAME,null,DATABASE_VERSION);
                    db =  datebaseHelper.getWritableDatabase();
                    db.execSQL("insert into zz (name,password) values(?,?)",new String[]{namestring,passstring});
                    Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_LONG).show();
                    Intent b=new Intent(Register.this,MainActivity.class);
                    startActivity(b);
                }
                else
                {
                    Toast.makeText(Register.this,"两次密码不一致", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

