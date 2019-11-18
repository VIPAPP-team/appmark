package com.vipapp.appmark2.widget;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.activities.StringEditorActivity;
import com.vipapp.appmark2.activities.ViewDesignActivity;
import com.vipapp.appmark2.items.FileActionItem;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.FileUtils;
import com.vipapp.appmark2.utils.wrapper.mActivity;
import com.vipapp.appmark2.callbacks.PushCallback;

import java.io.File;

public class FileActionButton extends TextView implements View.OnClickListener {

    PushCallback<Void> onClick = result -> {};

    Project project;
    File file;
    int fileType = -1;

    FileActionItem[] items = new FileActionItem[]{
            new FileActionItem() {
        public void action(File file) {
            Intent i = new Intent(getContext(), ViewDesignActivity.class);
            i.putExtra("file", file);
            i.putExtra("project", project);
            mActivity.get().startActivity(i);
        }
        public String getPlaceholder(Context context) {
            return context.getString(R.string.view);
        }
    },
            new FileActionItem() {
        public void action(File file) {
            Intent i = new Intent(getContext(), StringEditorActivity.class);
            i.putExtra("file", file);
            i.putExtra("project", project);
            mActivity.get().startActivity(i);
        }
        public String getPlaceholder(Context context) {
            return context.getString(R.string.view);
        }
    }
    };

    public FileActionButton(Context context) {
        super(context);
    }
    public FileActionButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public FileActionButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setup(Context context, AttributeSet attributeSet){
        super.setup(context, attributeSet);
        setOnClickListener(this);
    }

    public void setProject(Project project){
        this.project = project;
    }
    public void setFile(File file){
        fileType = getFileType(file);
        this.file = file;
        if(fileType == -1) setVisibility(GONE); else {
            setVisibility(VISIBLE);
            setText(items[fileType].getPlaceholder(getContext()));
        }
    }

    @Override
    public void onClick(View view) {
        if(file.exists()) {
            onClick.onComplete(null);
            if (fileType != -1) {
                items[fileType].action(file);
            }
        }
    }

    public void setOnClick(PushCallback<Void> callback){
        this.onClick = callback;
    }

    private int getFileType(File file){
        if(FileUtils.isFileLayout(file, project)) return Const.FILE_LAYOUT;
        if(FileUtils.isFileStrings(file, project)) return Const.FILE_STRINGS;

        return -1;
    }

}
