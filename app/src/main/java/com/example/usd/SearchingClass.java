package com.example.usd;

public class SearchingClass {

    public static String divineInText(String strQuestion){
        return "1";
        //TODO
    }

    public static int findFirstIndex(int i, String bible){
        return -1;
        //TODO
    }

    public static int findLastIndex(int i, String bible){
        return -1;
        //TODO
    }


    public static int simpleTextSearch(char[] pattern, char[] text) {
        int patternSize = pattern.length;
        int textSize = text.length;

        int i = 0;

        while ((i + patternSize) <= textSize) {
            int j = 0;
            while (text[i + j] == pattern[j]) {
                j += 1;
                if (j >= patternSize)
                    return i;
            }
            i += 1;
        }
        return -1;
    }

}
