package com.vipapp.appmark2.widget;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.activity.StringsEditorActivity;
import com.vipapp.appmark2.activity.ViewDesignActivity;
import com.vipapp.appmark2.item.FileActionItem;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.wrapper.mActivity;
import com.vipapp.appmark2.callback.PushCallback;

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
            Intent i = new Intent(getContext(), StringsEditorActivity.class);
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
    public boolean setFile(File file){
        fileType = getFileType(file);
        boolean fileTypeFound = fileType != -1;
        this.file = file;
        if (fileTypeFound) {
            setVisibility(VISIBLE);
            setText(items[fileType].getPlaceholder(getContext()));
        } else {
            setVisibility(GONE);
        }
        return fileTypeFound;
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
