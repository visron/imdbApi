package com.davis.imdbsample.util;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import android.widget.TextView;

public class SuperScriptUtil {
    public static String getSuperScriptText(TextView tv, String numberToConvert){
        if (numberToConvert==null){
            tv.setVisibility(View.GONE);
            return "";
        }
        if (numberToConvert.trim().isEmpty()){
            tv.setVisibility(View.GONE);
            return "";

        }
        if (numberToConvert.length()==1){
            numberToConvert+=".0";
        }
        if (numberToConvert.length()>3) {
            numberToConvert = numberToConvert.substring(0,1);
        }
        String finalval = numberToConvert.charAt(0)+"<sup>"+numberToConvert.substring(1,3)+"</sup>";
        //return String.valueOf(Html.fromHtml(finalval));
        numberToConvert = numberToConvert.charAt(0)+"."+numberToConvert.charAt(1);
        SpannableStringBuilder cs = new SpannableStringBuilder("X3 + X2");
        cs = new SpannableStringBuilder(numberToConvert);

        cs.setSpan(new SuperscriptSpan(), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        cs.setSpan(new RelativeSizeSpan(0.75f), 1, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
         tv.setText(Html.fromHtml(finalval));
//         tv.setText(cs);
        return cs.toString();
    }
}
