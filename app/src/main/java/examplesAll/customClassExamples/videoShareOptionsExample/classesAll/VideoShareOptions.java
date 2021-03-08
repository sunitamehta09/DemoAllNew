package examplesAll.customClassExamples.videoShareOptionsExample.classesAll;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.self.demoaall.R;
import com.self.demoaall.databinding.VideoshareOptionsBinding;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import examplesAll.customClassExamples.videoShareOptionsExample.callbacksAll.SocialOptionsClickListener;

public class VideoShareOptions extends FrameLayout {
    private Context mContext;
    private int mPosition;
    public String playlistName, videoName, duration, videoUrl;
    // ShareListener shareListener = null;
    private int currentIndex = 0;
    private ProgressDialog dialog;
    private Dialog dialog1;
    private boolean isLive = false, isTextMode = false, isPreview = false;
    private String pageSource = "", source = "", catalogType = "", widgetType = "";
    private int adVideoIndex = 0;
    private boolean isFragment1 = false;
    VideoshareOptionsBinding viewBinding;
    SocialOptionsClickListener optionsClickListener;

    public VideoShareOptions(Context context) {
        this(context, null);
    }

    public VideoShareOptions(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoShareOptions(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.videoshare_options, this, true);

        viewBinding.shareByWhatapp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    optionsClickListener.onOptionsClick(isFragment1, "WhatAppClicked");
            }
        });

        viewBinding.shareByFb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsClickListener.onOptionsClick(isFragment1, "FbClicked");
            }
        });

        viewBinding.shareByGMail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsClickListener.onOptionsClick(isFragment1, "GmailClicked");
            }
        });

        viewBinding.shareByTwitter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsClickListener.onOptionsClick(isFragment1, "TwitterClicked");
            }
        });

        viewBinding.shareOptions.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                optionsClickListener.onOptionsClick(isFragment1, "SharedClicked");
            }
        });
    }

    public boolean isTextMode() {
        return isTextMode;
    }

    public void setTextMode(boolean textMode) {
        isTextMode = textMode;
        if (isTextMode)
            viewBinding.shareByGMail.setColorFilter(ContextCompat.getColor(mContext, R.color.colorDarkPink),
                    android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public void enableShareKit(boolean isEnable) {
        viewBinding.shareByWhatapp.setEnabled(isEnable);
        viewBinding.shareByTwitter.setEnabled(isEnable);
        viewBinding.shareByGMail.setEnabled(isEnable);
        viewBinding.shareByFb.setEnabled(isEnable);
        viewBinding.shareOptions.setEnabled(isEnable);
    }

    public void setIsFragment1(boolean isFragment1) {
        this.isFragment1 = isFragment1;
        if (isFragment1)
            viewBinding.shareOptions.setVisibility(VISIBLE);
        else
            viewBinding.shareOptions.setVisibility(GONE);
    }

    public void setOptionsClickListener(SocialOptionsClickListener optionsClickListener) {
        this.optionsClickListener = optionsClickListener;
    }
}