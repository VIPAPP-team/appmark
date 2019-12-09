package com.vipapp.appmark2.util;

import com.vipapp.appmark2.callback.Predicate;
import com.vipapp.appmark2.callback.PushCallback;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings("WeakerAccess")
public class ClassUtils extends Utils {
    public static <T> void castIfInstance(Object object, Class<T> castClass, PushCallback<T> callback){
        if(castClass.isInstance(object))
            callback.onComplete(castClass.cast(object));
    }

    public static <ClassType> ClassType getInstance(Class<ClassType> classType, Object... constructor_args){
        try {
            if(constructor_args.length == 0) return classType.newInstance();
            else {
                Object[] classes_object = ArrayUtils.filter(constructor_args, Class.class::isInstance);
                Class[] classes = new Class[classes_object.length];
                //noinspection SuspiciousSystemArraycopy
                System.arraycopy(classes_object, 0, classes, 0, classes_object.length);
                Object[] args = ArrayUtils.filter(constructor_args, x -> !ArrayUtils.in_array(classes, x));
                return classType.getConstructor(classes).newInstance(args);
            }
        } catch (Exception e){
            throw new RuntimeException(e.getCause()     );
        }
    }
    public static Object getInstance(String className, Object... constructor_args){
        Class<?> clazz = getClass(className);
        if(clazz == null){
            throw new RuntimeException(new ClassNotFoundException());
        }
        return getInstance(clazz, constructor_args);
    }

    @Nullable
    public static <T> Class<T> getClass(String name){
        try{
            //noinspection unchecked
            return (Class<T>) Class.forName(name);
        } catch (ClassNotFoundException e){
            return null;
        }
    }

    public static boolean classExists(String className){
        try{
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e){
            return false;
        }
    }

    public static Class getClassFromFile(File classFile){
        String className = getClassFullnameFromFile(classFile);
        try {
            return Class.forName(className);
        } catch (Exception ignored) {}
        return null;
    }

    public static int resolveClassType(Class clazz){
        if(clazz != null){
            if(clazz.isInterface()) return Const.INTERFACE_TYPE; else
            if(clazz.isLocalClass()) return Const.LOCAL_CLASS_TYPE;
            return Const.CLASS_TYPE;
        }
        return Const.CLASS_NOT_FOUND_TYPE;
    }

    public static String getClassNameFromFile(File classFile){
        String className = classFile.getName();

        className = className.replaceAll("\\.class", "");
        className = className.substring(!className.contains("$") ? 0: className.indexOf("$") + 1);

        return className;
    }

    public static String getClassFullnameFromFile(File classFile){
        return classFile.getPath().replaceAll("\\.class", "").replaceAll("[/$]", ".");
    }

    public static <T> T getOrDefault(T object, T Default, Predicate<T> condition){
        return condition.test(object)? object: Default;
    }

    @Nullable
    private static Method getMethod(Class<?> clazz, String method_name, Class[] args){
        try{
            return clazz.getMethod(method_name, args);
        } catch (NoSuchMethodException|SecurityException ignored){
            return null;
        }
    }
    @Nullable
    private static Method getMethod(Object object, String method_name, Class[] args){
        return getMethod(object.getClass(), method_name, args);
    }

    public static boolean hasMethod(Class<?> clazz, String method_name, Class[] args){
        return getMethod(clazz, method_name, args) != null;
    }
    public static boolean hasMethod(Object object, String method_name, Class[] args){
        return hasMethod(object.getClass(), method_name, args);
    }

    public static boolean callMethod(Object object, String method_name, Class[] args_classes, Object[] args){
        if(!hasMethod(object, method_name, args_classes))
            return false;

        @NonNull
        Method method = Objects.requireNonNull(getMethod(object, method_name, args_classes));

        try {
            method.invoke(object, args);
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }

        return true;
    }
    public static boolean callMethod(Object object, String method_name, Object[] args){
        Class[] args_classes = new Class[args.length];
        args_classes = ArrayUtils.map(args, args_classes, Object::getClass);

        return callMethod(object, method_name, args_classes, args);
    }

}
