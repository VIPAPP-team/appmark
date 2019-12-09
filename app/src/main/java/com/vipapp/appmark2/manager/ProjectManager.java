package com.vipapp.appmark2.manager;


import com.vipapp.appmark2.item.OnLoadItem;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.wrapper.mActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static com.vipapp.appmark2.util.Const.LOAD_TIME;

public class ProjectManager extends DefaultManager<Project> {

    private File project_directory;

    public ProjectManager(Object... args){
        super(args);
    }

    public boolean existsWithName(String name){
        for(Project project: getObjects()){
            if(project.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public ArrayList<Project> init(Object... args) {
        int[] loaded = new int[]{0, 0};

        setupVars(args);

        ArrayList<Project> projects = new ArrayList<>();
        File[] files = project_directory.listFiles();
        for(File file: files){
            if(Project.notProject(file))
                continue;
            Project project = Project.fromFile(file);
            project.exec(none -> loaded[0]++);
            loaded[1]++;
            projects.add(project);
        }

        while(loaded[0] != loaded[1]) {
            Thread.sleep(LOAD_TIME);
        }

        Collections.sort(projects, (project1, project2) ->
                -Long.compare(project1.getDir().lastModified(), project2.getDir().lastModified()));

        Thread.ui(() -> mActivity.get().onLoad(new OnLoadItem(
                projects.size() == 0? Const.NO_PROJECTS: Const.PROJECT_LOAD)));

        return projects;
    }

    private void setupVars(Object... args){
        if(args[0] instanceof String)
            project_directory = new File((String)args[0]);
        else
            project_directory = (File) args[0];
        FileUtils.refresh(project_directory, true);
    }

}
