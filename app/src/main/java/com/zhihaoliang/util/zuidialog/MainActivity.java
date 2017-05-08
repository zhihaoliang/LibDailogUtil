package com.zhihaoliang.util.zuidialog;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhihaoliang.util.dialog.MyCustomDialog;
import com.zhihaoliang.util.dialog.MyDialog;
import com.zhihaoliang.util.zuidialog.wigets.FlikerProgressBar;


public class MainActivity extends AppCompatActivity implements MyDialog.DialogListener, Runnable{

    private FlikerProgressBar mFlikerProgressBar;

    private Thread mDownLoadThread;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mFlikerProgressBar.setProgress(msg.arg1);
            if(msg.arg1 == 100){
                mFlikerProgressBar.finishLoad();
            }
        }
    };
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

    public void onDowload(View view){
        mFlikerProgressBar = (FlikerProgressBar) getLayoutInflater().inflate(R.layout.layout_view,null);
        new MyCustomDialog(this, "版本升级，请稍后",false,mFlikerProgressBar);
        downLoad();
    }

    private void downLoad() {
        mDownLoadThread = new Thread(this);
        mDownLoadThread.start();
    }

    @Override
    public void onCancle(byte state) {

    }

    @Override
    public void onConfrim(byte state) {

    }
    @Override
    public void run() {
        try {
            while( ! mDownLoadThread.isInterrupted()){
                float progress = mFlikerProgressBar.getProgress();
                progress  += 2;
                Thread.sleep(200);
                Message message = mHandler.obtainMessage();
                message.arg1 = (int) progress;
                mHandler.sendMessage(message);
                if(progress == 100){
                    break;
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
