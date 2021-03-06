package com.bm.view;

/**
 * Create by zwonb on 2018/5/30
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richer.tzjjl.R;


public class TabMyView extends LinearLayout {
    private TextView txt;
    private ImageView icon;
    private ImageView red;
    private int resIdN;
    private int resIdP;

    private static  int colorN = 0 ;
    private static  int colorP = 0 ;

    public TabMyView(Context context) {
        super(context);
        init();
    }

    public TabMyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabMyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TabMyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.tab_my_view, this);
        this.txt = ((TextView) findViewById(R.id.tab_my_view_txt));
        this.icon = ((ImageView) findViewById(R.id.tab_my_view_icon));
        this.red = ((ImageView) findViewById(R.id.tab_my_view_red));

        colorN = Color.parseColor("#a2a2a2");
        colorP = Color.parseColor("#a2953e");

        this.red.setVisibility(INVISIBLE);
    }

    /**
     * @param txt    设置文本
     * @param resIdN 未选中的图片
     * @param resIdP 选中的图片
     */
    public void setValue(String txt, int resIdN, int resIdP) {
        this.resIdN = resIdN;
        this.resIdP = resIdP;
        this.txt.setText(txt);
        this.icon.setImageResource(resIdN);
    }

    public void setColor(int defuColor, int colr) {
        colorN = defuColor;
        colorP = colr;
    }

    public void setSelect(boolean isSelect) {
        this.icon.setImageResource(isSelect ? resIdP : resIdN);
        if (isSelect)
            this.txt.setTextColor(colorP);
        else
            this.txt.setTextColor(colorN);
        // showRed(isSelect);
    }


    //显示红点
    public void showRed(boolean isShow) {
        this.red.setVisibility(isShow ? VISIBLE : INVISIBLE);
    }

}