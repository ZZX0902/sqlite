package com.example.zhangzhaoxiang.hmandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.RawContacts;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class clist extends Activity {

    private ListView mLvShow;
    private List<Map<String, String>> dataList;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clist);
        mLvShow = (ListView) findViewById(R.id.lv_show);
        dataList = getDataList();
        adapter = new SimpleAdapter(this, dataList, R.layout.activity_clist2//
                , new String[] { "name", "number", "email" }//
                , new int[] { R.id.tv_name, R.id.tv_number, R.id.tv_email });
        mLvShow.setAdapter(adapter);
    }

    /**
     * 加载数据
     *
     * @return
     */
    private List<Map<String, String>> getDataList() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(RawContacts.CONTENT_URI,
                new String[] { RawContacts._ID, RawContacts.DISPLAY_NAME_PRIMARY }//
                , null, null, null);
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            String id = cursor.getString(cursor.getColumnIndex(RawContacts._ID));// "_id"
            String name = cursor.getString(cursor.getColumnIndex(RawContacts.DISPLAY_NAME_PRIMARY));// "display_name"
            map.put("name", name);
            Cursor phoneCursor = resolver.query(Phone.CONTENT_URI//
                    , new String[] { Phone.NUMBER }// "data1"
                    , "raw_contact_id=?", new String[] { id }, null);
            if (phoneCursor.moveToNext()) {
                String number = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER));
                map.put("number", number);
            }
            Cursor emailCursor = resolver.query(Email.CONTENT_URI//
                    , new String[] { Email.ADDRESS}// "data1"
                    , "raw_contact_id=?", new String[] { id }, null);
            if (emailCursor.moveToNext()) {
                String email = emailCursor.getString(emailCursor.getColumnIndex(Email.ADDRESS));
                map.put("email", email);
            }
            list.add(map);
        }
        return list;
    }
}