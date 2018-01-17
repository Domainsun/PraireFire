package com.praire.fire.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.praire.fire.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *  解决android textview自动换行排版问题
 * @author Administrator
 */
public class TextViewUtils {
    /*** 半角转换为全角
     *将textview中的字符全角化。即将所有的数字、字母及标点全部转为全角字符，
     * 使它们与汉字同占两个字节，这样就可以避免由于占位导致的排版混乱问题了。
     * 半角转为全角的代码如下，只需调用即可
     * @param input
     * @return
     */
    public static String toABC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /** * 去除特殊字符或将所有中文标号替换为英文标号
     * 利用正则表达式将所有特殊字符过滤，或利用replaceAll（）将中文标号替换为英文标号。
     * 转化之后，则可解决排版混乱问题。
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * 修改字体颜色
     * @param tv 要修改的控件
     * @param start 改变颜色开始的位置
     * @param end 改变颜色结束的位置
     * @param hintcolor 原来默认的颜色
     * @param tocolor 要改变成的颜色
     */
    @SuppressWarnings("deprecation")
    public static void changeFontColor(Context context,TextView tv, int start, int end, int hintcolor, int tocolor) {
        SpannableStringBuilder builder = new SpannableStringBuilder(tv.getText().toString());
        ForegroundColorSpan numberColor = new ForegroundColorSpan(context.getResources().getColor(tocolor));
        ForegroundColorSpan hintColor = new ForegroundColorSpan(context.getResources().getColor(hintcolor));
        builder.setSpan(hintColor, 0, start,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(numberColor, start, end,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tv.setText(builder);
    }

    /**
     * textview 同时改变字符串中部分字体的颜色与大小
     * @param tv
     * @param start 开始的位置
     * @param end 结束的位置
     * @param color 颜色（非正常颜色值）
     * @param size 字号(非sp为单位，所以值要设大点) 为0 即采用原始的正常的 size大小
     */
    public static void changeFontSize(TextView tv, int start, int end, int color, int size) {
        ColorStateList colors = ColorStateList.valueOf(color);
        SpannableStringBuilder spanBuilder = new SpannableStringBuilder(tv.getText().toString());
        //style 为0 即是正常的，还有Typeface.BOLD(粗体) Typeface.ITALIC(斜体)等
        spanBuilder.setSpan(new TextAppearanceSpan(null, 0, size, colors, null), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(spanBuilder);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
