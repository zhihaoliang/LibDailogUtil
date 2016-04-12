package com.zhihaoliang.util.dialog;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;




import java.util.List;

/**
 * Created by haoliangzhi on 2015/9/10.
 */

public class MyDialog extends Dialog  {
    private DialogListener mDialogListener;

    private byte mState;

    public MyDialog(Context paramContext, DialogListener dialogListener, byte state, String content, boolean cancelable) {
        super(paramContext, R.style.Theme_Light_FullScreenDialogAct);
        super.setContentView(R.layout.common_dialog_generic);
        init(paramContext,dialogListener, state, content, cancelable);
    }

    public MyDialog(Context paramContext, DialogListener dialogListener, byte state, int contentId, boolean cancelable) {
        super(paramContext, R.style.Theme_Light_FullScreenDialogAct);
        super.setContentView(R.layout.common_dialog_generic);
        String content = paramContext.getResources().getString(contentId);
        init(paramContext,dialogListener, state, content, cancelable);
    }

    private void init(Context paramContext,DialogListener dialogListener, byte state, String content, boolean cancelable) {
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();

        localLayoutParams.width = -1;
        localLayoutParams.height = -2;
        getWindow().setAttributes(localLayoutParams);

        if (dialogListener != null) {
            findViewById(R.id.dilaog_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialogListener.onCancle(mState);
                }
            });
            findViewById(R.id.dilaog_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialogListener.onConfrim(mState);
                }
            });
        }
        mState = state;
        mDialogListener = dialogListener;
        ((TextView) findViewById(R.id.dialog_tv_message)).setText(content);
        setCancelable(cancelable);
        if(!isBackground(paramContext)){
            show();
        }
    }


    public interface DialogListener {
        public void onCancle(byte state);

        public void onConfrim(byte state);

    }

    public  boolean isBackground(Context context) {
        if(context == null){
            return true;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
}
