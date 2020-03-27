package com.vipapp.appmark2.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.inputmethod.InputMethodManager;

import com.vipapp.appmark2.item.SpanItem;
import com.vipapp.appmark2.util.wrapper.Service;
import com.vipapp.appmark2.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.vipapp.appmark2.util.Const.IMPORTS_REGEX;
import static com.vipapp.appmark2.util.Const.WORD_SPLITTER;

@SuppressWarnings("WeakerAccess")
public class TextUtils {

    public static String replaceLast(String text, String regex, String replacement){
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static boolean hasSpan(Spannable spannable, SpanItem spanItem){
        ArrayList<SpanItem> spans = new ArrayList<>();
        for(ForegroundColorSpan span: spannable.getSpans(0, spannable.length(), ForegroundColorSpan.class)){
            spans.add(new SpanItem(spannable.getSpanStart(span), spannable.getSpanEnd(span)));
        }
        return ArrayUtils.any(spans, curSpanItem -> curSpanItem.end_index == spanItem.end_index && curSpanItem.start_index == spanItem.start_index);
    }

    public static Spannable applyPatternUI(Pattern pattern, String color, Spannable text){
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            final ForegroundColorSpan span = getForegroundSpan(color);
            SpanItem item = new SpanItem(matcher.start(), matcher.end());
            if(!hasSpan(text, item)) {
                clearForegroundSpans(text, matcher.start(), matcher.end());
                text.setSpan(span, matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return text;
    }

    private static ForegroundColorSpan getForegroundSpan(String color){
        return new ForegroundColorSpan(Color.parseColor(color));
    }

    public static String getCurrentWordWithOffset(EditText editText, int offset){
        return getWordAtPosition(Objects.requireNonNull(editText.getText()).toString(), editText.getSelectionStart() + offset);
    }

    public static String getCurrentWord(EditText editText){
        return getCurrentWordWithOffset(editText, 0);
    }

    public static String getWordAtPosition(String string, int position){
        int length = 0;

        for(String currentWord: string.split(WORD_SPLITTER)) {
            length = length + currentWord.length();
            if(length >= position) {
                return currentWord;
            }
            length += 1;
        }

        return "";
    }

    public static void deleteWordAtPosition(Editable editable, int pos){
        int start = pos;
        int end = pos;
        while(start > 0 && !Character.toString(editable.charAt(start)).matches(WORD_SPLITTER)){
            start--;
        }
        while(end < editable.length() && !Character.toString(editable.charAt(end)).matches(WORD_SPLITTER)){
            end++;
        }
        editable.delete(pos == 0? 0: start + 1, end);
    }

    public static String insert(String destination, int position, String toInsert){
        return destination.substring(0, position) + toInsert + destination.substring(position);
    }

    @Nullable
    public static List<String> parseImports(String text){
        try{
            text = text.replaceAll(IMPORTS_REGEX, "");
            return Arrays.asList(text.split("\n"));
        } catch (Exception e){
            return null;
        }
    }

    public static void clearForegroundSpans(Spannable spannable, int start_pos, int end_pos){
        clearSpans(spannable, ForegroundColorSpan.class, start_pos, end_pos);
    }

    public static void clearSpans(Spannable spannable, Class<?> spanClass, int start_pos, int end_pos){
        for (Object span: spannable.getSpans(start_pos, end_pos, spanClass)) {
            spannable.removeSpan(span);
        }
    }

    public static void clearAllSpansExclude(@NonNull Spannable spannable, int start_pos, int end_pos){
        try{
            for(ForegroundColorSpan span: spannable.getSpans(0, spannable.length(), ForegroundColorSpan.class)){
                int span_start = spannable.getSpanStart(span);
                int span_end = spannable.getSpanEnd(span);
                if((span_start > start_pos && span_end > start_pos) ||
                        (span_start < end_pos && span_end < end_pos)) {
                    spannable.removeSpan(span);
                }
            }
        } catch(Exception ignored){}
    }

    public static String multipleString(String text, int multiplier){
        return new String(new char[multiplier]).replace("\0", text);
    }

    public static void copyToClipboard(String text){
        ((ClipboardManager)ContextUtils.getSystemService(CLIPBOARD_SERVICE))
                .setPrimaryClip(ClipData.newPlainText("clipboard", text));
    }

    public static void forceShowKeyboard(EditText editText){
        InputMethodManager imm = (InputMethodManager) Service.get(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

}
