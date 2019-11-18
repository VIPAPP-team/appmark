package com.vipapp.appmark2.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import com.vipapp.appmark2.items.StringData;
import com.vipapp.appmark2.widget.EditText;
import java.util.ArrayList;
import java.util.Objects;

public class UndoRedoUtils implements TextWatcher {
    private EditText editText;
    private ArrayList<StringData> history = new ArrayList();
    private int start_before = 0;
    private String text_before;
    private long time = 0;
    private ArrayList<StringData> undid = new ArrayList();
    private boolean undoOrRedo = false;

    public UndoRedoUtils(EditText editText) {
        this.editText = editText;
        editText.addTextChangedListener(this);
    }

    private void addToHistory(String str, int i) {
        this.history.add(0, new StringData(str, i));
        this.history = this.history.size() > 50 ? new ArrayList<>(this.history.subList(0, 50)) : this.history;
    }

    private void setTextFromData(StringData data) {
        editText.setText(data.getString());
        try {
            Selection.setSelection(editText.getText(), data.getCursorStart());
        } catch (IndexOutOfBoundsException ignored){}
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.text_before = charSequence.toString();
    }

    public void clearHistory() {
        this.history.clear();
        this.undid.clear();
        this.start_before = 0;
        this.time = 0;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if ((Time.getCurrentTime() - this.time > 3000 || Math.abs(i3 - i2) >= 10) && !this.undoOrRedo && !this.text_before.equals(charSequence.toString()) && (this.history.size() == 0 || !charSequence.toString().equals(((StringData) this.history.get(0)).getString()))) {
            this.time = Time.getCurrentTime();
            addToHistory(this.text_before, this.start_before);
        }
        if (!this.undoOrRedo && !this.text_before.equals(charSequence.toString())) {
            this.undid.clear();
            this.start_before = this.editText.getSelectionStart();
        }
    }

    public void redo() {
        if (this.undid.size() > 0) {
            this.history.add(0, new StringData(((Editable) Objects.requireNonNull(this.editText.getText())).toString(), this.editText.getSelectionStart()));
            this.undoOrRedo = true;
            setTextFromData((StringData) this.undid.get(0));
            this.undoOrRedo = false;
            this.undid.remove(0);
        }
    }

    public void undo() {
        if (this.history.size() > 0) {
            this.undid.add(0, new StringData(((Editable) Objects.requireNonNull(this.editText.getText())).toString(), this.editText.getSelectionStart()));
            this.undoOrRedo = true;
            setTextFromData((StringData) this.history.get(0));
            this.undoOrRedo = false;
            this.history.remove(0);
        }
    }
}