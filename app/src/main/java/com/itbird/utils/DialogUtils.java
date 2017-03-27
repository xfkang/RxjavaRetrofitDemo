package com.itbird.utils;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itbird.R;

/**
 * 对话框创建
 * Created by itbird on 2017/3/6
 */
public final class DialogUtils {

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg, boolean isCancle) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loading, null);
        TextView title = (TextView) view.findViewById(R.id.id_dialog_loading_msg);
        title.setText(msg);
        Dialog dialog = new Dialog(context, R.style.dialog);
        dialog.setCanceledOnTouchOutside(isCancle);// 设置点击屏幕
        dialog.setContentView(view);
        return dialog;
    }
}
