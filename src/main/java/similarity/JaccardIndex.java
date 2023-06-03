/**
 * The `similarity` package provides utility classes for calculating similarity measures between sets or collections of elements.
 * It includes methods to calculate the Jaccard Index, a measure of similarity between two sets.
 * 
 * The main class in this package is:
 * - `JaccardIndex`: A utility class that provides a static method to calculate the Jaccard Index between two sets.
 * 
 * The package utilizes the Set data structure to represent sets of elements and performs set operations to calculate the similarity measure.
 * 
 * Example usage:
 * ```java
 * Set<String> setA = new HashSet<>();
 * setA.add("apple");
 * setA.add("banana");
 * setA.add("orange");
 * 
 * Set<String> setB = new HashSet<>();
 * setB.add("banana");
 * setB.add("cherry");
 * setB.add("orange");
 * 
 * double jaccardIndex = JaccardIndex.calculateJaccardIndex(setA, setB);
 * System.out.println("Jaccard Index: " + jaccardIndex);
 * ```
 * 
 * Authors:
 * - Abdalrhman Fawzy
 */

package similarity;

import java.util.HashSet;
import java.util.Set;


public class JaccardIndex {

    public static double calculateJaccardIndex(Set<String> setA, Set<String> setB) {
        Set<String> intersectionSet = new HashSet<>(setA);
        intersectionSet.retainAll(setB);

        Set<String> unionSet = new HashSet<>(setA);
        unionSet.addAll(setB);

        int intersectionSize = intersectionSet.size();
        int unionSize = unionSet.size();

        return (double) intersectionSize / unionSize;
    }

    public static void main(String[] args) {
        Set<String> setA = new HashSet<>();
        setA.add("apple");
        setA.add("banana");
        setA.add("orange");

        Set<String> setB = new HashSet<>();
        setB.add("banana");
        setB.add("cherry");
        setB.add("orange");

        double jaccardIndex = calculateJaccardIndex(setA, setB);
        System.out.println("Jaccard Index: " + jaccardIndex);
    }
}

