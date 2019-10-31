package com.eaeducation.manto.vip.action;

import com.eaeducation.manto.vip.adapter.DailyPracticeAdapter;
import com.shehuan.niv.NiceImageView;

/**
 * Created by cuihao on  2019/10/24.
 */
public interface IDailyPracticeAction {
    void setAdapter(DailyPracticeAdapter adapter);

    void playAudio(String url, NiceImageView imgView);
}
