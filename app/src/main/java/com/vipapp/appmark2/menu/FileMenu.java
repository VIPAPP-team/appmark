package com.vipapp.appmark2.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.alert.ImageImportDialog;
import com.vipapp.appmark2.alert.confirm.DeleteFileDialog;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.FileItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.picker.string.FileNamePicker;
import com.vipapp.appmark2.picker.StringChooser;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.TextUtils;
import com.vipapp.appmark2.util.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FileMenu extends DefaultMenu<File, FileMenu.FileHolder> {

    private ArrayList<File> files;

    private File project_file;
    private Project project;
    private File current_path;
    private File opened;

    private PushCallback[] menuCallbacks = new PushCallback[]{
            // Goto opened
            none -> goto_file(opened.getParentFile()),
            // Goto project
            none -> goto_file(project_file),
            // create file
            none -> {
                FileNamePicker picker = new FileNamePicker(filename -> createNewFile(filename, false));
                picker.show();
            },
            // create folder
            none -> {
                FileNamePicker picker = new FileNamePicker(filename -> createNewFile(filename, true));
                picker.setTitle(R.string.enter_foldername);
                picker.setHint("dir");
                picker.show();
            },
            // import image
            none -> ImageImportDialog.show(current_path, this::notifyFile),
            // delete file
            file_item -> {
                //noinspection unchecked
                Item<File> item = (Item<File>) file_item;
                DeleteFileDialog.show(item.getInstance(), result -> {
                    files.remove(item.getType());
                    pushArray(files, false);
                    notifyRemoved(item.getType());
                });
            },
            // rename file
            file_item -> {
                //noinspection unchecked
                Item<File> item = (Item<File>)file_item;
                FileNamePicker picker = new FileNamePicker(string -> {
                    files.set(item.getType(), FileUtils.changeName(item.getInstance(), string));
                    pushArray(files, false);
                    notifyChanged(item.getType());
                });
                picker.setText(item.getInstance().getName());
                picker.show();
            },
            // copy path
            file_item -> {
                //noinspection unchecked
                Item<File> item = (Item<File>) file_item;
                TextUtils.copyToClipboard(item.getInstance().getAbsolutePath());
                Toast.show(R.string.file_path_copied);
            }
    };

    private FileItem[] items = new FileItem[]{
            new FileItem() {
            public String getString(Context ctx) { return ctx.getString(R.string.go_to); }
            public Bitmap getImage(Context ctx) { return BitmapFactory.decodeResource(ctx.getResources(), R.drawable.go_to); }
            public void onClick() {
                //noinspection unchecked
                StringChooser chooser = new StringChooser(item -> menuCallbacks[item.getType()].onComplete(null));
                ArrayList<Item<String>> array = new ArrayList<>(Const.goto_chooser);
                if(project_file == null) array.remove(0);
                if(opened == null) array.remove(1);
                chooser.setArray(array);
                chooser.setTitle(R.string.go_to);
                chooser.show();
            }
        },
            new FileItem() {
            public String getString(Context ctx) { return ctx.getString(R.string.create); }
            public Bitmap getImage(Context ctx) { return BitmapFactory.decodeResource(ctx.getResources(), R.drawable.create_file); }
            public void onClick() {
                //noinspection unchecked
                StringChooser chooser = new StringChooser(item -> menuCallbacks[item.getType()].onComplete(null));
                chooser.setArray(Const.create_chooser);
                chooser.setTitle(R.string.create);
                chooser.show();
            }
        },
            new FileItem() {
            public String getString(Context ctx) { return ".."; }
            public Bitmap getImage(Context ctx) { return BitmapFactory.decodeResource(ctx.getResources(), R.drawable.directory); }
            public void onClick(){
                File backup = current_path;
                try {
                    current_path = current_path.getParentFile();
                    pushArray(list(null));
                } catch (NullPointerException e){
                    current_path = backup;
                }
            }
        }
    };

    private Comparator<File> file_comparator = (file1, file2) -> {
        if(!(file1.isDirectory() && file2.isDirectory()))
            if(file1.isDirectory()) return -1;
            else if(file2.isDirectory()) return 1;
        return file1.getName().compareTo(file2.getName());
    };

    public FileMenu(){
        current_path = new File(Const.PROJECT_STORAGE);
        opened = current_path;
    }

    @Override
    public ArrayList<File> list(Context context) {
        files = new ArrayList<>(Arrays.asList(current_path.listFiles()));
        Collections.sort(files, file_comparator);
        // Add null item for menu
        files.add(0, null);
        files.add(0, null);
        files.add(0, null);
        //noinspection unchecked
        pushItem(new Item(Const.PATH, current_path.getAbsolutePath()));
        return files;
    }

    @Override
    public void onValueReceived(Item item) {
        switch(item.getType()){
            case Const.PATH:
                goto_file((File)item.getInstance());
                break;
            case Const.PROJECT:
                this.project = (Project)item.getInstance();
                this.project_file = project.getSource();
                break;
            case Const.OPENED_FILE:
                this.opened = (File)item.getInstance();
                goto_file(opened.getParentFile());
                break;
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.file_default;
    }

    @Override
    public FileHolder getViewHolder(ViewGroup parent, int itemType) {
        return new FileHolder(inflate(parent));
    }

    private void update(){
        goto_file(current_path);
    }

    private void goto_file(File file){
        current_path = file;
        pushArray(list(null));
    }

    @Override
    public void bind(FileHolder vh, File item, int i) {
        if(item == null){
            FileItem current = items[i];
            //noinspection ConstantConditions
            vh.icon.setImageBitmap(current.getImage(getRecyclerView().getContext()));
            vh.name.setText(current.getString(getRecyclerView().getContext()));
            vh.itemView.setOnClickListener(view -> current.onClick());
        } else {
            vh.icon.setImageResource(item.isDirectory() ?
                    R.drawable.directory :
                    R.drawable.file);
            vh.name.setText(item.getName());
            setCallbacks(vh, item);
        }
    }

    private void setCallbacks(RecyclerView.ViewHolder vh, File item){
        vh.itemView.setOnClickListener(view -> {
            if (item.isDirectory()) {
                current_path = item;
                pushArray(list(null));
            } else {
                //noinspection unchecked
                pushItem(new Item(Const.FILE_SELECTED, item));
            }
        });
        if(item != null) vh.itemView.setOnLongClickListener(view -> {
            if(files.indexOf(item) != -1) {
                //noinspection unchecked
                StringChooser chooser = new StringChooser(action -> menuCallbacks[action.getType()].onComplete(
                        new Item<>(files.indexOf(item), item)));
                chooser.setTitle(R.string.select_action);
                chooser.setArray(Const.file_action_chooser);
                chooser.show();
            }
            return true;
        });
    }

    private void createNewFile(String filename, boolean directory){
        File newFile = new File(current_path, filename);
        if(FileUtils.refresh(newFile, directory)) {
            if (!directory)
                FileUtils.writeFileUI(newFile, FileUtils.getDefaultTextForFile(newFile, project));
            notifyFile(newFile);
        } else {
            Toast.show(R.string.error_create_file);
        }
    }

    private void notifyFile(File file){
        list(null);
        pushArray(files, false);
        notifyInserted(files.indexOf(file));
    }

    static class FileHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;

        public FileHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
        }
    }

}
