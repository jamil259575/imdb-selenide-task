package com.example.utils;

import com.example.config.Config;

import java.time.Duration;

public class Utils {
    public static Duration defaultTimeout() {
        return Duration.ofMillis(Config.getInt("timeout"));
    }
}
