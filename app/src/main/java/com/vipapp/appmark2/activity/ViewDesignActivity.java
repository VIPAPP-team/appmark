package com.vipapp.appmark2.activity;

import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.FileUtils;
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
    public Integer onCreateView() {
        return R.layout.activity_view_design;
    }

    @Override
    public void findViews(){
        view = f(R.id.design);
        progress = f(R.id.progress);
        error = f(R.id.error);
        title = f(R.id.title);
    }

    @Override
    public void init(){
        project = (Project) getIntent().getSerializableExtra("project");
        file = (File) getIntent().getSerializableExtra("file");
    }

    @Override
    public void setup() {
        load();
        setupViews();
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
