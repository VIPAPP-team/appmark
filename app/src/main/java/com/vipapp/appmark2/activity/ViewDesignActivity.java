package com.vipapp.appmark2.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.alert.EditViewDialog;
import com.vipapp.appmark2.callback.Mapper;
import com.vipapp.appmark2.item.BuiltView;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.picker.StringChooser;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ArrayUtils;
import com.vipapp.appmark2.util.DisplayUtils;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.widget.DesignView;
import com.vipapp.appmark2.widget.TextView;
import com.vipapp.appmark2.xml.XMLObject;

import java.io.File;
import java.util.ArrayList;

public class ViewDesignActivity extends BaseActivity{

    private ArrayList<BuiltView> widgets = new ArrayList<>();

    private int[] loaded = new int[]{0, 2};

    private boolean success_build = true;
    private boolean isLoaded = false;

    XMLObject selected;

    File file;

    Project project;
    XMLObject xmlObject;

    DesignView view;
    ProgressBar progress;
    TextView error;
    TextView title;

    View select_widget;

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
        select_widget = f(R.id.select_widget);
    }

    @Override
    public void init(){
        project = (Project) getIntent().getSerializableExtra("project");
        file = (File) getIntent().getSerializableExtra("file");
    }

    @Override
    public void setCallbacks() {
        select_widget.setOnClickListener(view -> {
            ArrayList<Item<String>> list = new ArrayList<>();
            int i = 0;
            for(BuiltView widget: widgets) {
                XMLObject object = widget.getObject();
                String id = object.getNamedAttribute("android:id").getValue();
                list.add(new Item<>(i, id == null?
                        object.getName() + "(no id)":
                        id.replaceFirst("@\\+id/", "") + ": " + object.getName()));
                i++;
            }
            StringChooser widget_chooser = new StringChooser(result ->
                    widgets.get(result.getType()).getView().callOnClick());
            widget_chooser.setTitle(R.string.select_widget);
            widget_chooser.setArray(list);
            widget_chooser.show();
        });
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
        if(!build()){
            setError();
        }
    }

    private void setError(){
        success_build = false;
        error.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);
        select_widget.setVisibility(View.GONE);
    }

    private void setLoaded(){
        loaded[0] ++;
        if(loaded[0] == loaded[1]) {
            isLoaded = true;
            progress.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            select_widget.setVisibility(View.VISIBLE);
            setupViewsOnLoad();
        }
    }

    private boolean build(){
        widgets.clear();
        return view.buildDesign(xmlObject, project, new BuiltViewMapper());
    }

    class BuiltViewMapper implements Mapper<BuiltView, View>{
        public View map(BuiltView viewObject) {

            LinearLayout linearLayout = new LinearLayout(ViewDesignActivity.this);
            int padding = (int)(1 * DisplayUtils.getScaledDensity());
            linearLayout.setPadding(padding, padding, padding, padding);
            linearLayout.setBackground(getDrawable(R.drawable.ripple_black_border));
            linearLayout.setOnClickListener(layout -> {
                selected = viewObject.getObject();
                EditViewDialog.show(viewObject.getObject(), project, viewObject.getView(), widgets, none -> build());
            });

            View addable = viewObject.getView();
            addable.setEnabled(!ArrayUtils.in_array(new Class[]{
                    SeekBar.class
            }, addable.getClass()));
            addable.setClickable(false);
            addable.setFocusable(true);
            addable.setFocusableInTouchMode(true);
            addable.setOnFocusChangeListener((view, b) -> {
                if(b)
                    linearLayout.callOnClick();
            });

            linearLayout.setTranslationX(addable.getTranslationX());
            linearLayout.setTranslationY(addable.getTranslationY());
            linearLayout.setScaleX(addable.getScaleX());
            linearLayout.setScaleY(addable.getScaleY());
            linearLayout.setVisibility(addable.getVisibility());
            linearLayout.setRotation(addable.getRotation());

            addable.setRotation(0);
            addable.setTranslationX(0);
            addable.setTranslationY(0);
            addable.setScaleY(1);
            addable.setScaleX(1);

            linearLayout.addView(addable);

            linearLayout.setLayoutParams(addable.getLayoutParams());
            addable.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            widgets.add(new BuiltView(linearLayout, viewObject.getObject()));

            return linearLayout;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    private void save(){
        if(isLoaded && success_build) {
            FileUtils.writeFile(file, xmlObject.toString());
        }
    }

}
