package com.ibm.watson.developer_cloud.android.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class StartChat extends FrameLayout {

    private EditText messageEditText;
    private ImageButton sendButton;
    private Context context;
    private boolean isAutoClearEnabled;
    private boolean isSoftInputHidden;
    private String messageBoxHint = null;


    public StartChat(Context context) {
        this(context, null);
    }

    public StartChat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StartChat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initializeViews(context, attrs);
    }

    private void initializeViews(Context context, AttributeSet attrs) {

        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.chatbar);

        messageBoxHint = typedArray
                .getString(R.styleable.chatbar_cb_messageBoxHint);

        isAutoClearEnabled = typedArray
                .getBoolean(R.styleable.chatbar_cb_isTextCleanerEnabled, true);

        isSoftInputHidden = typedArray
                .getBoolean(R.styleable.chatbar_cb_isSoftInputHidden, false);

        typedArray.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.start_chat, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        messageEditText = this.findViewById(R.id.messageEditText);
        sendButton = this.findViewById(R.id.sendButton);
        sendButton.setImageResource(R.drawable.ic_send_black_24dp);
        if (messageBoxHint != null) messageEditText.setHint(messageBoxHint);
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void setSendClickListener(final OnClickListener listener) {
        sendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onClick(view);

                if (isSoftInputHidden) {
                    hideSoftInput();
                }

                if (isAutoClearEnabled) {
                    messageEditText.setText("");
                }

            }
        });
    }

    public String getMessageText() {
        if (messageEditText.getText() != null) {
            return messageEditText.getText().toString();
        } else {
            return "";
        }
    }

    public void hideSoftInput() {
        View view = ((Activity) context).getCurrentFocus();
        if (view == null) view = new View((context));
        InputMethodManager imm = (InputMethodManager) (context).getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
