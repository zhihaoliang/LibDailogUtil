package com.zhihaoliang.util.zuidialog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhihaoliang.util.dialog.MyDialog;

import static android.R.id.content;

public class MainActivity extends AppCompatActivity implements MyDialog.DialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTwo(View view){
        new MyDialog(this,this,(byte)0, "版本升级",false);
    }
    public void onOne(View view){
        new MyDialog(this,this,(byte)0, "版本升级",false,true);
    }

    @Override
    public void onCancle(byte state) {

    }

    @Override
    public void onConfrim(byte state) {

    }
}
