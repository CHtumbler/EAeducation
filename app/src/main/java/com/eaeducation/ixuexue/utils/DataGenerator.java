package com.eaeducation.ixuexue.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.eaeducation.ixuexue.R;
import com.eaeducation.ixuexue.information.InformationFragment;
import com.eaeducation.ixuexue.openclass.OpenClassFragment;
import com.eaeducation.ixuexue.institute.InstituteFragment;
import com.eaeducation.ixuexue.me.MeFragment;

/**
 * Created by cuihao on 2019/10/10 0010.
 */
public class DataGenerator {
    public static final int[] mTabRes = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    public static final int[] mTabResPressed = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    public static final int[] mTabTitle = new int[]{R.string.main_tab_information, R.string.main_tab_open_class, R.string.main_tab_institute, R.string.main_tab_me};

    public static Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[4];
        fragments[0] = new InformationFragment();
        fragments[1] = new OpenClassFragment();
        fragments[2] = new InstituteFragment();
        fragments[3] = new MeFragment();
        return fragments;
    }

    /**
     * 获取Tab 显示的内容
     *
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_tablayout, null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
