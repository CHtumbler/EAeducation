package com.eaeducation.ixuexue.vip.action;

import com.eaeducation.ixuexue.vip.adapter.DailyPracticeAdapter;
import com.shehuan.niv.NiceImageView;

/**
 * Created by cuihao on  2019/10/24.
 */
public interface IDailyPracticeAction {
    void setAdapter(DailyPracticeAdapter adapter);

    void playAudio(String url, NiceImageView imgView);
}
