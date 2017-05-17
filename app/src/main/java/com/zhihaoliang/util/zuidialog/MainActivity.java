package com.zhihaoliang.util.zuidialog;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhihaoliang.util.dialog.ActionSheetDialog;
import com.zhihaoliang.util.dialog.AlertDialog;
import com.zhihaoliang.util.dialog.MyCustomDialog;
import com.zhihaoliang.util.zuidialog.wigets.FlikerProgressBar;


public class MainActivity extends AppCompatActivity implements  Runnable{

    private FlikerProgressBar mFlikerProgressBar;

    private Thread mDownLoadThread;

    private  MyCustomDialog mMyCustomDialog;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mFlikerProgressBar.setProgress(msg.arg1);
            if(msg.arg1 == 100){
                mFlikerProgressBar.finishLoad();
                mMyCustomDialog.dismiss();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                new ActionSheetDialog(MainActivity.this)
                        .builder()
                        .setTitle("清空消息列表后，聊天记录依然保留，确定要清空消息列表？")
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("清空消息列表", ActionSheetDialog.SheetItemColor.Red
                                , new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                }).show();
                break;
            case R.id.btn2:
                new ActionSheetDialog(MainActivity.this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("发送给好友",
                                ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                })
                        .addSheetItem("转载到空间相册",
                                ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                })
                        .addSheetItem("上传到群相册",
                                ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                })
                        .addSheetItem("保存到手机",
                                ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                }).show();
                break;
            case R.id.btn3:
                new ActionSheetDialog(MainActivity.this)
                        .builder()
                        .setTitle("好友列表")
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem("删除好友", ActionSheetDialog.SheetItemColor.Red
                                , new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                })
                        .addSheetItem("增加好友", ActionSheetDialog.SheetItemColor.Blue
                                , new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                })
                        .addSheetItem("备注", ActionSheetDialog.SheetItemColor.Blue
                                , new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //填写事件
                                    }
                                }).show();
                break;
            case R.id.btn4:
                new AlertDialog(MainActivity.this)
                        .builder()
                        .setTitle("退出当前帐号")
                        .setMsg("再连续登陆天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
                        .setPositiveButton("确认退出", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //填写事件
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //填写事件
                            }
                        }).show();
                break;
            case R.id.btn5:
                new AlertDialog(MainActivity.this)
                        .builder()
                        .setTitle("错误信息")
                        .setMsg("你的手机sd卡出现问题，建议删除不需要的文件，否则收不到图片和视频等打文件")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //填写事件
                            }
                        }).show();
                break;
        }
    }

    public void onDowload(View view){
        mFlikerProgressBar = (FlikerProgressBar) getLayoutInflater().inflate(R.layout.layout_view,null);
        mMyCustomDialog =    new MyCustomDialog(this, "版本升级，请稍后",false,mFlikerProgressBar);
        downLoad();
    }

    private void downLoad() {
        mDownLoadThread = new Thread(this);
        mDownLoadThread.start();
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
