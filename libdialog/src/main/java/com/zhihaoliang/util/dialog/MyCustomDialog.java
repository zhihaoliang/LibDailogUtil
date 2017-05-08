package com.zhihaoliang.util.dialog;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;
public class MyCustomDialog extends  Dialog {

    public MyCustomDialog(Context paramContext, String title, boolean cancelable, int layoutId) {
        super(paramContext, R.style.Theme_Light_FullScreenDialogAct);
        super.setContentView(R.layout.custom_dialog_generic);
        View contentView  = getLayoutInflater().inflate(layoutId,null);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dialog_layout_content);
        linearLayout.addView(contentView);
        init(paramContext,  title, cancelable,contentView);
    }

    public MyCustomDialog(Context paramContext,  int titleId, boolean cancelable , int layoutId) {
        super(paramContext, R.style.Theme_Light_FullScreenDialogAct);
        super.setContentView(R.layout.custom_dialog_generic);
        String title = paramContext.getResources().getString(titleId);
        View contentView  = getLayoutInflater().inflate(layoutId,null);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dialog_layout_content);
        linearLayout.addView(contentView);
        init(paramContext,  title, cancelable,contentView);
    }

    public MyCustomDialog(Context paramContext, String title, boolean cancelable, View contentView) {
        super(paramContext, R.style.Theme_Light_FullScreenDialogAct);
        super.setContentView(R.layout.custom_dialog_generic);
        init(paramContext,  title, cancelable,contentView);
    }

    public MyCustomDialog(Context paramContext, int titleId, boolean cancelable, View contentView) {
        super(paramContext, R.style.Theme_Light_FullScreenDialogAct);
        super.setContentView(R.layout.custom_dialog_generic);
        String title = paramContext.getResources().getString(titleId);
        init(paramContext,  title, cancelable,contentView);
    }

    private void init(Context paramContext,  String titleContent, boolean cancelable, View view) {
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();

        localLayoutParams.width = -1;
        localLayoutParams.height = -2;
        getWindow().setAttributes(localLayoutParams);

        ((TextView) findViewById(R.id.dialog_tv_title)).setText(titleContent);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dialog_layout_content);
        linearLayout.addView(view);

        setCancelable(cancelable);
        if (!isBackground(paramContext)) {
            show();
        }
    }

    public boolean isBackground(Context context) {
        if (context == null) {
            return true;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}

