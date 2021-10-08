package com.rubin.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rubin.weiv.R;

/**
 * Description: TODO<支付密码View>
 * Author: Zuobb
 * Date: 2021/10/08 15:53
 */
public class PayPwdView extends RelativeLayout {
    private EditText editText;
    private TextView[] textViews;
    private static int MAX = 6;
    private String inputContent;

    public PayPwdView(Context context) {
        this(context, null);
    }

    public PayPwdView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayPwdView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_pay_pwd, this);

        textViews = new TextView[MAX];
        textViews[0] = (TextView) findViewById(R.id.tv_0);
        textViews[1] = (TextView) findViewById(R.id.tv_1);
        textViews[2] = (TextView) findViewById(R.id.tv_2);
        textViews[3] = (TextView) findViewById(R.id.tv_3);
        textViews[4] = (TextView) findViewById(R.id.tv_4);
        textViews[5] = (TextView) findViewById(R.id.tv_5);

        editText = (EditText) findViewById(R.id.edit_text_view);

        editText.setCursorVisible(false);//隐藏光标
        setEditTextListener();
    }

    private void setEditTextListener() {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = editText.getText().toString();

                if (inputCompleteListener != null) {
                    if (inputContent.length() >= MAX) {
                        inputCompleteListener.inputComplete();
                    } else {
                        inputCompleteListener.invalidContent();
                    }
                }

                for (int i = 0; i < MAX; i++) {
                    if (i < inputContent.length()) {
                        textViews[i].setText("●");
                    } else {
                        textViews[i].setText("");
                    }
                }
            }
        });
    }

    /**
     * 清空密码
     */
    public void clearPwd() {
        editText.setText("");
    }


    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {

        void inputComplete();

        void invalidContent();
    }

    public String getEditContent() {
        return inputContent;
    }

}
