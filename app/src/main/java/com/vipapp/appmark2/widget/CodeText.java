package com.vipapp.appmark2.widget;

import android.content.Context;
import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.LineBackgroundSpan;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.Mapper;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.ScrollChange;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.DisplayUtils;
import com.vipapp.appmark2.util.MathUtils;
import com.vipapp.appmark2.util.TextUtils;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.Time;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.util.UndoRedoUtils;
import com.vipapp.appmark2.util.wrapper.Res;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static com.vipapp.appmark2.util.Const.CURRENT_LINE_COLOR;
import static com.vipapp.appmark2.util.Const.DISTANCE_TO_ZOOM;
import static com.vipapp.appmark2.util.Const.ERROR_COLOR;
import static com.vipapp.appmark2.util.Const.HIGHLIGHT_COUNTDOWN;
import static com.vipapp.appmark2.util.Const.HIGHLIGHT_DEBUG;
import static com.vipapp.appmark2.util.Const.LINE_NUMBER_COLOR;
import static com.vipapp.appmark2.util.Const.MAX_LINES_IN_TEXT_EDITOR;
import static com.vipapp.appmark2.util.Const.MAX_MAIN_EDITOR_FONT_SIZE;
import static com.vipapp.appmark2.util.Const.MIN_MAIN_EDITOR_FONT_SIZE;
import static com.vipapp.appmark2.util.Const.TEXT_SIZE_STEP;
import static com.vipapp.appmark2.util.Const.WARNING_COLOR;
import static com.vipapp.appmark2.util.Const.back_symbol;

public class CodeText extends EditText {

    PushCallback<ScrollChange> scroll;

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
                        if (inserted == '\n')
                            return autoIndent(string, destination, d_start, d_end);
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

    public CharSequence autoIndent(CharSequence source, Spanned dest, int d_start, int d_end){
        String indent = "";
        int sequenceStart = d_start - 1;
        int sequenceEnd;

        // find start of this line
        boolean stringFound = false;
        int pt = 0;

        for (; sequenceStart > -1; --sequenceStart) {
            char c = dest.charAt(sequenceStart);

            if (c == '\n')
                break;

            if (c != ' ' && c != '\t') {
                if (!stringFound) {
                    // indent always after those characters
                    if (c == '{' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' || c == '=')
                        --pt;

                    stringFound = true;
                }

                // parenthesis counter
                if (c == '(')
                    --pt;
                else if (c == ')')
                    ++pt;
            }
        }

        // copy indent of this line into the next
        char charAtCursor = ' ';
        if (sequenceStart > -1) {
            charAtCursor = dest.charAt(d_start);

            for (sequenceEnd = ++sequenceStart; sequenceEnd < d_end; ++sequenceEnd) {
                char lastChar = dest.charAt(sequenceEnd);

                // auto expand comments
                if (charAtCursor != '\n' && lastChar == '/' && sequenceEnd + 1 < d_end && dest.charAt(sequenceEnd) == lastChar) {
                    sequenceEnd += 2;
                    break;
                }

                if (lastChar != ' ' && lastChar != '\t')
                    break;
            }

            indent += dest.subSequence(sequenceStart, sequenceEnd);
        }

        // add new indent
        if (pt < 0) {
            String old_indent = indent;
            indent += "    "; // (4 spaces)
            if(charAtCursor == '}' || charAtCursor == ')' || charAtCursor == ']')
                indent += "\n" + old_indent;
        }

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

    public void setError(int start, int end){
        getText().setSpan(new WaveSpan(Color.parseColor(ERROR_COLOR)), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }

    public void setWarning(int start, int end){
        getText().setSpan(new WaveSpan(Color.parseColor(WARNING_COLOR)), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    }

    public void clearErrors(){
        TextUtils.clearSpans(getText(), WaveSpan.class, 0, getText().length());
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
            insertSecondChar(text, start, lengthBefore, lengthAfter);
            cutBigString(text);
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

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        if(scroll != null)
            scroll.onComplete(new ScrollChange(vert, horiz, oldVert, oldHoriz));
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
    }

    private void mDraw(Canvas canvas){
        Layout layout = getLayout();
        if(layout != null) {
            // highlighting current line
            highlightLine(layout.getLineForOffset(getSelectionStart()), Color.parseColor(CURRENT_LINE_COLOR), canvas);
            // drawing line numbers
            int count = getLineCount();

            for (int i = 0; i < count; i++) {
                int baseline = getLineBounds(i, null);
                String num = Integer.toString(i + 1);
                canvas.drawText(num, 10 + getScrollX(), baseline, mPaint);
            }
            // drawing vertical divider
            int x = getScrollX() + getCompoundPaddingLeft() - 10;
            int y = Math.max(getLineHeight() * getLineCount(), getHeight());
            canvas.drawLine(x, 0, x, y, mPaint);
        }
    }

    private void highlightLine(int line, int color, Canvas canvas){
        highlightPaint.setColor(color);
        getLineBounds(line, lineBounds);
        lineBounds.left = 0;
        canvas.drawRect(lineBounds, highlightPaint);
    }

    private void insertSecondChar(CharSequence text, int start, int lengthBefore, int lengthAfter){
        time_edited = Time.getCurrentTime();
        // Auto adding char
        if (lengthAfter - lengthBefore == 1) {
            char inserted = text.charAt(start);
            if (start != 0 && symbolOpened(inserted, text)) {
                pasting_second = true;
                Objects.requireNonNull(getText()).insert(start + 1, String.valueOf(back_symbol.get(inserted)));
                setSelection(start + 1);
            }
        }
    }

    private boolean symbolOpened(Character symbol, CharSequence text){
        if(!back_symbol.containsKey(symbol)) {
            return false;
        }
        Character back = back_symbol.get(symbol);

        int symbol_count = 0;
        int back_count = 0;
        for(int i = 0; i < text.length(); i++){
            Character c = text.charAt(i);
            if(c.equals(symbol))
                symbol_count ++;
            if(c.equals(back))
                back_count ++;
        }

        return (!symbol.equals(back) && symbol_count == back_count + 1) ||
                (symbol.equals(back) && symbol_count % 2 == 1);
    }

    private void cutBigString(CharSequence text){
        int max_lines = MAX_LINES_IN_TEXT_EDITOR * 10000;
        if(text.length() > max_lines){
            pasting_second = true;
            setText(text.subSequence(0, max_lines + 1));
            setSelection(max_lines + 1);
        }
    }

    public void setOnScrollChangeListener(PushCallback<ScrollChange> listener){
        this.scroll = listener;
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

    public int getLineStartIndex(int line){
        Pattern pattern = Pattern.compile(".*?\n");
        Matcher matcher = pattern.matcher(getText());
        int i = 0;
        while(matcher.find()){
            if(line == i)
                return matcher.start();
            i++;
        }
        return -1;
    }
    public int getLineEndIndex(int line){
        Pattern pattern = Pattern.compile(".*?\n");
        Matcher matcher = pattern.matcher(getText());
        int i = 0;
        while(matcher.find()){
            if(line == i)
                return matcher.end();
            i++;
        }
        return -1;
    }

    @NonNull
    @Override
    public Editable getText() {
        return Objects.requireNonNull(super.getText());
    }

    private static class WaveSpan implements LineBackgroundSpan {

        private int lineWidth;
        private int waveSize;
        private int color;

        WaveSpan(int color){
            this(color, 1, 3);
        }

        WaveSpan(int color, int lineWidth, int waveSize){
            float scale = Res.get().getDisplayMetrics().density;
            this.lineWidth = (int)(lineWidth * scale + 0.5f);
            this.waveSize = (int)(waveSize * scale + 0.5f);
            this.color = color;
        }

        @Override
        public void drawBackground(Canvas canvas, Paint paint, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, int lnum) {
            Paint p = new Paint(paint);
            p.setColor(color);
            p.setStrokeWidth(lineWidth);

            int width = (int) paint.measureText(text, start, end);

            int doubleWaveSize = waveSize * 2;
            for(int i = left; i < width; i += doubleWaveSize){
                canvas.drawLine(i, bottom, i + waveSize, bottom - waveSize, p);
                canvas.drawLine(i + waveSize, bottom - waveSize, i + doubleWaveSize, bottom, p);
            }
        }
    }
}
