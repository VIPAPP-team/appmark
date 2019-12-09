package com.vipapp.appmark2.util;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import android.webkit.MimeTypeMap;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.wrapper.mContext;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.channels.FileChannel;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static com.vipapp.appmark2.util.Const.APPMARK_CACHE;
import static com.vipapp.appmark2.util.Const.PROJECT_STORAGE;

@SuppressWarnings("WeakerAccess")
public class FileUtils {

    public static void setupFiles(){
        File projects = new File(PROJECT_STORAGE);
        File cache = new File(APPMARK_CACHE);

        File projects_nomedia = new File(projects, ".nomedia");
        File cache_nomedia = new File(cache, ".nomedia");

        FileUtils.refresh(projects_nomedia, false);
        FileUtils.refresh(cache_nomedia, false);
    }

    public static String readFileUI(File file){
        StringBuilder returnable = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null){
                returnable.append(line).append('\n');
            }

            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return TextUtils.replaceLast(returnable.toString(), "\n", "");
    }

    public static void readFile(File file, PushCallback<String> result){
        Thread.start(() -> {
            String returnable = readFileUI(file);
            Thread.ui(() -> result.onComplete(returnable));
        });
    }

    public static String readAssetsUI(String filename){
        StringBuilder returnable = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(ContextUtils.context.getAssets().open(filename)));
            String line;

            while((line = br.readLine()) != null){
                returnable.append(line).append('\n');
            }

            br.close();
        } catch (IOException e) {
            return null;
        }
        return returnable.toString();
    }

    public static void writeFileUI(File file, String string){
        try {
            FileUtils.refresh(file, false);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(string);
            bw.close();
        } catch(Exception ignored){}
    }

    public static void writeFile(File file, String string){
        Thread.start(() -> writeFileUI(file, string));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean refresh(@Nullable File file, boolean isDirectory){
        try {
            if (file == null)
                return true;
            if (isDirectory)
                file.mkdirs();
            else {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static File getFileFromInputStream(File location, InputStream is){
        OutputStream outputStream;
        try {
            outputStream = new FileOutputStream(location);
            IOUtils.copy(is, outputStream);
            outputStream.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return location;
    }

    public static void deleteFile(File file){
        if(!file.delete()) {
            for (File file_child : file.listFiles())
                deleteFile(file_child);
            deleteFile(file);
        }
    }

    public static String getFileName(File file){
        return TextUtils.replaceLast(file.getName(), "\\..+", "");
    }

    public static String resolveMimeType(File file){
        try {
            if(file.getName().matches(Const.TEXT_FILE_REGEX))
                return "text/custom_appmark";
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            String mime = URLConnection.guessContentTypeFromStream(is);
            if(mime != null) return mime;
            return getMimeTypeFromName(file.getName());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static String getMimeTypeFromName(String fileUrl) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
        String mymeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        return mymeType == null? "text": mymeType;
    }

    public static String getFileExtension(File file){
        String name = file.getName();
        return name.indexOf('.') == -1? "": name.replaceAll(".*\\.", "");
    }

    public static String getRelativePath(File file, File to){
        return to.toURI().relativize(file.toURI()).getPath();
    }

    public static boolean openFileInExternal(File file){
        try {
            android.content.Context context = mContext.get();

            MimeTypeMap myMime = MimeTypeMap.getSingleton();
            Intent newIntent = new Intent(Intent.ACTION_VIEW);
            String mimeType = myMime.getMimeTypeFromExtension(getFileExtension(file));

            Uri fileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);

            context.grantUriPermission(context.getPackageName(), fileUri, FLAG_GRANT_READ_URI_PERMISSION);

            newIntent.setDataAndType(fileUri, mimeType);
            newIntent.setFlags(FLAG_GRANT_READ_URI_PERMISSION|FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(newIntent);
        } catch (Exception e){
            Toast.show(e.toString());
            return false;
        }
        return true;
    }

    public static void moveFile(File source, File destination){
        moveOrCopy(source, destination, true);
    }
    public static void copyFile(File source, File destination){
        moveOrCopy(source, destination, false);
    }
    private static void moveOrCopy(File source, File destination, boolean delete_source){
        FileUtils.refresh(destination, false);
        try{
            FileChannel outChannel = new FileOutputStream(destination).getChannel();
            FileChannel inputChannel = new FileInputStream(source).getChannel();
            inputChannel.transferTo(0, inputChannel.size(), outChannel);
            inputChannel.close();
            outChannel.close();
            if(delete_source) //noinspection ResultOfMethodCallIgnored
                source.delete();
        } catch (IOException ignored){}
    }

    public static boolean checkFileName(String filename){
        return filename.matches(Const.FILENAME_REGEX);
    }

    public static File changeName(File source, String name){
        File target = new File(source.getParentFile(), name);
        return source.renameTo(target)? target: source;
    }

    public static boolean isChild(File file, File to) {
        return file != null && (file.equals(to) || isChild(file.getParentFile(), to));
    }

    public static boolean isFileLayout(File file, Project project){
        return isChild(file, project.getLayoutDir());
    }
    public static boolean isFileRes(File file, Project project){
        return file.equals(project.getResDir());
    }
    public static boolean isFileValues(File file, Project project){
        return file.getName().matches(Const.VALUES_REGEX) && isFileRes(file.getParentFile(), project);
    }

    public static boolean isFileStrings(File file, Project project){
        return file.getName().equals("strings.xml") && isFileValues(file.getParentFile(), project);
    }

    public static void writeDefaultFileText(File file, Project project){
        writeFileUI(file, getDefaultTextForFile(file, project));
    }

    public static String getDefaultTextForFile(File file, Project project){
        if(isFileValues(file.getParentFile(), project)) {
            String default_file_path = "texts/values_default_texts/" + file.getName();
            String default_text = readAssetsUI(default_file_path);

            if(default_text != null)
                return default_text;

            return readAssetsUI("texts/default_values.xml");
        }
        return getDefaultTextWithExtension(file, project);
    }

    private static String getDefaultTextWithExtension(File file, Project project){
        String ext = getFileExtension(file);
        String path_to_default = Const.ext_to_default_texts.get(ext);

        if(path_to_default == null)
            return "";

        String default_text = readAssetsUI(path_to_default);

        if(ext.equals("java"))
            return String.format(default_text, project.getPackage(file), getFileName(file));

        return default_text;
    }

}
