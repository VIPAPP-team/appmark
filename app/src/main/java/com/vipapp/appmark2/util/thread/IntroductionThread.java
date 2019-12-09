package com.vipapp.appmark2.util.thread;

import com.vipapp.appmark2.util.ActivityStarter;
import com.vipapp.appmark2.util.Thread;

public class IntroductionThread extends Thread {
    private static long WAIT_TIME = 1500;

    public void run() {
        sleep(WAIT_TIME);
        ActivityStarter.go("MainActivity", true);
    }
}
