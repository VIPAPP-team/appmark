package com.vipapp.appmark2.alert.strings_list_editor;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.alert.StringsListEditor;
import com.vipapp.appmark2.items.TransformedItem;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.project.ProjectSettings;
import com.vipapp.appmark2.utils.wrapper.Str;

import java.util.ArrayList;

public class ProjectSettingsDialog {

    public static void show(Project project){
        ProjectSettings settings = project.getSettings();
        StringsListEditor.show(Str.get(R.string.project_settings), transformSettings(settings), result ->
                project.setSettings(transformToSettings(result)));
    }

    private static ArrayList<TransformedItem<String, String>> transformSettings(ProjectSettings settings){
        ArrayList<TransformedItem<String, String>> result = new ArrayList<>();
        for(String str: ProjectSettings.getAvailableKeys()){
            result.add(new TransformedItem<>(str + ":", settings.get(str)));
        }
        return result;
    }

    private static ProjectSettings transformToSettings(ArrayList<TransformedItem<String, String>> transformedItems){
        ProjectSettings settings = new ProjectSettings();
        for(TransformedItem<String, String> item: transformedItems){
            // crop ':' and add to settings
            settings.put(item.getItem1().substring(0, item.getItem1().length() - 1), item.getItem2());
        }
        return settings;
    }

}
