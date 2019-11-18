package com.vipapp.appmark2.widget;

import android.content.Context;
import android.graphics.Canvas;

import androidx.annotation.Nullable;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.service.autofill.FieldClassification;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callbacks.Mapper;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.DisplayUtils;
import com.vipapp.appmark2.utils.MathUtils;
import com.vipapp.appmark2.utils.TextUtils;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.Time;
import com.vipapp.appmark2.utils.Toast;
import com.vipapp.appmark2.utils.UndoRedoUtils;

import java.util.ArrayList;
import java.util.Objects;

import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static com.vipapp.appmark2.utils.Const.CURRENT_LINE_COLOR;
import static com.vipapp.appmark2.utils.Const.DISTANCE_TO_ZOOM;
import static com.vipapp.appmark2.utils.Const.HIGHLIGHT_COUNTDOWN;
import static com.vipapp.appmark2.utils.Const.HIGHLIGHT_DEBUG;
import static com.vipapp.appmark2.utils.Const.LINE_NUMBER_COLOR;
import static com.vipapp.appmark2.utils.Const.MAX_LINES_IN_TEXT_EDITOR;
import static com.vipapp.appmark2.utils.Const.MAX_MAIN_EDITOR_FONT_SIZE;
import static com.vipapp.appmark2.utils.Const.MIN_MAIN_EDITOR_FONT_SIZE;
import static com.vipapp.appmark2.utils.Const.TEXT_SIZE_STEP;
import static com.vipapp.appmark2.utils.Const.back_symbol;

public class CodeText extends EditText {

    UndoRedoUtils undoRedo;

    long time_edited = 0;

    private int language = Const.JAVA_LANGUAGE;

    boolean pasting_second = false;

    ArrayList<Mapper<Spannable, CharSequence>> highlights = new ArrayList<Mapper<Spannable, CharSequence>>(){{
        // TEXT HIGHLIGHT
        add(spannable -> spannable);
        // JAVA HIGHLIGHT
        add(spannable -> TextUtils.applyPatternUI(Const.COMMENTS_MULTI_LINE, Const.COMMENTS_COLOR,
                        TextUtils.applyPatternUI(Const.COMMENTS_SINGLE_LINE, Const.COMMENTS_COLOR,
                                TextUtils.applyPatternUI(Const.STRING_PATTERN, Const.STRING_COLOR,
                                        TextUtils.applyPatternUI(Const.INT_PATTERN, Const.INT_COLOR,
                                                TextUtils.applyPatternUI(Const.JAVA_KEYWORDS_PATTERN, Const.KEYWORDS_COLOR,
                                                        TextUtils.applyPatternUI(Const.IMPORT_NOT_IMPORTANT, Const.COMMENTS_COLOR,
                                                                TextUtils.applyPatternUI(Const.ANNOTATIONS, Const.ANNOTATIONS_COLOR, spannable))))))));
        // JSON HIGHLIGHT
        add(spannable -> TextUtils.applyPatternUI(Const.STRING_PATTERN, Const.STRING_COLOR,
                        TextUtils.applyPatternUI(Const.INT_PATTERN, Const.INT_COLOR, spannable)));
        // XML HIGHLIGHT
        add(spannable -> TextUtils.applyPatternUI(Const.XML_COMMENTS, Const.COMMENTS_COLOR,
                        TextUtils.applyPatternUI(Const.STRING_PATTERN, Const.STRING_COLOR,
                                TextUtils.applyPatternUI(Const.XML_START_ELEMENT, Const.KEYWORDS_COLOR,
                                        TextUtils.applyPatternUI(Const.XML_END_ELEMENT, Const.KEYWORDS_COLOR,
                                            TextUtils.applyPatternUI(Const.XMLNS, Const.XML_TAG_COLOR,
                                                    TextUtils.applyPatternUI(Const.XML_START_TAG, Const.XML_TAG_SECOND_COLOR,
                                                            TextUtils.applyPatternUI(Const.AFTER_XMLNS, Const.XML_TAG_SECOND_COLOR,
                                                                    TextUtils.applyPatternUI(Const.XML_END_TAG, Const.XML_TAG_COLOR, spannable)))))))));
    }};

    public CodeText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }
    public CodeText(Context context) {
        super(context);
        setup();
    }
    public CodeText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void setup(){
        setHorizontallyScrolling(true);
        text_before = "";
        highlight();
        setFilters();
        setupUndoRedo();
        setupTouchZoom();
        initDraw();
    }
    public void initDraw(){
        mPaint.setColor(Color.parseColor(LINE_NUMBER_COLOR));
    }
    public void setFilters(){
        setFilters(new InputFilter[]{
                (string, start, end, destination, d_start, d_end) -> {
                    if(end - start == 1 && start < string.length() && d_start < destination.length()){
                        char inserted = string.charAt(start);
                        if (inserted == '\n') {
                            return autoIndent(string, destination, d_start, d_end);
                        }
                    }
                    return string;
                }
        });
    }
    public void setupUndoRedo(){
        undoRedo = new UndoRedoUtils(this);
    }

    int first_pointer_id = 0;
    int second_pointer_id = 1;

    double startDistance = -1;
    boolean canTouch = true;

    private void setupTouchZoom(){
        setOnTouchListener((view, motionEvent) -> {
            try {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        startDistance = -1;
                        canTouch = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (motionEvent.getPointerCount() == 2) {
                            int first_pointer_index = motionEvent.findPointerIndex(first_pointer_id);
                            int second_pointer_index = motionEvent.findPointerIndex(second_pointer_id);

                            float x1 = motionEvent.getX(first_pointer_index);
                            float y1 = motionEvent.getY(first_pointer_index);
                            float x2 = motionEvent.getX(second_pointer_index);
                            float y2 = motionEvent.getY(second_pointer_index);

                            double distance = MathUtils.getDistanceBetween(x1, y1, x2, y2);
                            startDistance = startDistance == -1 ? distance : startDistance;

                            double difference = distance - startDistance;

                            if (Math.abs(difference) >= DISTANCE_TO_ZOOM) {
                                float k = (float)difference / DISTANCE_TO_ZOOM;
                                if (difference > 0)
                                    upTextSize(k);
                                else
                                    downTextSize(k);
                                startDistance = distance;
                                canTouch = false;
                            }

                        }
                        break;
                }
                performClick();
                return !canTouch;
            } catch (Exception ignored){
                return true;
            }
        });
    }

    // TODO: Discover how it works and improve code style
    public CharSequence autoIndent(CharSequence source, Spanned dest, int d_start, int d_end){
        String indent = "";
        int istart = d_start - 1;
        int iend;

        // find start of this line
        boolean dataBefore = false;
        int pt = 0;

        for (; istart > -1; --istart) {
            char c = dest.charAt(istart);

            if (c == '\n')
                break;

            if (c != ' ' &&
                    c != '\t') {
                if (!dataBefore) {
                    // indent always after those characters
                    if (c == '{' ||
                            c == '+' ||
                            c == '-' ||
                            c == '*' ||
                            c == '/' ||
                            c == '%' ||
                            c == '^' ||
                            c == '=')
                        --pt;

                    dataBefore = true;
                }

                // parenthesis counter
                if (c == '(')
                    --pt;
                else if (c == ')')
                    ++pt;
            }
        }

        // copy indent of this line into the next
        if (istart > -1) {
            char charAtCursor = dest.charAt(d_start);

            for (iend = ++istart;
                 iend < d_end;
                 ++iend) {
                char c = dest.charAt(iend);

                // auto expand comments
                if (charAtCursor != '\n' &&
                        c == '/' &&
                        iend + 1 < d_end &&
                        dest.charAt(iend) == c) {
                    iend += 2;
                    break;
                }

                if (c != ' ' &&
                        c != '\t')
                    break;
            }

            indent += dest.subSequence(istart, iend);
        }

        // add new indent
        if (pt < 0)
            indent += "    "; // (4 spaces)

        // append white space of previous line and new indent
        return source + indent;
    }

    String text_before;
    boolean setTextCalled = false;

    public void highlight(){
        Thread.loop(HIGHLIGHT_COUNTDOWN, () -> {
            String current_string = Objects.requireNonNull(getText()).toString();
            if (setTextCalled || (!text_before.equals(current_string) && Time.getCurrentTime() - time_edited > 500)) {
                Thread.ui(() -> highlights.get(language).map(getText()));
                text_before = current_string;
                setTextCalled = false;
                // showing update
                if(HIGHLIGHT_DEBUG)
                    Toast.show(R.string.highlight_updated);
            }
        });
    }

    public void setLanguage(int language){
        this.language = language;
    }
    public int getLanguage(){
        return this.language;
    }

    public int getTextSizeSp(){
        return (int)(getTextSize() / DisplayUtils.getScaledDensity());
    }

    public void undo() {
        undoRedo.undo();
    }
    public void redo(){
        undoRedo.redo();
    }
    public void clearHistory(){
        undoRedo.clearHistory();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(!hasFocus())
            requestFocus();

        if(!pasting_second) {
            int max_lines = MAX_LINES_IN_TEXT_EDITOR * 10000;
            if(text.length() > max_lines){
                pasting_second = true;
                setText(text.subSequence(0, max_lines + 1));
                setSelection(max_lines + 1);
                return;
            }
            time_edited = Time.getCurrentTime();
            // Auto adding char
            if (lengthAfter - lengthBefore == 1) {
                char inserted = text.charAt(start);
                if (back_symbol.containsKey(inserted)) {
                    pasting_second = true;
                    Objects.requireNonNull(getText()).insert(start + 1, String.valueOf(back_symbol.get(inserted)));
                    setSelection(start + 1);
                }
            }
        } else {
            pasting_second = false;
        }
    }

    @Override
    public int getCompoundPaddingLeft() {
        int symbol_offset = (int)(getTextSize() / 1.5);
        return Integer.toString(getLineCount()).length() * symbol_offset + symbol_offset;
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        mPaint.setTextSize(getTextSize());
    }

    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        mPaint.setTextSize(getTextSize());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        setTextCalled = true;
        super.setText(text, type);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(hasFocus() && getSelectionStart() == getSelectionEnd())
            clearFocus();
        performClick();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    Rect lineBounds = new Rect();
    Paint highlightPaint = new Paint();
    Paint mPaint = new Paint();

    // trying to draw view if error trying again after half second to fix bug with spans
    @Override
    protected void onDraw(Canvas canvas) {
        mDraw(canvas);
        try{
            super.onDraw(canvas);
        } catch (Exception e){
            Thread.delay(500, () -> draw(canvas), true);
        }
    }

    private void mDraw(Canvas canvas){
        // highlighting current line
        highlightLine(getLayout().getLineForOffset(getSelectionStart()), Color.parseColor(CURRENT_LINE_COLOR), canvas);
        // drawing line numbers
        int count = getLineCount();

        for(int i = 0; i < count; i++){
            int baseline = getLineBounds(i, null);
            String num = Integer.toString(i + 1);
            canvas.drawText(num, 10 + getScrollX(), baseline, mPaint);
        }
        // drawing vertical divider
        int x = getScrollX() + getCompoundPaddingLeft() - 10;
        int y = Math.max(getLineHeight() * getLineCount(), getHeight());
        canvas.drawLine(x, 0, x, y, mPaint);
    }

    private void highlightLine(int line, int color, Canvas canvas){
        highlightPaint.setColor(color);
        getLineBounds(line, lineBounds);
        lineBounds.left = 0;
        canvas.drawRect(lineBounds, highlightPaint);
    }

    private void upTextSize(float k){
        changeTextSize(TEXT_SIZE_STEP * k);
    }
    private void downTextSize(float k){
        changeTextSize(TEXT_SIZE_STEP * k);
    }
    private void changeTextSize(float sizeDifference){
        if(getTextSizeSp() <= MAX_MAIN_EDITOR_FONT_SIZE && getTextSizeSp() >= MIN_MAIN_EDITOR_FONT_SIZE)
            setTextSize(COMPLEX_UNIT_PX, getTextSize() + sizeDifference);
        if(getTextSizeSp() > MAX_MAIN_EDITOR_FONT_SIZE)
            setTextSize(MAX_MAIN_EDITOR_FONT_SIZE);
        if(getTextSizeSp() < MIN_MAIN_EDITOR_FONT_SIZE)
            setTextSize(MIN_MAIN_EDITOR_FONT_SIZE);
    }

}
