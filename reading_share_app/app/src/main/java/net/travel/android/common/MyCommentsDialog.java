package net.travel.android.common;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.blueberry.activity.R;
import com.common.util.BaseCallback;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * 购物车及购买按钮点击后弹出的小dialog
 * (二次封装github开源dialog组件)
 * url：
 * https://github.com/orhanobut/dialogplus
 *
 * @author
 */
public class MyCommentsDialog {
    private Context mContext;

    public MyCommentsDialog(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 创建dialog
     *
     * @param callback 回调
     */
    public DialogPlus createDialog(final BaseCallback callback) {
        return DialogPlus.newDialog(mContext)
                .setCancelable(false)
                .setContentHolder(new ViewHolder(R.layout.dialog_comments_layout))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        View contentView = dialog.getHolderView();
                        EditText editText = contentView.findViewById(R.id.et_count);

                        switch (view.getId()) {
                            case R.id.btn_close:
                                dialog.dismiss();
                                break;
                            case R.id.btn_ok:
                                callback.sendMessage(editText.getText().toString());
                                break;
                            default:
                                break;

                        }
                    }
                }).create();
    }

}
