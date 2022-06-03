package com.example.usd;

import java.util.Random;

public class SearchingClass {

    public String divine(){
        Random rand = new Random();
        int name = rand.nextInt(Bible.bible.length);
        int firstnum = rand.nextInt(Bible.bible[name].length);
        int secondnum = rand.nextInt(Bible.bible[name][firstnum].length);
        return Bible.bible[name][firstnum][secondnum];
    }

    public int simpleTextSearch(char[] pattern, char[] text) {
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
