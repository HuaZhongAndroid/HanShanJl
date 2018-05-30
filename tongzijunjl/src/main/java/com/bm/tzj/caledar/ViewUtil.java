package com.bm.tzj.caledar;

//辅助类

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author wangcccong
 * @version 1.140122 create at：2014-02-26
 */
public class ViewUtil {
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * string -> date
	 */
	public static Date str2Date(String str, String format) {
		if (str == null || str.length() == 0) {
			return null;
		}
		if (format == null || format.length() == 0) {
			format = FORMAT;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * 获取屏幕的宽度
	 * 
	 * @param context
	 * @return
	 */
	public int getScreenWidth(Context context) {
		Resources res = context.getResources();
		return res.getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public int getScreenHeight(Context context) {
		Resources res = context.getResources();
		return res.getDisplayMetrics().heightPixels;
	}

	/**
	 * 描述：根据分辨率获得字体大小.
	 * 
	 * @param screenWidth
	 *            the screen width
	 * @param screenHeight
	 *            the screen height
	 * @param textSize
	 *            the text size
	 * @return the int
	 */
	public static int resizeTextSize(int screenWidth, int screenHeight,
			int textSize) {
		float ratio = 1;
		try {
			float ratioWidth = (float) screenWidth / 480;
			float ratioHeight = (float) screenHeight / 800;
			ratio = Math.min(ratioWidth, ratioHeight);
		} catch (Exception e) {
		}
		return Math.round(textSize * ratio);
	}

	/**
	 * 
	 * 描述：dip转换为px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 * @throws
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return Math.round(dipValue * scale);
	}

	/**
	 * 
	 * 描述：px转换为dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 * @throws
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return Math.round(pxValue / scale);
	}

	/**
	 * 
	 * 描述：px转换为sp
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 * @throws
	 */
	public static int px2sp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return Math.round(pxValue / scale);
	}

	/**
	 * 
	 * 描述：sp转换为px
	 * 
	 * @param context
	 * @param spValue
	 * @return
	 * @throws
	 */
	public static int sp2px(Context context, float spValue) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return Math.round(spValue * scale);
	}
}
