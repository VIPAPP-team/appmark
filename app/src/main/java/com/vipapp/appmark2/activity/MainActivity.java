package com.vipapp.appmark2.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.alert.CreateProjectDialog;
import com.vipapp.appmark2.alert.LoadableInfoDialog;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.OnLoadItem;
import com.vipapp.appmark2.item.OnProjectEdited;
import com.vipapp.appmark2.manager.ProjectManager;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ActivityStarter;
import com.vipapp.appmark2.util.wrapper.Animation;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.util.wrapper.mAppInfo;
import com.vipapp.appmark2.util.wrapper.mSharedPreferences;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;
import com.vipapp.appmark2.server.Server;

import java.io.File;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.content.pm.PackageInfoCompat;

import static android.view.View.VISIBLE;
import static com.vipapp.appmark2.util.Const.PROJECT_MANAGER;
import static com.vipapp.appmark2.util.Const.STATE_LOADING;
import static com.vipapp.appmark2.util.Const.STATE_NO_PROJECTS;
import static com.vipapp.appmark2.util.Const.STATE_RUNNING;

@SuppressWarnings("unchecked")
public class MainActivity extends BaseActivity {

    ProjectManager manager;

    TextView no_projects;
    RecyclerView projectRecycler;
    TextView create_new;
    LinearLayout menu_container;
    LinearLayout title_container;

    int state = STATE_LOADING;

    PushCallback[] stateCallbacks = new PushCallback[]{
            none -> {},
            none -> projectRecycler.setVisibility(VISIBLE),
            none -> no_projects.setVisibility(VISIBLE),
    };

    PushCallback[] loadCallbacks = new PushCallback[]{
            // on projects loaded
            none -> setState(STATE_RUNNING),
            // when projects not found
            none -> setState(STATE_NO_PROJECTS),
            // ???
            none -> {},
            // on project deleted
            pos -> {
                projectRecycler.getAdapter().getList().remove((int)pos);
                projectRecycler.getAdapter().notifyItemRemoved((int)pos);
                manager.getObjects().remove((int)pos);
            },
            // on project edited
            projectEdited -> {
                OnProjectEdited item = (OnProjectEdited)projectEdited;
                projectRecycler.getAdapter().getList().set(item.getPosition(), item.getProject());
                projectRecycler.getAdapter().notifyItemChanged(item.getPosition());
                manager.getObjects().set(item.getPosition(), item.getProject());
            },
            // settings button clicked
            none -> {
                ActivityStarter.go("SettingsActivity");
                closeMenu();
            },
            // changelog button clicked
            none -> {
                LoadableInfoDialog dialog = new LoadableInfoDialog(R.string.changelog, callback ->
                        Server.getChangelog(string -> {
                    if(string != null){
                        callback.onComplete(string);
                    } else {
                        callback.onComplete(Str.get(R.string.offline));
                    }
                }));
                dialog.show();
                closeMenu();
            },
            // goto community
            none -> ActivityStarter.goExternalUrl(Const.TELEGRAM_COMMUNITY_URL)
    };

    @Override
    public Integer onCreateView() {
        return R.layout.activity_main;
    }

    @Override
    public void findViews(){
        projectRecycler = f(R.id.project_recycler);
        create_new = f(R.id.create_new);
        no_projects = f(R.id.no_projects);
        menu_container = f(R.id.menu_container);
        title_container = f(R.id.title_container);
    }

    @Override
    public void setCallbacks(){
        create_new.setOnClickListener(view -> {
            if(state != STATE_LOADING) CreateProjectDialog.show(this::insertFirstProject,
                    name -> !manager.existsWithName(name));
        });
        title_container.setOnClickListener(view -> openMenu());
        menu_container.setOnClickListener(view -> closeMenu());
    }

    @Override
    public void setup() {
        setupProjectManager();
        openLastProject();
        checkNewVersion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupProjectManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        projectRecycler.update();
    }

    @Override
    public void onLoadCallback(OnLoadItem item) {
        int what = item.getId();
        loadCallbacks[what].onComplete(item.getObject());
    }

    public void setupProjectManager(){
        manager = new ProjectManager(Const.PROJECT_STORAGE);
        projectRecycler.pushValue(PROJECT_MANAGER, manager);
    }

    public void checkNewVersion(){
        Server.getActualCode(code -> {
            if(code != null && Integer.parseInt(code) > PackageInfoCompat.getLongVersionCode(mAppInfo.get()))
                Server.getActualVersion(version -> Toast.show(String.format(Str.get(R.string.new_version_available), version)));
        });
    }

    public void openLastProject(){
        String path = mSharedPreferences.getString("last_project", "");
        File project = new File(path);
        if(!path.equals("") && project.exists() && Const.OPEN_LAST_PROJECT){
            // Open CodeActivity
            Intent i = new Intent(this, CodeActivity.class);
            i.putExtra("project", Project.fromFile(project));
            i.putExtra("opened_last", true);
            startActivity(i);
        }
    }

    public void insertFirstProject(Project project){
        if(state != STATE_RUNNING)
            setState(STATE_RUNNING);
        manager.getObjects().add(0, project);
        Objects.requireNonNull(projectRecycler.getAdapter()).getList().add(0, project);
        projectRecycler.getAdapter().notifyItemInserted(0);
    }

    public void openMenu(){
        // hiding create_new button bcz it draws over shadow
        create_new.setVisibility(View.GONE);
        // animation
        Animation.fadeIn(menu_container);
        // turning on clicking
        menu_container.setVisibility(VISIBLE);
    }

    public void closeMenu(){
        // showing create_new button again
        create_new.setVisibility(VISIBLE);
        // back animation
        Animation.fadeOut(menu_container, 150);
        // turning off clicking
        Thread.delay(150, () -> menu_container.setVisibility(View.GONE), true);
    }

    public void setState(int state){
        this.state = state;
        hideAll();
        stateCallbacks[state].onComplete(null);
    }

    public void hideAll(){
        projectRecycler.setVisibility(View.GONE);
        no_projects.setVisibility(View.GONE);
    }

}
