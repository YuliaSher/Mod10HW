import java.io.File;
import java.io.IOException;
import java.util.*;


public class WordsFrequency {
    public static void main(String[] args) {

        WordsFrequency wFr = new WordsFrequency();
        String read = wFr.read(new File("words.txt"));
        wFr.reverseSort(read);
    }

    public String read(File file) {
    // reading from file into a String
        StringJoiner sj = new StringJoiner(" ");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                sj.add(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sj.toString();
    }

    public void reverseSort(String read) {
        read = read.replaceAll("\\s+", " "); //deleting all double+ whitespaces
        String[] sorted = read.split(" "); //getting an array from it
        Arrays.sort(sorted); //sorting array
//      System.out.println("Arrays.toString(sorted) = " + Arrays.toString(sorted)); //checking how it works

        //creating new ArrayList of objects of inner class Frequency
        List<Frequency> frList = new ArrayList<>();
        int count = 1;
        int k = 0;
        String name = sorted[0];
        Frequency fr = new Frequency(name, count);

        //filling in the ArrayList with data from sorted array (name - a unique word in array, count - its frequency)
        for (int i = 0; i < sorted.length; i++) {
            name = sorted[i];
            if ((i == sorted.length - 1) && !sorted[i - 1].equals(sorted[i])) {
                //for the last element that doesn't repeat
                frList.add(k, new Frequency(name, count));
                k++;
            } else if ((i == sorted.length - 1) && sorted[i - 1].equals(sorted[i])) {
                //for the last element that repeats
                frList.add(k, new Frequency(name, count));
                k++;
            } else if (sorted[i].equals(sorted[i + 1])) {
                //for all other repeating elements in array
                count++;
            } else {
                //for all elements in array (except the last one): adding to ArrayList
                frList.add(k, new Frequency(name, count));
                count = 1;
                k++;
            }
            Collections.sort(frList);
        }
        frList.forEach(System.out::println);
    }

    static class Frequency implements Comparable<Frequency> {
        private String name;
        private int count;

        public Frequency(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return name + " " + count;
        }

        @Override
        public int compareTo(Frequency o) {
            int comp = ((Frequency) o).getCount();
            return comp - this.count;
        }
    }
}