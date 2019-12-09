package com.vipapp.appmark2.menu;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.activity.CodeActivity;
import com.vipapp.appmark2.alert.EditProject;
import com.vipapp.appmark2.alert.confirm.DeleteProject;
import com.vipapp.appmark2.holder.ProjectHolder;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.manager.ProjectManager;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.util.wrapper.mActivity;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.PROJECT_MANAGER;

public class ProjectMenu extends DefaultMenu<Project, ProjectHolder>{

    private ProjectManager manager;

    public ArrayList<Project> list(Context context){
        if(manager != null)
            manager.exec(this::pushArray);
        return null;
    }
    public void bind(ProjectHolder vh, Project item, int i) {
        if(item.getName() == null ||
                item.getPackage() == null ||
                item.getVersionName() == null)
            Toast.show(String.format(
                    Str.get(R.string.xml_error), item.getAndroidManifestFile().getAbsolutePath()));

        vh.name.setText(item.getName());
        vh.packag.setText(item.getPackage());
        vh.version_name.setText(item.getVersionName());
        vh.edit.setVisibility(item.getManifest().isCorrect()?View.VISIBLE:View.GONE);
        ImageUtils.loadInto(item.getIcon(), vh.icon);
        setCallbacks(vh, item, i);
    }

    private void setCallbacks(ProjectHolder vh, Project item, int i){
        vh.itemView.setOnLongClickListener(view -> {
            showView(vh);
            return true;
        });
        vh.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mActivity.get(), CodeActivity.class);
            intent.putExtra("project", item);
            mActivity.get().startActivity(intent);
        });
        vh.delete.setOnClickListener(view -> DeleteProject.show(item, i));
        vh.edit.setOnClickListener(view -> EditProject.show(item, i));
        vh.close.setOnClickListener(view -> hideView(vh));
    }

    @Override
    public void onValueReceived(Item item) {
        if(item.getType() == PROJECT_MANAGER){
            manager = (ProjectManager) item.getInstance();
            manager.exec(this::pushArray);
        }
        super.onValueReceived(item);
    }

    private void showView(ProjectHolder vh){
        vh.edit_panel.setVisibility(View.VISIBLE);
    }
    private void hideView(ProjectHolder vh){
        vh.edit_panel.setVisibility(View.GONE);
    }
}
