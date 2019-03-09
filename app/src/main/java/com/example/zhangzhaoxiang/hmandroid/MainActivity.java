package com.example.zhangzhaoxiang.hmandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EditText edittext1,edittext2,edittext3;
    private Button button;
    private com.example.zhangzhaoxiang.hmandroid.DBHelper datebaseHelper;
    private TextView textview;
    private static final String DATABASE_NAME="test.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="zz";
    private com.example.zhangzhaoxiang.hmandroid.DBHelper databaseHelper;
    private SQLiteDatabase db;

    private EditText nameText,passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = (EditText) findViewById(R.id.user_name);
        passText = (EditText) findViewById(R.id.user_password);
        Button tijiaoai = (Button) findViewById(R.id.in_button);
        textview = (TextView) findViewById(R.id.main_add);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        tijiaoai.setOnClickListener(new LoginListener());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    class LoginListener implements View.OnClickListener {
        public void onClick(View v){
            String nameString =nameText.getText().toString();
            String passString=passText.getText().toString();
            if(nameString.equals("")||passString.equals(""))
            {
                new AlertDialog.Builder(MainActivity.this).setTitle("错误")
                        .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                        .show();
            }else{
                isUserinfo(nameString,passString);
            }
        }
    }
    public Boolean isUserinfo(String name,String pass)
    {
        String nameString=name;
        String passString=pass;
        databaseHelper=new com.example.zhangzhaoxiang.hmandroid.DBHelper(MainActivity.this,DATABASE_NAME,null,DATABASE_VERSION);
        db =  databaseHelper.getReadableDatabase();
        try{
            Cursor cursor=db.query(TABLE_NAME, new String[]{"name","password"},"name=?",new String[]{nameString},null,null,"password");
            while(cursor.moveToNext())
            {
                String password=cursor.getString(cursor.getColumnIndex("password"));
                if(passString.equals(password))
                {
                    new AlertDialog.Builder(MainActivity.this).setTitle("正确").setMessage("成功登录").setPositiveButton("确定", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Intent a=new Intent(MainActivity.this,video.class);
                            startActivity(a);}
                    }).show();
                    break;
                }
                else
                {
                    Toast.makeText(this, "用户名密码不正确",Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }catch(SQLiteException e){

        }
        return false;
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, ass.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(MainActivity.this, video.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Uri uri = Uri.parse("https://www.baidu.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(MainActivity.this, clist.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
