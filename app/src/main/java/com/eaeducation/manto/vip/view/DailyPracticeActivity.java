package com.eaeducation.manto.vip.view;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eaeducation.manto.R;
import com.eaeducation.manto.base.BaseActivity;
import com.eaeducation.manto.utils.status.StatusBarUtil;
import com.eaeducation.manto.vip.action.IDailyPracticeAction;
import com.eaeducation.manto.vip.adapter.DailyPracticeAdapter;
import com.eaeducation.manto.vip.presenter.DailyPracticePresenter;
import com.shehuan.niv.NiceImageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class DailyPracticeActivity extends BaseActivity implements IDailyPracticeAction {

    @BindView(R.id.rcy_practice_list)
    RecyclerView mRcyPractice;
    @BindView(R.id.title)
    TextView mTitle;

    private DailyPracticePresenter mPresenter;
    private MediaPlayer mMediaPlayer;
    private AnimationDrawable mAnimationDrawable;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_daily_practice;
    }

    @Override
    protected void initView() {
        setTitle(R.string.dialy_practice_title);
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);//设置状态栏透明
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        //设置RecyclerView管理器
        mRcyPractice.setLayoutManager(new LinearLayoutManager(this));
        mPresenter = new DailyPracticePresenter(this, this);
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    protected void initData() {
        mRcyPractice.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                JZVideoPlayerStandard.onChildViewAttachedToWindow(view, R.id.jz_daily_part1_video);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JZVideoPlayerStandard.onChildViewDetachedFromWindow(view);
            }
        });
    }

    @Override
    public void setAdapter(DailyPracticeAdapter adapter) {
        mRcyPractice.setAdapter(adapter);
    }

    @Override
    public void playAudio(String url, NiceImageView imgView) {
        if (!TextUtils.isEmpty(url)) {
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mAnimationDrawable.stop();
            } else {
                try {
                    mMediaPlayer = new MediaPlayer();
                    try {
                        mMediaPlayer.setDataSource(url);
                        mMediaPlayer.prepareAsync();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        mMediaPlayer.stop();
                        mMediaPlayer = null;
                        mMediaPlayer = new MediaPlayer();
                    }
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mAnimationDrawable = (AnimationDrawable) imgView.getDrawable();
                            mAnimationDrawable.stop();
                            imgView.setImageResource(R.drawable.audio_speaking);
                            try {
                                if (mMediaPlayer != null){
                                    mMediaPlayer.stop();
                                }
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            }
                            mMediaPlayer = null;
                        }
                    });
                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mMediaPlayer.start();
                            mAnimationDrawable = (AnimationDrawable) imgView.getDrawable();
                            mAnimationDrawable.start();
                        }
                    });
                    mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
//                            LogUtil.d("swl", "mMediaPlayer onError");
                            return false;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mMediaPlayer = null;
        }
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
    }

    @OnClick({R.id.back, R.id.issue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.issue:
                break;
        }
    }

}