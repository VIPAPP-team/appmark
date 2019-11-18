package com.vipapp.appmark2.utils.thread;

import com.vipapp.appmark2.utils.ActivityStarter;
import com.vipapp.appmark2.utils.Thread;

public class IntroductionThread extends Thread {
    private static long WAIT_TIME = 1500;

    public void run() {
        sleep(WAIT_TIME);
        ActivityStarter.go("MainActivity", true);
    }
}
