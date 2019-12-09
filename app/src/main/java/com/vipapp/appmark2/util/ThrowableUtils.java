package com.vipapp.appmark2.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ThrowableUtils {
    public static String toString(Throwable throwable){
        StringWriter stackTrace = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stackTrace));
        return stackTrace.toString();
    }
}
