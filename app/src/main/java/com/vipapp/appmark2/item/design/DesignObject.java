package com.vipapp.appmark2.item.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.callback.Mapper;
import com.vipapp.appmark2.item.BuiltView;
import com.vipapp.appmark2.item.Method;
import com.vipapp.appmark2.item.design.attribute.HeightAttribute;
import com.vipapp.appmark2.item.design.attribute.LayoutGravityAttribute;
import com.vipapp.appmark2.item.design.attribute.WeightAttribute;
import com.vipapp.appmark2.item.design.attribute.WidthAttribute;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.ResourcesUtils;
import com.vipapp.appmark2.util.ViewUtils;
import com.vipapp.appmark2.util.wrapper.mContext;
import com.vipapp.appmark2.xml.XMLAttribute;
import com.vipapp.appmark2.xml.XMLObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;

import static com.vipapp.appmark2.util.Const.ATTRIBUTES;
import static com.vipapp.appmark2.util.Const.STYLE_ATTR;

@SuppressWarnings("WeakerAccess")
public class DesignObject {

    private XMLObject object;

    private static String[] availablePackages = new String[]{
            "android.widget",
            "android.view"
    };

    private Class[] availableArgs = new Class[]{
            float.class,
            int.class,
            boolean.class,
            String.class,
            CharSequence.class,
    };

    public String getName(){
        return getFullName().replaceAll(".+\\.", "");
    }
    public String getFullName(){
        return getViewClass().getName();
    }

    public View getView(){
        try{
            Class<? extends View> clazz = getViewClass();
            String style = object.getNamedAttribute(STYLE_ATTR).getValue();
            if(style == null)
                return ClassUtils.getInstance(clazz, Context.class, mContext.get());
            return ClassUtils.getInstance(clazz,
                            Context.class, AttributeSet.class, int.class,
                            mContext.get(), null, ResourcesUtils.getAndroidIdentifier(style));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static void setupLayoutParams(View view, XMLObject object, Res res){
        WeightAttribute weightAttribute = new WeightAttribute();
        weightAttribute.apply(view, object, res);
        LayoutGravityAttribute gravityAttribute = new LayoutGravityAttribute();
        gravityAttribute.apply(view, object, res);
        ViewUtils.setLinearLayoutParams(view,
                AttributesUtils.valueToFloat(weightAttribute.getValue()),
                AttributesUtils.valueToGravity(gravityAttribute.getValue()));
        new HeightAttribute().apply(view, object, res);
        new WidthAttribute().apply(view, object, res);
    }

    private void setupChildren(ViewGroup viewGroup, XMLObject object, Project project, Mapper<BuiltView, View> mapper){
        for(XMLObject child: object.getChildren()) {
            if(child.getName().equals("#text"))
                continue;
            DesignObject designObject = createNew(child);
            View v = designObject.setupView(child, project, mapper);
            viewGroup.addView(v);
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

    private void applyInvokableAttributes(View v, XMLObject object, Res res, ArrayList<String> applied){
        for(XMLAttribute attribute: object.getAttributes()){
            if(!applied.contains(attribute.getName())) {
                String methodName = reformatToMethodName(attribute.getName());
                for (Class<?> arg: availableArgs) {
                    Method method = new Method(v, methodName, arg);
                    if (method.exists()) {
                        try {
                            method.call(AttributesUtils.convert(attribute.getValue(), arg, res));
                            break;
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }
    }

    private void applyAttributes(View v, XMLObject object, Project project, Mapper<BuiltView, View> mapper){
        ArrayList<String> applied = new ArrayList<>();
        for(DesignAttribute attribute: ATTRIBUTES){
            attribute.apply(v, object, project.getRes());
            applied.add(attribute.getName());
        }
        applyInvokableAttributes(v, object, project.getRes(), applied);
        if(v instanceof ViewGroup)
            setupChildren((ViewGroup)v, object, project, mapper);
    }

    public View setupView(XMLObject object, Project project, Mapper<BuiltView, View> mapper) {
        View view = getView();
        applyAttributes(view, object, project, mapper);
        setupLayoutParams(view, object, project.getRes());
        view = mapper == null? view: mapper.map(new BuiltView(view, object));
        setupLayoutParams(view, object, project.getRes());
        return view;
    }

    public static DesignObject createNew(XMLObject object){
        DesignObject designObject = new DesignObject();
        designObject.object = object;
        return designObject;
    }

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
