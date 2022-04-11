package iguigui.leetcodes.strings;

import java.util.ArrayList;
import java.util.List;

public class LC68 {
    public static void main(String[] args) {
        String[] strings = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(new LC68().fullJustify(strings,16));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> strings = new ArrayList<>();

        List<List<String>> tmp = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int left = maxWidth;
            ArrayList<String> strings1 = new ArrayList<>();
            if (word.length() < left - 1) {
                strings1.add(word);
                left -= (word.length() + 1);
                continue;
            }
            if (i == word.length() - 1) {
                for (int j = 0; j < strings1.size() - 1; j++) {
                    strings1.set(j % (strings1.size() - 1),strings1.get(j % (strings1.size() - 1)) + " ");
                }
                strings1.set(strings1.size() - 1,strings1.get(strings1.size() - 1) + " ".repeat(left - strings1.size()));
            } else {
                for (int j = 0; j < left; j++) {
                    strings1.set(j % (strings1.size() - 1),strings1.get(j % (strings1.size() - 1)) + " ");
                }
            }
            tmp.add(strings1);
        }


        for (List<String> stringList : tmp) {
        }

        return strings;
    }

}
