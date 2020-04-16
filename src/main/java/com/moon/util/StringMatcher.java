package com.moon.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringMatcher {

    private static String s = "PC;IIP:192.168.3.254;LIP:192.168.3.39;MAC:4CEDFBC62028;HD:WD-WCC6Y1FV85P0;PCN:DESKTOP-4QSO54K;CPU:BFEBFBFF000906EA;PI:C,NTFS,120.01;VOL:881B9377";

    public static void main(String[] args) {
        System.out.println(getLIP1());
        System.out.println(getLIP2());
    }

    public static String getLIP1() {
        String result = "";
        String regex = "LIP:.+?;";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        while (m.find()) {
            result = m.group().replace("LIP:", "").replace(";", "");
        }
        return result;
    }

    public static String getLIP2() {
        if (s.contains("LIP:")) {
            Optional<String> first = Arrays.stream(s.split(";")).filter(s1 -> s1.startsWith("LIP:")).findAny();
            if (first.isPresent()) {
                return first.get().replace("LIP:","");
            }
        }
        return "failed";
    }
}
