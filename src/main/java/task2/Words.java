package task2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 04.10.2016.
 */
public class Words {

    public void stringManipulation(String str){
        Set<Character> uniqueChars = new HashSet<Character>();
        int startIndex = 0;
        int longestSequenceStartIndex = 0;
        int longestSequenceLength = 0;
        for (int i=0; i < str.length(); i++) {

            for (int j=startIndex; j < i; j++) {
                if (str.charAt(j) == str.charAt(i)) {
                    int sequenceLength = i - startIndex;
                    if (sequenceLength > longestSequenceLength) {
                        longestSequenceLength = sequenceLength;
                        longestSequenceStartIndex = startIndex;
                    }
                    startIndex = j + 1;
                    break;
                }
            }
            uniqueChars.add(str.charAt(i));
        }

        int sequenceLength = str.length() - startIndex;
        if (sequenceLength > longestSequenceLength) {
            longestSequenceLength = sequenceLength;
            longestSequenceStartIndex = startIndex;
        }

        if (longestSequenceLength == uniqueChars.size()) {
            System.out.printf("substring = \"%s\", startIndex = %s\n",
                    str.substring(longestSequenceStartIndex, longestSequenceStartIndex + longestSequenceLength),
                    longestSequenceStartIndex);
        } else {
            System.out.printf("substring not found. Longest unique chars sequence = %s, all unique chars = %s\n",
                    str.substring(longestSequenceStartIndex, longestSequenceStartIndex + longestSequenceLength),
                    uniqueChars.toString());
        }
    }

    public static void main(String[] args) {
        String firstString = "abbcdabc";
        String secondString = "cbacdcbc";
        Words words = new Words();
        words.stringManipulation(firstString);

    }
}
