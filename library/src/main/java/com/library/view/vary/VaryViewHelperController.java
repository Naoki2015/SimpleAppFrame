/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.library.view.vary;


import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.library.R;


/**
 * 动态显示帮助View如empty或者error
 */
public class VaryViewHelperController {

    private IVaryViewHelper helper;
    private View errorLayout;
    private View emptyLayout;

    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }

    public void showLoading(){
        View layout = helper.inflate(R.layout.loading);
        ProgressBar progressBar = (ProgressBar) layout.findViewById(R.id.loading);
        helper.showLayout(layout);
    }

    public void showError(View.OnClickListener onClickListener){
        showError("", onClickListener);
    }

    /**
     * 显示默认errorView
     * @param errorMsg
     * @param onClickListener
     */
    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        if(errorLayout == null){
            errorLayout = helper.inflate(R.layout.message);
        }

        TextView textView = (TextView) errorLayout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(errorMsg)) {
            textView.setText(errorMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_error_msg));
        }

        ImageView imageView = (ImageView) errorLayout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.drawable.ic_error);

        if(onClickListener != null){
            errorLayout.setOnClickListener(onClickListener);
        }
        helper.showLayout(errorLayout);
    }

    public void showEmpty(View.OnClickListener onClickListener){
        showEmpty("", onClickListener);
    }

    /**
     * 显示默认的emptyView
     * @param emptyMsg
     * @param onClickListener
     */
    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        if(emptyLayout == null){
            emptyLayout = helper.inflate(R.layout.message);
        }

        TextView textView = (TextView) emptyLayout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_empty_msg));
        }

        ImageView imageView = (ImageView) emptyLayout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.drawable.ic_exception);

        if(onClickListener != null){
            emptyLayout.setOnClickListener(onClickListener);
        }
        helper.showLayout(emptyLayout);
    }

    /**
     * 复原
     */
    public void restore() {
        helper.restoreView();
    }

    /**
     * 替换自定义的view
     * @param customView
     */
    public void showCustomView(View customView){
        if(customView == null){
            throw new IllegalArgumentException("customView is null...");
        }
        helper.showLayout(customView);
    }

    /**
     * 替换自定义的view
     * @param customView
     * @param targetView  被替换的view
     */
    public void showCustomView(View customView, View targetView){
        if(customView == null){
            throw new IllegalArgumentException("customView is null...");
        }
        if(targetView == null){
            throw new IllegalArgumentException("targetView is null...");
        }
        helper = new VaryViewHelper(targetView);
        helper.showLayout(customView);
    }

}
