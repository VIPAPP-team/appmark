package com.vipapp.appmark2.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.utils.FileUtils;
import com.vipapp.appmark2.widget.DesignView;
import com.vipapp.appmark2.widget.TextView;
import com.vipapp.appmark2.xml.XMLObject;

import java.io.File;

public class ViewDesignActivity extends BaseActivity {

    int[] loaded = new int[]{0, 2};

    boolean success_build = true;

    File file;

    Project project;
    XMLObject xmlObject;

    DesignView view;
    ProgressBar progress;
    TextView error;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_design);
        findViews();
        setupVars();
        load();
        setupViews();
    }

    private void setupVars(){
        project = (Project) getIntent().getSerializableExtra("project");
        file = (File) getIntent().getSerializableExtra("file");
    }

    private void findViews(){
        view = findViewById(R.id.design);
        progress = findViewById(R.id.progress);
        error = findViewById(R.id.error);
        title = findViewById(R.id.title);
    }

    private void load(){
        // Loading project
        project.exec(none -> setLoaded());
        // Loading file text
        FileUtils.readFile(file, result -> {
            xmlObject = XMLObject.parse(result);
            setLoaded();
        });
    }

    private void setupViews(){
        title.setText(file.getName());
    }

    private void setupViewsOnLoad(){
        if(!view.buildDesign(xmlObject, project)){
            setError();
        }
    }

    private void setError(){
        success_build = false;
        error.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);
    }

    private void setLoaded(){
        loaded[0] ++;
        if(loaded[0] == loaded[1]) {
            progress.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            setupViewsOnLoad();
        }
    }

}
