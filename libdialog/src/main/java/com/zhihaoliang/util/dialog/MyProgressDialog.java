package com.zhihaoliang.util.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * 联网界面
 * 
 * @author haoliang
 */
public class MyProgressDialog extends Dialog  {

    private LayoutInflater mLayoutInflater;
    private View mView;
    private TextView msgTextView;
    private int mOffsetY = 0;

    private AnimationDrawable mAnimationDrawable;
    private ImageView mTipsProgerss = null;

    private boolean mCanCancel;
    private Cancle mCancle;

    public MyProgressDialog(Context paramContext) {
        this(paramContext, 0);
    }

    public MyProgressDialog(Context paramContext, int paramInt) {
        super(paramContext, R.style.qzone_qZoneInputDialog);
        mLayoutInflater = LayoutInflater.from(paramContext);

        mView = mLayoutInflater.inflate(R.layout.dialog_progress, null);
        msgTextView = ((TextView)mView.findViewById(R.id.msgTextView));
        mOffsetY = paramInt;

        mTipsProgerss = ((ImageView)mView.findViewById(R.id.tipsprogerss_show));
        mAnimationDrawable = ((AnimationDrawable)mTipsProgerss.getDrawable());
    }

    public void setOffsetY(int offsetY) {
        mOffsetY = offsetY;
    }

    public void setMsgText(String paramString) {
        msgTextView.setText(paramString);
    }

    public void setMsgText(int paramInt) {
        msgTextView.setText(paramInt);
    }

    public void setProgersStart(boolean paramBoolean) {
        if (mTipsProgerss != null) {
            if (paramBoolean) {
                if (!mAnimationDrawable.isRunning()) {
                    mAnimationDrawable.start();
                }
                mTipsProgerss.setVisibility(View.VISIBLE);
            } else {
                if (mAnimationDrawable.isRunning()) {
                    mAnimationDrawable.stop();
                }
                mTipsProgerss.setVisibility(View.GONE);
            }
        }
    }

    public void setCanCancel(boolean canCancle) {
        mCanCancel = canCancle;
    }

    public boolean getCanCancel() {
        return mCanCancel;
    }

    public void setCancle(Cancle cancle) {
        mCancle = cancle;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        Window localWindow = getWindow();
        localWindow.setContentView(mView);
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        localLayoutParams.width = -1;
        localLayoutParams.height = -2;
        localLayoutParams.gravity = 48;
        localLayoutParams.y += mOffsetY;
        localWindow.setAttributes(localLayoutParams);
        setCanceledOnTouchOutside(false);
    }

    protected void onStop() {
        if (mAnimationDrawable.isRunning())
            mAnimationDrawable.stop();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (mCanCancel) {
            if (mCancle != null) {
                mCancle.cancleOprate();
                super.onBackPressed();
            }
        }
    }

    public interface Cancle {
        void cancleOprate();
    }
}
