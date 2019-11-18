package com.vipapp.appmark2.utils;
import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.items.ClassItem;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.*;

public class AndroidInspector{

	private static boolean isLoading = false;

	private static Enumeration<JarEntry> e;
	private static ArrayList<ClassItem> classes;
	
	public static void getAllClasses(String pathToAndroidJar, PushCallback<ArrayList<ClassItem>> callback){
		if(!isLoading) {
			if (classes != null) callback.onComplete(classes);
			else {
				isLoading = true;
				try {
					classes = new ArrayList<>();
					JarFile jar = new JarFile(pathToAndroidJar);
					e = jar.entries();
					loop();
					callback.onComplete(classes);
				} catch (IOException e) {}
				isLoading = false;
			}
		}
	}
	
	private static void loop(){
		while(e.hasMoreElements()){
			JarEntry entry = e.nextElement();
			File entryFile = new File(entry.getName());
			String className = entryFile.getName();
			if(className.endsWith(".class")){
				className = ClassUtils.getClassNameFromFile(entryFile);
				String classImport = ClassUtils.getClassFullnameFromFile(entryFile);
				
				Class clazz = ClassUtils.getClassFromFile(entryFile);
				int classType = ClassUtils.resolveClassType(clazz);
				
				classes.add(new ClassItem(className, classImport, classType));
			}
		}
	}
	
}
