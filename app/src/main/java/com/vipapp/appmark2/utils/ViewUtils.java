package com.vipapp.appmark2.utils;

import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.utils.wrapper.Res;

public class ViewUtils {

    private static ViewGroup.MarginLayoutParams getLayoutParams(View v){
        return v.getLayoutParams() == null?
                new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT):
                (ViewGroup.MarginLayoutParams) v.getLayoutParams();
    }

    private static void setSize(View view, int width, int height){
        ViewGroup.LayoutParams params = getLayoutParams(view);
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }
    private static void setMargins(View view, int left, int top, int right, int bottom){
        ViewGroup.MarginLayoutParams params = getLayoutParams(view);
        params.setMargins(left, top, right, bottom);
    }

    public static void setWidth(View view, int width){
        setSize(view, width, getLayoutParams(view).height);
    }
    public static void setHeight(View view, int height){
        setSize(view, getLayoutParams(view).width, height);
    }

    public static void setMargins(View view, int margins){
        setMargins(view, margins, margins, margins, margins);
    }
    public static void setMarginLeft(View view, int marginLeft){
        ViewGroup.MarginLayoutParams params = getLayoutParams(view);
        setMargins(view, marginLeft, params.topMargin, params.rightMargin, params.bottomMargin);
    }
    public static void setMarginTop(View view, int marginTop){
        ViewGroup.MarginLayoutParams params = getLayoutParams(view);
        setMargins(view, params.leftMargin, marginTop, params.rightMargin, params.bottomMargin);
    }
    public static void setMarginRight(View view, int marginRight){
        ViewGroup.MarginLayoutParams params = getLayoutParams(view);
        setMargins(view, params.leftMargin, params.topMargin, marginRight, params.bottomMargin);
    }
    public static void setMarginBottom(View view, int marginBottom){
        ViewGroup.MarginLayoutParams params = getLayoutParams(view);
        setMargins(view, params.leftMargin, params.topMargin, params.rightMargin, marginBottom);
    }
    public static void setMarginStart(View view, int marginStart){
        if(Res.get().getBoolean(R.bool.rtl))
            setMarginRight(view, marginStart);
        else
            setMarginLeft(view, marginStart);
    }
    public static void setMarginEnd(View view, int marginEnd){
        if(Res.get().getBoolean(R.bool.rtl))
            setMarginLeft(view, marginEnd);
        else
            setMarginRight(view, marginEnd);
    }
    public static void setMarginHorizontal(View view, int marginHorizontal){
        setMarginLeft(view, marginHorizontal);
        setMarginRight(view, marginHorizontal);
    }
    public static void setMarginVertical(View view, int marginVertical){
        setMarginTop(view, marginVertical);
        setMarginBottom(view, marginVertical);
    }

    public static void setPaddings(View view, int padding){
        view.setPadding(padding, padding, padding, padding);
    }
    public static void setPaddingLeft(View view, int paddingLeft){
        view.setPadding(paddingLeft, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }
    public static void setPaddingTop(View view, int paddingTop){
        view.setPadding(view.getPaddingLeft(), paddingTop, view.getPaddingRight(), view.getPaddingBottom());
    }
    public static void setPaddingRight(View view, int paddingRight){
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), paddingRight, view.getPaddingBottom());
    }
    public static void setPaddingBottom(View view, int paddingBottom){
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), paddingBottom);
    }
    public static void setPaddingStart(View view, int paddingStart){
        if(Res.get().getBoolean(R.bool.rtl))
            setPaddingRight(view, paddingStart);
        else
            setPaddingLeft(view, paddingStart);
    }
    public static void setPaddingEnd(View view, int paddingEnd){
        if(Res.get().getBoolean(R.bool.rtl))
            setPaddingLeft(view, paddingEnd);
        else
            setPaddingRight(view, paddingEnd);
    }
    public static void setPaddingHorizontal(View view, int paddingHorizontal){
        setPaddingLeft(view, paddingHorizontal);
        setPaddingRight(view, paddingHorizontal);
    }
    public static void setPaddingVertical(View view, int paddingVertical){
        setPaddingTop(view, paddingVertical);
        setPaddingBottom(view, paddingVertical);
    }

}
