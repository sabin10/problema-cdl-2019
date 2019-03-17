import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scannerInput = new Scanner(System.in);
        while (scannerInput.hasNext()) {
            String wordBeginning = scannerInput.next();
            if (wordBeginning.equals("/exit"))
                break;

            List<Word> wordList = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                Scanner scannerFile = null;
                try {
                    scannerFile = new Scanner(new File(args[i]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (scannerFile.hasNextLine()) {
                    Scanner scannerFile2 = new Scanner(scannerFile.nextLine());
                    while (scannerFile2.hasNext()) {
                        String wordFromFile = refractorString(scannerFile2.next());
                        if (wordContainsInput(wordFromFile, wordBeginning)) {
                            if (!listContainsWord(wordList, wordFromFile)) {
                                wordList.add(new Word(wordFromFile));
                                wordList.get(getPositionInList(wordList, wordFromFile)).addApparanceOnArticle(i + 1);
                            } else {
                                wordList.get(getPositionInList(wordList, wordFromFile)).addApparanceOnArticle(i + 1);
                            }
                        }
                    }
                }
            }
            if (wordList.isEmpty()) {
                System.out.println("No suggestions...");
            } else {
                Collections.sort(wordList);
                int limitList = (wordList.size() < 5) ? wordList.size() : 5;
                for (int j = 0; j < limitList; j++) {
                    wordList.get(j).printOut();
                }
            }
            System.out.print("\n");
        }
    }

    public static boolean wordContainsInput(String source, String input) {
        if (source.toLowerCase().indexOf(input) == -1)
            return false;
        if (source.toLowerCase().indexOf(input) == 0)
            return true;
        String [] parts = source.split("-");
        for (String part : parts) {
            if (part.startsWith(input))
                return true;
        }
        return false;
    }

    public static String refractorString(String source) {
        if (source.length() == 0 || !Character.isLetter(source.charAt(0)))
            return "";
        int i = 0;
        while (i < source.length() && (Character.isLetter(source.charAt(i)) || source.charAt(i) == '-')) {
            i++;
        }
        if (i >= source.length())
            return source.toLowerCase();
        return source.substring(0, i).toLowerCase();
    }

    public static boolean listContainsWord(List<Word> list, String word) {
        return list.stream().map(Word::getWord).filter(word::equals).findFirst().isPresent();
    }

    public static int getPositionInList(List<Word> list, String word) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getWord().equals(word))
                return i;
        }
        return -1;
    }
}
