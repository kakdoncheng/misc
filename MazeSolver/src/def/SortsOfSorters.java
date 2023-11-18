package def;

/* SortsOfSorters
 * by John P. Spurgeon
 * updated on 19 April 2018
 * at: http://scribbledecobble.blogspot.com/2018/04/sorts-of-sorters.html
 *
 * Classic Sequences to Sort
 *
 * 1. A Multiset
 * Sequence: c a b d d a b d a d
 * See: TAOCP, vol. 3, sec. 5.1.2
 *
 * 2. A Set
 * Sequence: 503 087 512 061 908 170 897 275 653 426 154 509 612 677 765 703
 * See: TAOCP, vol. 3 (2nd ed.), p. 76
 */

// With a little help from my friends...

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* Counters */

interface ComparisonCounter
{
    int getComparisonCount();
}

interface ExchangeCounter
{
    int getExchangeCount();
}

// meter: n. a device that measures and records the quantity, degree, or rate of something.

interface SortMeter extends ComparisonCounter, ExchangeCounter
{
    boolean actuallyCountsComparisons();
    boolean actuallyCountsExchanges();
}

// Note: lhs => left hand side, rhs => right hand side.

final class CountingComparator<E> implements Comparator<E>, ComparisonCounter
{
    private Comparator<E> cmp;
    private int comparisonCount = 0;

    public CountingComparator(Comparator<E> cmp)
    {
        this.cmp = cmp;
    }
    public final int compare(E lhs, E rhs)
    {
        comparisonCount++;
        return cmp.compare(lhs, rhs);
    }
    public final int getComparisonCount()
    {
        return comparisonCount;
    }
}

/* abstracted adj.
 *
 *  1. Drawn off; separate, apart from 1660.
 *  2. Withdrawn from the contemplation of present objects; absent in mind 1643.
 * *3. Separated from the concrete, ideal, abstruse (replaced by ABSTRACT a. 4) - 1823.
 *
 *  The Evil one a. stood From his own evil MILT.
 *
 *  * disinterestedness.
 *
 *  Source: The Sorter Oxford English Dictionary on Historical Principles,
 *          Third Edition, Revised with Addenda (1947).
 */

class Sorters
{
    public static final String
        ABSTRACTED_SORTER = "Abstracted Sorter",
        ABSTRACTED_EXCHANGE_SORTER = "Abstracted Exchange Sorter",
        BAD_BUBBLE_SORTER = "Bad Bubble Sorter",
        BETTER_BUBBLE_SORTER = "Better Bubble Sorter",
        BEST_BUBBLE_SORTER = "Best Bubble Sorter",
        COLLECTIONS_SORTER = "Collections Sorter",
        COMPARISON_COUNTING_SORTER = "Comparison Counting Sorter",
        COMPARISON_EXCHANGE_COUNTING_SORTER = "Comparison/Exchange Counting Sorter",
        INSERTION_SORTER = "Insertion Sorter",
        MERGE_SORTER = "Merge Sorter",
        QUICK_SORTER = "Quick Sorter";
}

/* Abstract Sorters */

abstract class Sorter implements SortMeter
{
    protected abstract <E> void sortMethod(List<E> sequence, Comparator<E> cmp);

    public <E> void sort(List<E> sequence, Comparator<E> cmp)
    {
        this.sortMethod(sequence, cmp);
    }
    public final <E extends Comparable<E>> void sort(List<E> sequence)
    {
        Comparator<E> cmp = (E lhs, E rhs) -> lhs.compareTo(rhs);
        sort(sequence, cmp);
    }
    public final <E extends Comparable<E>> void sortReverse(List<E> sequence)
    {
        Comparator<E> cmp = (E lhs, E rhs) -> rhs.compareTo(lhs);
        sort(sequence, cmp);
    }
    public boolean actuallyCountsComparisons()
    {
        return false; // This SortMeter doesn't actually count comparisons. It's abstracted.
    }
    public int getComparisonCount()
    {
        return -1;
    }
    public boolean actuallyCountsExchanges()
    {
        return false; // This SortMeter doesn't actually count exchanges. It's abstracted.
    }
    public int getExchangeCount()
    {
        return -1;
    }
    public String toString()
    {
        return Sorters.ABSTRACTED_SORTER;
    }
}

abstract class ComparisonCountingSorter extends Sorter
{
    private int comparisonCount = 0;

    public final <E> void sort(List<E> sequence, Comparator<E> cmp)
    {
        CountingComparator<E> cc = new CountingComparator<E>(cmp);
        this.sortMethod(sequence, cc);
        comparisonCount = cc.getComparisonCount();
    }
    public final boolean actuallyCountsComparisons()
    {
        return true;
    }
    public final int getComparisonCount()
    {
        return comparisonCount;
    }
    public String toString()
    {
        return Sorters.COMPARISON_COUNTING_SORTER;
    }
}

abstract class ExchangeSorter extends Sorter
{
    protected <E> void swap(List<E> sequence, int i, int j)
    {
        E tmp = sequence.get(i);
        sequence.set(i, sequence.get(j));
        sequence.set(j, tmp);
    }
    public String toString()
    {
        return Sorters.ABSTRACTED_EXCHANGE_SORTER;
    }
}

abstract class ComparisonExchangeCountingSorter extends ExchangeSorter
{
    private int comparisonCount = 0, swapCount = 0;

    protected final <E> void swap(List<E> sequence, int i, int j)
    {
        super.swap(sequence, i, j);
        swapCount++;
    }
    public final <E> void sort(List<E> sequence, Comparator<E> cmp)
    {
        CountingComparator<E> cc = new CountingComparator<E>(cmp);
        this.sortMethod(sequence, cc);
        comparisonCount = cc.getComparisonCount();
    }
    public final boolean actuallyCountsComparisons()
    {
        return true;
    }
    public final int getComparisonCount()
    {
        return comparisonCount;
    }
    public final boolean actuallyCountsExchanges()
    {
        return true;
    }
    public final int getExchangeCount()
    {
        return swapCount;
    }
    public String toString()
    {
        return Sorters.COMPARISON_EXCHANGE_COUNTING_SORTER;
    }
}

/* Bubble Sort */

class BadBubbleSorter extends ComparisonExchangeCountingSorter
{
    // Exercise 1a: Fix the problem with the bubble sort implementation below.
    // done
    protected <E> void sortMethod(List<E> sequence, Comparator<E> cmp)
    {
        /*
         * This method sorts. But it sorts in the wrong direction!
         */

        final int n = sequence.size();
        final int lastIndex = n - 1;
        boolean hit = false;
        for (int count = 0; count < n; count++)
        {
            for (int i = 0; i < lastIndex-1 ; i++)
            {
                int j = i + 1;
                E lhs = sequence.get(i);
                E rhs = sequence.get(j);
                if (cmp.compare(lhs, rhs) > 0){
                    swap(sequence, i, j);
                    hit = true;
                }
            }
            if(!hit){
                break;
            }
        }
    }
    public String toString()
    {
        return Sorters.BAD_BUBBLE_SORTER;
    }
}

class BetterBubbleSorter extends BadBubbleSorter
{
    // Exercise 1b: Implement bubble sort again. Make it better this time.
    // Specifically: On each pass, do one fewer comparison than before.

    protected <E> void sortMethod(List<E> sequence, Comparator<E> cmp)
    {
        final int n = sequence.size();
        final int lastIndex = n - 1;
        for (int count = 0; count < n; count++)
        {
            for (int i = 0; i < lastIndex-1-count; i++)
            {
                if (cmp.compare(sequence.get(i), sequence.get(i+1)) > 0)
                {
                    swap(sequence, i, i+1);
                }
            }
        }
    }
    public String toString()
    {
        return Sorters.BETTER_BUBBLE_SORTER;
    }
}

final class BestBubbleSorter extends BetterBubbleSorter
{
    // Exercise 1c: Implement bubble sort one more time. Make it even better.
    // Specifically: Stop as soon as you know you're done.

    protected <E> void sortMethod(List<E> sequence, Comparator<E> cmp)
    {
        final int n = sequence.size();
        final int lastIndex = n - 1;
        boolean hit = false;
        for (int count = 0; count < n; count++)
        {
            for (int i = 0; i < lastIndex-1-count; i++)
            {
                if (cmp.compare(sequence.get(i), sequence.get(i+1)) > 0)
                {
                    swap(sequence, i, i+1);
                    hit = true;
                }
            }
            if(!hit){
                break;
            }
        }
    }
    public final String toString()
    {
        return Sorters.BEST_BUBBLE_SORTER;
    }
}

/* Insertion Sort */

final class InsertionSorter extends ComparisonExchangeCountingSorter
{
    /* Sorting an unordered sequence. */

    protected <E> void sortMethod(List<E> sequence, Comparator<E> cmp)
    {
        // Exercise 2a: Implement insertion sort.
        for(int c = 1; c < sequence.size(); c++){
            E next = sequence.remove(c);
            int n = c;
            for(int i = 0; i < c; i++){
                if (cmp.compare(sequence.get(i), next) > 0){
                    n = i;
                    break;
                }
            }
            sequence.add(n, next);
        }
    }

    /* Inserting into a sorted multiset. */

    public <E> void insertIntoMultiset(E element, List<E> sortedSeq, Comparator<E> cmp)
    {
        // Exercise 2b: Write code that inserts an element into a multiset (duplicates allowed).
        // Assume that the list passed to the method is in sorted order.
        int n = sortedSeq.size();
        for(int i = 0; i < sortedSeq.size(); i++){
            if (cmp.compare(sortedSeq.get(i), element) >= 0){
                n = i;
                break;
            }
        }
        sortedSeq.add(n, element);
    }
    public final <E extends Comparable<E>>
    void insertIntoMultiset(E element, List<E> sortedSeq)
    {
        Comparator<E> cmp = (E lhs, E rhs) -> lhs.compareTo(rhs);
        insertIntoMultiset(element, sortedSeq, cmp);
    }
    public final <E extends Comparable<E>>
    void insertIntoMultisetReverse(E element, List<E> sortedSeq)
    {
        Comparator<E> cmp = (E lhs, E rhs) -> rhs.compareTo(lhs);
        insertIntoMultiset(element, sortedSeq, cmp);
    }

    /* Inserting into a sorted set. */

   // Exercise 2c: Write code that inserts an element into a set (duplicates not allowed).
        // Assume that the list passed to the method is in sorted order.
   public <E> boolean insertIntoSet(E element, List<E> sortedSeq, Comparator<E> cmp) {
       int n = sortedSeq.size();
        for(int i = 0; i < sortedSeq.size(); i++){
            if (cmp.compare(sortedSeq.get(i), element) == 0){
              //  n = i;
                return false;
            }
            if (cmp.compare(sortedSeq.get(i), element) > 0 ){
                n = i;
                break;
            }
        }
        sortedSeq.add(n, element);

        return true ; // Modify this line if necessary.

    }

    public final <E extends Comparable<E>>
    boolean insertIntoSet(E element, List<E> sortedSeq)
    {
        Comparator<E> cmp = (E lhs, E rhs) -> lhs.compareTo(rhs);
        insertIntoSet(element, sortedSeq, cmp);
        return false;
    }
    public final <E extends Comparable<E>>
    boolean insertIntoSetReverse(E element, List<E> sortedSeq)
    {
        Comparator<E> cmp = (E lhs, E rhs) -> rhs.compareTo(lhs);
        insertIntoSet(element, sortedSeq, cmp);
        return false;
    }

    public String toString()
    {
        return Sorters.INSERTION_SORTER;
    }
}

/* Quick Sort */

final class QuickSorter extends ComparisonExchangeCountingSorter
{
    protected <E> void sortMethod(List<E> sequence, Comparator<E> cmp)
    { 
        sort(sequence, cmp, 0, sequence.size());
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    <E> void sort(List<E> arr, Comparator<E> cmp, int low, int high)
    {
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            E pivot = arr.get(high);
            int i = (low-1); // index of smaller element
            for (int j=low; j<high; j++)
            {
            // If current element is smaller than or
            // equal to pivot
                if (cmp.compare(arr.get(j), pivot) <= 0)
                {
                    i++;
                    // swap arr[i] and arr[j]
                    E temp = arr.get(i);
                    arr.set(i, arr.get(j));
                    arr.set(j, temp);
                }
            }
            // swap arr[i+1] and arr[high] (or pivot)
            E temp = arr.get(i+1);
            arr.set(i+1, arr.get(high));
            arr.set(high, temp);
            int pi = i+1;

            // Recursively sort elements before
            // partition and after partition
            sort(arr, cmp, low, pi-1);
            sort(arr, cmp, pi+1, high);
        }

    }

    public String toString()
    {
        return Sorters.QUICK_SORTER;
    }
}

/* Merge Sort */

final class MergeSorter extends ComparisonCountingSorter
{
    protected <E> void sortMethod(List<E> sequence, Comparator<E> cmp)
    {
        sort(sequence, cmp, 0, sequence.size()-1);
    }
    <E> void merge(List<E> arr, Comparator<E> cmp, int l, int m, int r)
    {
        //Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;


        /* Create temp arrays */
        ArrayList<E> L = new ArrayList<E>(n1);
        ArrayList<E> R = new ArrayList<E>(n2);

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
        	L.add(arr.get(l + i));
            //L.set(i, arr.get(l + i));
        for (int j=0; j<n2; ++j)
        	R.add(arr.get(m + 1+ j));
            //R.set(j, arr.get(m + 1+ j));


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (cmp.compare(L.get(i), R.get(j)) <= 0)
            {
                arr.set(k, L.get(i));
                i++;
            }
            else
            {
                arr.set(k, R.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr.set(k, L.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr.set(k, R.get(j));
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    <E> void sort(List<E> arr, Comparator<E> cmp, int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, cmp, l, m);
            sort(arr, cmp, m+1, r);

            // Merge the sorted halves
            merge(arr, cmp, l, m, r);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public String toString()
    {
        return Sorters.MERGE_SORTER;
    }
}

/* A sorter that uses Java's Collections.sort method to sort. */

final class CollectionsSorter extends ComparisonCountingSorter
{
    protected <E> void sortMethod(List<E> sequence, Comparator<E> cmp)
    {
        Collections.sort(sequence, cmp);
    }
    public String toString()
    {
        return Sorters.COLLECTIONS_SORTER;
    }
}

/* String Utilities */

class StringTester
{
    public static boolean isNumeric(String s)
    {
        try
        {
            Double.parseDouble(s);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    public static boolean areNumeric(String[] strings)
    {
        if (strings == null || strings.length == 0)
            return false;
        else
            for (String s : strings)
                if (!isNumeric(s))
                    return false;
        return true;
    }
}

class StringSorter {
    public static List<Double> sortAsNumbers(String[] strings, Sorter sorter) {
        List<Double> list = new LinkedList<Double>();
        for (String s : strings) list.add(Double.parseDouble(s));
        sorter.sort(list);
        return list;
    }
    public static List<Double> sortAsNumbersReverse(String[] strings, Sorter sorter) {
        List<Double> list = new ArrayList<Double>();
        for (String s : strings) list.add(Double.parseDouble(s));
        sorter.sortReverse(list);
        return list;
    }
    public static List<String> sortAsStrings(String[] strings, Sorter sorter) {
        List<String> list = new ArrayList<String>();
        for (String s : strings) list.add(s);
        sorter.sort(list);
        return list;
    }
    public static List<String> sortAsStringsReverse(String[] strings, Sorter sorter) {
        List<String> list = new LinkedList<String>();
        for (String s : strings) list.add(s);
        sorter.sortReverse(list);
        return list;
    }
    public static List<Double> insertNumbersIntoSet(String[] strings, InsertionSorter sorter) {
        List<Double> list = new LinkedList<Double>();
        for (String s : strings) sorter.insertIntoSet(Double.parseDouble(s), list);
        return list;
    }
    public static List<Double> insertNumbersIntoMultiset(String[] strings, InsertionSorter sorter) {
        List<Double> list = new ArrayList<Double>();
        for (String s : strings) sorter.insertIntoMultiset(Double.parseDouble(s), list);
        return list;
    }
    public static List<String> insertStringsIntoSet(String[] strings, InsertionSorter sorter) {
        List<String> list = new ArrayList<String>();
        for (String s : strings) sorter.insertIntoSet(s, list);
        return list;
    }
    public static List<String> insertStringsIntoMultiset(String[] strings, InsertionSorter sorter) {
        List<String> list = new LinkedList<String>();
        for (String s : strings) sorter.insertIntoMultiset(s, list);
        return list;
    }
}

/* Main Program: SortsOfSorters */

public class SortsOfSorters
{
    private static <E> void print(List<E> sequence, SortMeter counter, String label)
    {
        System.out.println(counter);
        System.out.print(label + ":");
        for (E element : sequence) System.out.print(" " + element);
        System.out.println();
        if (counter.actuallyCountsComparisons())
            System.out.println("Comparisons: " + counter.getComparisonCount());
        if (counter.actuallyCountsExchanges())
            System.out.println("Exchanges: " + counter.getExchangeCount());
        System.out.println();
    }
    public static void main(String[] args)
    {
        Sorter[] sortsOfSorters = {
            new BadBubbleSorter(), new BetterBubbleSorter(), new BestBubbleSorter(),
            new InsertionSorter(), new MergeSorter(), new QuickSorter(),
            new CollectionsSorter(),
        };
        if (StringTester.areNumeric(args))
        {
            for (Sorter sorter : sortsOfSorters)
            {
                print(StringSorter.sortAsNumbers(args, sorter), sorter, "Sorted");
                print(StringSorter.sortAsNumbersReverse(args, sorter), sorter, "Reversed");
                if (sorter.toString().equals(Sorters.INSERTION_SORTER))
                {
                    InsertionSorter iSrtr = (InsertionSorter) sorter;
                    print(StringSorter.insertNumbersIntoSet(args, iSrtr), iSrtr, "Set");
                    print(StringSorter.insertNumbersIntoMultiset(args, iSrtr), iSrtr, "Multiset");
                }
            }
        }
        else
        {
            for (Sorter sorter : sortsOfSorters)
            {
                print(StringSorter.sortAsStrings(args, sorter), sorter, "Sorted");
                print(StringSorter.sortAsStringsReverse(args, sorter), sorter, "Reversed");
                if (sorter.toString().equals(Sorters.INSERTION_SORTER))
                {
                    InsertionSorter iSrtr = (InsertionSorter) sorter;
                    print(StringSorter.insertStringsIntoSet(args, iSrtr), iSrtr, "Set");
                    print(StringSorter.insertStringsIntoMultiset(args, iSrtr), iSrtr, "Multiset");
                }
            }
        }
    }
}