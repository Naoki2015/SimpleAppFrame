package com.library.view.vary;

import android.view.View;

/**
 * Created by yiwei on 16/3/31.
 */
public class VaryViewHelperConfig {
    private View.OnClickListener errorListener;
    private View.OnClickListener emptyListener;

    private View targetView;
    private View errorView;
    private View emptyView;

    public View.OnClickListener getErrorListener() {
        return errorListener;
    }

    public void setErrorListener(View.OnClickListener errorListener) {
        this.errorListener = errorListener;
    }

    public View.OnClickListener getEmptyListener() {
        return emptyListener;
    }

    public void setEmptyListener(View.OnClickListener emptyListener) {
        this.emptyListener = emptyListener;
    }

    public View getTargetView() {
        return targetView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
    }

    public View getErrorView() {
        return errorView;
    }

    public void setErrorView(View errorView) {
        this.errorView = errorView;
    }

    public View getEmptyView() {
        return emptyView;
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public static class Builder{
        private View.OnClickListener errorListener;
        private View.OnClickListener emptyListener;
        private View targetView;
        private View errorView;
        private View emptyView;


        public Builder(){

        }

        public Builder setTargerView(View targetView){
            this.targetView = targetView;
            return this;
        }

        public Builder setErrorListener(View.OnClickListener errorListener){
            this.errorListener = errorListener;
            return this;
        }

        public Builder setEmptyListener(View.OnClickListener emptyListener){
            this.emptyListener = emptyListener;
            return this;
        }

        public Builder setEmptyView(View emptyView){
            this.emptyView = emptyView;
            return this;
        }

        public Builder setErrorView(View errorView){
            this.errorView = errorView;
            return this;
        }


        public VaryViewHelperConfig build(){
            VaryViewHelperConfig config = new VaryViewHelperConfig();
            config.setTargetView(targetView);
            config.setEmptyView(emptyView);
            config.setErrorView(errorView);
            config.setEmptyListener(emptyListener);
            config.setErrorListener(errorListener);
            return config;
        }
    }
}
