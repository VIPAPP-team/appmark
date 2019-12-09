package com.vipapp.appmark2.picker;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.Predicate;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.ProjectItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.ContextUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;

import java.util.Objects;

import static com.vipapp.appmark2.util.Const.DEFAULT_APP_NAME;
import static com.vipapp.appmark2.util.Const.DEFAULT_PACKAGE_NAME;
import static com.vipapp.appmark2.util.Const.DEFAULT_VERSION_CODE;
import static com.vipapp.appmark2.util.Const.DEFAULT_VERSION_NAME;

public class ProjectPicker extends DefaultPicker<ProjectItem> {

    private Predicate<String> checkName;

    private Bitmap icon;

    private TextView next;
    private EditText app_name;
    private EditText project_package;
    private EditText version_name;
    private EditText version_id;
    private ImageView app_icon;

    public ProjectPicker(PushCallback<ProjectItem> callback, ProjectItem default_values, Predicate<String> checkName) {
        super(callback);
        this.checkName = checkName;
        setView(R.layout.create_project_first);
        loadDefaultIcon();
        findViews(getView());
        setCallbacks();
        setValues(default_values);
    }
    public ProjectPicker(PushCallback<ProjectItem> callback, Predicate<String> checkName){
        this(callback, null, checkName);
    }
    public ProjectPicker(PushCallback<ProjectItem> callback){
        this(callback, null, null);
    }

    private void loadDefaultIcon(){
        icon = BitmapFactory.decodeResource(ContextUtils.context.getResources(), R.drawable.app_icon_default);
    }
    private void findViews(View v){
        next = v.findViewById(R.id.next);
        app_name = v.findViewById(R.id.app_name);
        project_package = v.findViewById(R.id.project_package);
        version_name = v.findViewById(R.id.version_name);
        version_id = v.findViewById(R.id.version_id);
        app_icon = v.findViewById(R.id.app_icon);
    }
    private void setCallbacks(){
        next.setOnClickListener(view -> {
            try {
                Predicate<String> not_empty = text -> !text.equals("");
                String app_name_str = ClassUtils.getOrDefault(
                        Objects.requireNonNull(app_name.getText()).toString(), DEFAULT_APP_NAME, not_empty);
                String project_package_str = ClassUtils.getOrDefault(
                        Objects.requireNonNull(project_package.getText()).toString(), DEFAULT_PACKAGE_NAME, not_empty);
                String version_name_str = ClassUtils.getOrDefault(
                        Objects.requireNonNull(version_name.getText()).toString(), DEFAULT_VERSION_NAME, not_empty);
                String version_id_string = ClassUtils.getOrDefault(
                        Objects.requireNonNull(version_id.getText()).toString(), DEFAULT_VERSION_CODE, not_empty);

                if(!checkName.test(app_name_str)){
                    Toast.show(R.string.project_with_name_already_exists);
                    return;
                }
                if(!checkPackage(project_package_str)){
                    Toast.show(R.string.incorrect_package);
                    return;
                }

                int version_id_int = Integer.parseInt(version_id_string);
                pushItem(new ProjectItem(app_name_str, project_package_str, version_name_str, version_id_int, icon));
                cancel();
            } catch (NumberFormatException e){
                //Toast.show(R.string.incorrect_version_id);
                //Toast.show(e.getMessage());
            }
        });
        app_icon.setOnClickListener(view -> {
            ImagePicker picker = new ImagePicker(bitmap ->  {
                icon = bitmap.getBitmap();
                app_icon.setImageBitmap(icon);
            });
            picker.show();
        });
    }
    private static boolean checkPackage(String project_package){
        return project_package.matches(Const.PACKAGE_REGEX);
    }
    @SuppressLint("SetTextI18n")
    private void setValues(ProjectItem values){
        if(values != null){
            app_name.setText(values.getName());
            project_package.setText(values.getPackage());
            version_name.setText(values.getVersionName());
            version_id.setText(Integer.toString(values.getVersionCode()));
            ImageUtils.load(values.getIconFile(), icon -> {
                this.icon = icon;
                app_icon.setImageBitmap(icon);
            });
        }
    }

}
