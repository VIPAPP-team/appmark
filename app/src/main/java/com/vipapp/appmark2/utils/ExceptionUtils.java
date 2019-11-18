package com.vipapp.appmark2.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
    public static String toString(Throwable throwable){
        StringWriter stackTrace = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stackTrace));
        return stackTrace.toString();
    }
}
