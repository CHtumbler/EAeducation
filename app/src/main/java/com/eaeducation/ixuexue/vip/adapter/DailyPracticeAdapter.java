package com.eaeducation.ixuexue.vip.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.eaeducation.ixuexue.R;
import com.shehuan.niv.NiceImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class DailyPracticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int DAILY_VIDEO = 0;//视频回顾
    public static final int DAILY_CLASS_PERFORMANCE = 1;//课堂表现
    public static final int DAILY_WORDS = 2;//单词
    public static final int DAILY_SENTENCE = 3;//句子
    private Context mContext;
    private VoiceClickListener mListener;

    public DailyPracticeAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return DAILY_VIDEO;
        } else if (position == 1) {
            return DAILY_CLASS_PERFORMANCE;
        } else if (position == 2) {
            return DAILY_WORDS;
        } else {
            return DAILY_SENTENCE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case DAILY_VIDEO:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_part1_video, parent, false);
                holder = new VideoViewHolder(view);
                break;
            case DAILY_CLASS_PERFORMANCE:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_part2_class_performance, parent, false);
                holder = new ClassPerformanceViewHolder(view);
                break;
            case DAILY_WORDS:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_part3_words, parent, false);
                holder = new WordsViewHolder(view);
                break;
            case DAILY_SENTENCE:
                view = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_part4_sentence, parent, false);
                holder = new SentenceViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case DAILY_VIDEO:
                VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
                videoViewHolder.mStandard.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                        , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
                videoViewHolder.mStandard.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
                //设置全屏和横屏播放
                videoViewHolder.mStandard.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                videoViewHolder.mStandard.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                break;
            case DAILY_CLASS_PERFORMANCE:
                ClassPerformanceViewHolder performanceViewHolder = (ClassPerformanceViewHolder) holder;

                break;
            case DAILY_WORDS:
                WordsViewHolder wordsViewHolder = (WordsViewHolder) holder;
                wordsViewHolder.mRcyWords.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                List<String> mWordsList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    mWordsList.add("");
                }
                CommonAdapter commonAdapter = new CommonAdapter(mContext, R.layout.layout_daily_part3_words_item, mWordsList) {
                    @Override
                    protected void convert(ViewHolder holder, Object o, int position) {
                        NiceImageView mNiv = holder.getView(R.id.niv_part3_word_voice);
                        mNiv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mListener.voiceClickListener("https://m10.music.126.net/20191026170405/7abec2f3a3259c4fb69e11c65e1a128e/yyaac/045b/0e0f/0e52/31fc08122e9e8fa87c279897972e6006.m4a", mNiv);
                            }
                        });
                    }
                };
                wordsViewHolder.mRcyWords.setAdapter(commonAdapter);
                break;
            case DAILY_SENTENCE:
                SentenceViewHolder sentenceViewHolder = (SentenceViewHolder) holder;
                sentenceViewHolder.mRcy.setLayoutManager(new LinearLayoutManager(mContext));
                sentenceViewHolder.mRcy.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
                List<String> mSentenceList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    mSentenceList.add("");
                }
                sentenceViewHolder.mRcy.setAdapter(new CommonAdapter(mContext, R.layout.layout_daily_part4_sentence_item, mSentenceList) {
                    @Override
                    protected void convert(ViewHolder holder, Object o, int position) {

                    }
                });
                break;

        }


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public interface VoiceClickListener{
        void voiceClickListener(String voiceUrl, NiceImageView imgView);
    }

    public void setVoiceClickListener(VoiceClickListener listener){
        this.mListener = listener;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private JZVideoPlayerStandard mStandard;

        public VideoViewHolder(View itemView) {
            super(itemView);
            mStandard = itemView.findViewById(R.id.jz_daily_part1_video);
        }
    }

    public class ClassPerformanceViewHolder extends RecyclerView.ViewHolder {

        public ClassPerformanceViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class WordsViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mRcyWords;

        public WordsViewHolder(View itemView) {
            super(itemView);
            mRcyWords = itemView.findViewById(R.id.rcy_words_list);
        }
    }

    public class SentenceViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mRcy;

        public SentenceViewHolder(View itemView) {
            super(itemView);
            mRcy = itemView.findViewById(R.id.rcy_sentence_list);
        }
    }
}
