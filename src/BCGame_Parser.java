import java.io.*;
import java.util.*;

/**
 * Created by james on 28/07/2017.
 */
public class BCGame_Parser {

    public static void main(String[] args) throws IOException {
        String inputFile = args[0];
        final int ARRAY_OFFSET = 3;
        final int MAX_ISO_LEN = 12;

        BufferedReader in = null;
        BufferedWriter[] writers = new BufferedWriter[MAX_ISO_LEN-ARRAY_OFFSET];

        try {
            in = new BufferedReader(new FileReader(inputFile));
            for (int i = 0; i < MAX_ISO_LEN - ARRAY_OFFSET; i++) {
                String outFile = (i + ARRAY_OFFSET) + "_letter_isograms.txt";
                writers[i] = new BufferedWriter(new FileWriter(outFile));
            }


            String word;
            while ((word = in.readLine()) != null) {
                if (word.length() > 2 && word.length() < MAX_ISO_LEN && isIsogram(word)) {
                    writers[word.length()-ARRAY_OFFSET].write(word);
                    writers[word.length()-ARRAY_OFFSET].newLine();
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
            for (BufferedWriter bw : writers) {
                if (bw != null) {
                    bw.close();
                }
            }
        }
    }

    private static boolean isIsogram(String word) {
        HashMap<Character, Boolean> letterSeen = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (Character c : word.toCharArray()) {
            char tempChar = Character.toLowerCase(c);
            if (!set.add(tempChar)) {
                return false;
            }
        }

        return true;
    }


}
