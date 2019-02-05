package com.example.playerdemo;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//播放控制类
public class PlayHolder implements SurfaceHolder.Callback {


    static {
        System.loadLibrary("native-lib");
    }

    private String dataSource;
    private SurfaceHolder holder;
    private OnPrepareListener listener;

    /**
     * 让使用 设置播放的文件 或者 直播地址
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 设置播放显示的画布
     *
     * @param surfaceView
     */
    public void setSurfaceView(SurfaceView surfaceView) {
        if (null != holder) {
            holder.removeCallback(this);
        }
        holder = surfaceView.getHolder();
        holder.addCallback(this);
    }

    public void onError(int errorCode) {
        System.out.println("Java接到回调:" + errorCode);
    }


    public void onPrepare() {
        if (null != listener) {
            listener.onPrepare();
        }
    }

    public void setOnPrepareListener(OnPrepareListener listener) {
        this.listener = listener;
    }

    /**
     * 准备好 要播放的视频
     */
    public void prepare() {
        native_prepare(dataSource);
    }

    /**
     * 开始播放
     */
    public void start() {
        native_start();
    }

    /**
     * 停止播放
     */
    public void stop() {
        native_stop();
    }

    public void release() {
        holder.removeCallback(this);
        native_release();
    }


    //创建画布
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }


    //画布发生了变化（横竖屏切换、按了home都会回调这个函数）
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        native_setSurface(holder.getSurface());
    }

    //销毁画布 (按了home/退出应用/)
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public interface OnPrepareListener {
        void onPrepare();
    }


    native void native_prepare(String dataSource);

    native void native_start();

    native void native_stop();

    native void native_release();

    native void native_setSurface(Surface surface);
}
