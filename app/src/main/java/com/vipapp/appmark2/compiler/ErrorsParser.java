package com.vipapp.appmark2.compiler;

import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.util.wrapper.Str;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;

import static com.vipapp.appmark2.util.Const.AAPT_ERROR_PATTERN;
import static com.vipapp.appmark2.util.Const.JAVA_ERROR_PATTERN;

public class ErrorsParser {
    public static int TYPE_JAVA = 0;
    public static int TYPE_AAPT = 1;

    private static final ErrorType[] types = new ErrorType[]{
            // Java error type
            new ErrorType() {
                Pattern getPattern() { return JAVA_ERROR_PATTERN; }
                Error parse(Matcher matcher) {
                    int type = matcher.group(1).equals("ERROR")? Error.TYPE_ERROR: Error.TYPE_WARNING;
                    File file = new File(matcher.group(2));
                    int line_number = Integer.parseInt(matcher.group(3));
                    String message = matcher.group(4);
                    return new Error(type,  line_number, file, message);
                }
            },
            // Aapt error parser
            new ErrorType() {
                Pattern getPattern() { return AAPT_ERROR_PATTERN; }
                Error parse(Matcher matcher) {
                    int type = Error.TYPE_ERROR;
                    File file = new File(matcher.group(1));
                    int line_number = Integer.parseInt(matcher.group(2));
                    String message = matcher.group(3);
                    return new Error(type, line_number, file, message);
                }
            }
    };

    private ArrayList<Error> errors = new ArrayList<>();

    public ErrorsParser(int errors_type, String text){
        ErrorType type = types[errors_type];
        Matcher matcher = type.getPattern().matcher(text);
        while(matcher.find()){
            errors.add(type.parse(matcher));
        }
    }

    public ArrayList<Error> getErrors(){
        return errors;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Error error: errors)
            builder.append(error.toString()).append("\n");
        return builder.toString();
    }

    private static abstract class ErrorType{
        abstract Pattern getPattern();
        abstract Error parse(Matcher matcher);
    }

    @SuppressWarnings("WeakerAccess")
    public static class Error{
        public static final int TYPE_ERROR = 0;
        public static final int TYPE_WARNING = 1;

        private int type;
        private int line_number;
        private File file;
        private String message;

        Error(int type, int line_number, File file, String message) {
            this.type = type;
            this.line_number = line_number;
            this.file = file;
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getLineNumber() {
            return line_number;
        }

        public void setLineNumber(int line_number) {
            this.line_number = line_number;
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @NonNull
        @Override
        public String toString() {
            return String.format(Locale.getDefault(), "type: %1$d\nline: %2$d\nfile: %3$s\nmessage: %4$s",
                    type, line_number, file.getAbsolutePath(), message);
        }
    }
}
