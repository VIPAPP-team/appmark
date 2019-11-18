package com.vipapp.appmark2.items.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vipapp.appmark2.items.Method;
import com.vipapp.appmark2.items.design.attribute.BackgroundAttribute;
import com.vipapp.appmark2.items.design.attribute.HeightAttribute;
import com.vipapp.appmark2.items.design.attribute.WidthAttribute;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.ArrayUtils;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ClassUtils;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.Toast;
import com.vipapp.appmark2.utils.wrapper.mContext;
import com.vipapp.obfuscated.xml.XMLAttribute;
import com.vipapp.obfuscated.xml.XMLObject;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.vipapp.appmark2.utils.Const.ATTRIBUTES;

public class DesignObject {

    private XMLObject object;

    public String getName(){
        return getFullName().replaceAll(".+\\.", "");
    }
    public String getFullName(){
        return getViewClass().getName();
    }

    public View getView(){
        try{
            Class<? extends View> clazz = getViewClass();
            return ClassUtils.getInstance(clazz, Context.class, mContext.get());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public View setupView(XMLObject object, Project project){
        View view = getView();
        applyAttributes(view, object, project);
        return view;
    }

    private void applyAttributes(View v, XMLObject object, Project project){
        for(DesignAttribute attribute: ATTRIBUTES){
            attribute.apply(v, object, project.getResources());
            object.removeAttribute(attribute.getName());
        }
        applyInvokableAttributes(v, object, project.getResources());
        if(v instanceof ViewGroup)
            setupChildren((ViewGroup)v, object, project);
    }

    private Class[] availableArgs = new Class[]{ float.class, int.class, String.class, CharSequence.class };

    private void applyInvokableAttributes(View v, XMLObject object, Res res){
        for(XMLAttribute attribute: object.getAttributes()){
            String methodName = reformatToMethodName(attribute.getName());
            for(Class<?> arg: availableArgs){
                Method method = new Method(v, methodName, arg);
                if(method.exists()) {
                    try {
                        method.call(AttributesUtils.convert(attribute.getValue(), arg, res));
                        break;
                    } catch (Exception ignored){}
                }
            }
        }
    }

    private String reformatToMethodName(String attrName){
        attrName = attrName.replaceAll(".*:", "");
        StringBuilder method_name = new StringBuilder("set");
        for(String str: attrName.split("_")){
            method_name.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
        }
        return method_name.toString();
    }

    private static void setupLayoutParams(View view, XMLObject object, Res res){
        new HeightAttribute().apply(view, object, res);
        new WidthAttribute().apply(view, object, res);
    }

    private void setupChildren(ViewGroup viewGroup, XMLObject object, Project project){
        for(XMLObject child: object.getChildren()) {
            if(child.getName().equals("#text"))
                continue;
            DesignObject designObject = createNew(child);
            View v = designObject.setupView(child, project);
            viewGroup.addView(v);
            setupLayoutParams(v, object, project.getResources());
        }
    }

    public static DesignObject createNew(XMLObject object){
        DesignObject designObject = new DesignObject();
        designObject.object = object;
        return designObject;
    }

    private static String[] availablePackages = new String[]{
            "android.widget",
            "android.view"
    };

    @NonNull
    private Class<? extends View> getViewClass(){
        String name = object.getName();

        Class<? extends View> clazz = ClassUtils.getClass(name);
        if(clazz != null)
            return clazz;

        for(String packag: availablePackages){
            String widgetName = packag + "." + name;
            clazz = ClassUtils.getClass(widgetName);
            if(clazz != null)
                return clazz;
        }
        return View.class;
    }

    public XMLObject getXMLObject(){
        return object;
    }

}
