package com.vipapp.appmark2.manager;

import com.vipapp.appmark2.items.FileLocale;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.utils.ArrayUtils;
import com.vipapp.appmark2.utils.FileUtils;
import com.vipapp.obfuscated.xml.XMLArray;
import com.vipapp.obfuscated.xml.XMLObject;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.vipapp.appmark2.utils.Const.RES_XML_OBJ_DEFAULT;

public abstract class DefaultResManager<T> extends DefaultManager<XMLObject> {

    private String tagName;

    @Nullable
    private XMLObject xml_root_object;
    @Nullable
    private XMLArray xml_objects_array;

    private File file;

    public DefaultResManager(File source, String tagName){
        super(source.exists()? source: null);
        this.file = source;
        this.tagName = tagName;
    }

    public DefaultResManager<T> getLocaled(Project project, String locale){
        File values_localed = project.getValuesDir(locale);
        File file_localed = new File(values_localed, file.getName());

        if(!file_localed.exists()){
            FileUtils.refresh(file_localed, false);
            FileUtils.writeFileUI(file_localed,
                    FileUtils.getDefaultTextForFile(file_localed, project));
        }

        return new DefaultResManager<T>(file_localed, tagName) {
            @NonNull
            @Override
            protected T toValue(@NonNull String stringVal) {
                return DefaultResManager.this.toValue(stringVal);
            }

            @NonNull
            @Override
            protected String fromValue(@NonNull T value) {
                return DefaultResManager.this.fromValue(value);
            }
        };
    }

    public ArrayList<FileLocale> getLocales(Project project){
        return ArrayUtils.filter(project.getFileLocales(), object -> {
            File to_check = new File(project.getValuesDir(object.getLocaleName()), file.getName());
            return to_check.exists();
        });
    }

    @Nullable
    public XMLObject getObject(@NonNull String name){
        return xml_objects_array == null? null: xml_objects_array.getWithFilter(object -> {
            String obj_name = object.getNamedAttribute("name").getValue();
            return obj_name != null && obj_name.equals(name);
        });
    }

    @Nullable
    public T get(@NonNull String name){
        XMLObject object = getObject(name);
        return object == null? null: toValue(object.getValue());
    }

    @NonNull
    protected abstract T toValue(@NonNull String stringVal);
    @NonNull
    protected abstract String fromValue(@NonNull T value);

    public boolean add(@NonNull String name, @NonNull T value){
        return addString(name, fromValue(value));
    }
    private boolean addString(@NonNull String name, @NonNull String value){
        String object_string = String.format(RES_XML_OBJ_DEFAULT, tagName, name, value);
        XMLObject object = XMLObject.parse(object_string);

        if(object == null || xml_objects_array == null)
            return false;

        xml_objects_array.add(object);
        getObjects().add(object);

        return true;
    }

    public boolean rename(@NonNull String oldName, @NonNull String newName){
        XMLObject object = getObject(oldName);

        if(object == null)
            return false;

        object.setAttribute("name", newName);

        return true;
    }

    public boolean changeValue(@NonNull String name, @NonNull T object){
        return changeValueWithString(name, fromValue(object));
    }
    private boolean changeValueWithString(@NonNull String name, @NonNull String newValue){
        XMLObject object = getObject(name);

        if (object == null)
            return false;

        object.setValue(newValue);

        return true;
    }

    public void save(){
        if(xml_root_object != null) FileUtils.writeFileUI(file, xml_root_object.toString());
    }

    @Override
    public ArrayList<XMLObject> init(Object... objArr) {
        File file = (File) objArr[0];
        if(file == null)
            return null;
        XMLObject res = XMLObject.parse(FileUtils.readFileUI(file));
        if(res == null)
            return null;
        return setupFromRoot(res);
    }

    private ArrayList<XMLObject> setupFromRoot(XMLObject object){
        XMLArray objects_array = getArrayFromRoot(object);
        this.xml_root_object = object;
        this.xml_objects_array = objects_array;
        return xml_objects_array.toList();
    }

    private XMLArray getArrayFromRoot(XMLObject object){
        return object.getNamedXMLArray(tagName);
    }

    public File getFile(){
        return file;
    }

}
