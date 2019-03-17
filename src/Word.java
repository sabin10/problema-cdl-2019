import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word implements Comparable<Word>{
    private String word;
    private List<Integer> apparancesOnArticle;

    public Word(String word) {
        this.word = word;
        apparancesOnArticle = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public void addApparanceOnArticle(int i) {
        if (!apparancesOnArticle.contains(i))
            apparancesOnArticle.add(i);
    }

    public void printOut() {
        if (apparancesOnArticle.size() > 1) {
            Collections.sort(apparancesOnArticle);
        }
        System.out.print(word + " : ");
        for (int apparance : apparancesOnArticle) {
            System.out.print(apparance + " ");
        }
        System.out.print("\n");
    }

    @Override
    public int compareTo(Word that) {
        return this.getWord().compareTo(that.getWord());
    }
}
