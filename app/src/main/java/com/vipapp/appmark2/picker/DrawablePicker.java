package com.vipapp.appmark2.picker;

import android.graphics.BitmapFactory;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.alert.ImageImportDialog;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.ImageItem;
import com.vipapp.appmark2.project.Drawables;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.wrapper.Str;

import java.io.File;
import java.util.ArrayList;

import static com.vipapp.appmark2.util.wrapper.Res.get;

public class DrawablePicker extends DefaultPicker<String> {

    private ArrayList<ImageItem> items = new ArrayList<>();

    private Project project;

    private int menuBorder = 0;
    private PushCallback[] callbacks = new PushCallback[]{
            none -> ImageImportDialog.show(project.getDrawablesDir(), file -> pushItem(fromFile(file)))
    };

    private ImageItemChooser chooser = new ImageItemChooser(imageItem -> {
        int itemIndex = items.indexOf(imageItem);
        if(itemIndex >= menuBorder)
            pushItem(imageItem.getTitle());
        else
            //noinspection unchecked
            callbacks[itemIndex].onComplete(null);
    });

    public DrawablePicker(PushCallback<String> callback, Project project) {
        super(callback);
        this.project = project;
        chooser.setTitle(R.string.select_drawable);
        loadDrawables(project.getRes());
    }

    private void loadDrawables(Res res){
        Drawables drawables = res.getDrawables();

        items.add(new ImageItem(BitmapFactory.decodeResource(get(), R.drawable.create), Str.get(R.string.create)));

        menuBorder = items.size();

        if(drawables != null) {
            ArrayList<File> files = drawables.getDrawables();
            int[] loaded = new int[]{0};
            for (File file: files) {
                ImageUtils.load(file, bitmap -> {
                    loaded[0]++;
                    String drawableName = fromFile(file);
                    items.add(new ImageItem(bitmap, drawableName));
                    if(loaded[0] == files.size()) {
                        chooser.setArray(items);
                        chooser.show();
                    }
                });
            }
            if(files.size() == 0){
                chooser.show();
            }
        }
    }

    @Override
    public void show() {}

    private static String fromFile(File file){
        return "@drawable/" + FileUtils.getFileName(file);
    }
}
