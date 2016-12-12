package com.example.hemel007.services;

/**
 * Created by hemel99 on 9/18/2016.
 */
public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = "com.example.hemel007.services.action.main";
        public static String INIT_ACTION = "com.example.hemel007.services.action.init";
        public static String PREV_ACTION = "com.example.hemel007.services.action.prev";
        public static String PLAY_ACTION = "com.example.hemel007.services.action.play";
        public static String NEXT_ACTION = "com.example.hemel007.services.action.next";
        public static String STARTFOREGROUND_ACTION = "com.example.hemel007.services.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.example.hemel007.services.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}
