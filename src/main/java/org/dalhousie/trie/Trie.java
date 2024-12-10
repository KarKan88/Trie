package org.dalhousie.trie;

import org.dalhousie.trie.mapping.CharacterNaturalMapping;
import org.dalhousie.trie.store.IntegerArrayList;
import org.dalhousie.trie.store.IntegerList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class Trie {
    //Intro
    //Problem statment
    //Def related to
    //Benchmark and explained the prob
// Existing Soln
    //New Soln
    //Conclusion
    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void Trie() throws IOException {
        CharacterNaturalMapping cnm = CharacterNaturalMapping.getInstance();
        File folder = new File("/Users/karkan/Documents/MACS/ADS/code/ADSProject/src/main/resources/archive");
        File[] listOfFiles = folder.listFiles();

        HashSet<String> stopWords = new HashSet<String>(Files.readAllLines(Path.of("/Users/karkan/Documents/MACS/ADS/code/ADSProject/src/main/resources/stop-words.txt"), StandardCharsets.ISO_8859_1));
        AbstractDoubleArrayTrie trie = new DoubleArrayTrieImpl(cnm.getN());
        int nat;

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = Files.readString(Path.of(file.getAbsolutePath()), StandardCharsets.ISO_8859_1);
                content = removeNonAlphanumeric(content);

                String[] contentArr = content.toLowerCase().split("\\s+");
                for (int k = 0; k < contentArr.length; k++) {
                    IntegerList list = new IntegerArrayList();
                    for (Character c : contentArr[k].toCharArray()) {
                        nat = cnm.toNatural(c);
                        list.add(nat);
                    }
                    for (int j = 0; j < list.size(); j++) {
                        list.set(j, list.get(j));
                    }
                    try {
                        trie.addToTrie(list);
                    } catch (Exception ignored) {

                    }
                }
            }
        }

        String toMap1 = "Atheist".toLowerCase();
        IntegerList list1 = new IntegerArrayList();
        for (Character c : toMap1.toCharArray()) {
            nat = cnm.toNatural(c);
            list1.add(nat);
        }

        String toMap2 = "Scientific".toLowerCase();
        IntegerList list2 = new IntegerArrayList();
        for (Character c : toMap2.toCharArray()) {
            nat = cnm.toNatural(c);
            list2.add(nat);
        }
        for (int i = 0; i < list1.size(); i++) {
            list1.set(i, list1.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            list2.set(i, list2.get(i));
        }
        assert trie.containsPrefix(list1) == SearchResult.PERFECT_MATCH;
        assert trie.containsPrefix(list2) == SearchResult.PERFECT_MATCH;
        System.out.println(trie.containsPrefix(list1));
        System.out.println(trie.containsPrefix(list2));
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void hashMap() throws IOException {
        File folder = new File("/Users/karkan/Documents/MACS/ADS/code/ADSProject/src/main/resources/archive");
        File[] listOfFiles = folder.listFiles();

        HashSet<String> stopWords = new HashSet<String>(Files.readAllLines(Path.of("/Users/karkan/Documents/MACS/ADS/code/ADSProject/src/main/resources/stop-words.txt"), StandardCharsets.ISO_8859_1));
        Set<String> wordCollection = new HashSet();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String content = Files.readString(Path.of(file.getAbsolutePath()), StandardCharsets.ISO_8859_1);
                content = removeNonAlphanumeric(content);

                String[] contentArr = content.toLowerCase().split("\\s+");
                for (int k = 0; k < contentArr.length; k++) {
                    wordCollection.add(contentArr[k]);
                }
            }
        }

        String toMap1 = "Atheist".toLowerCase();

        String toMap2 = "Scientific".toLowerCase();
        System.out.println(wordCollection.contains(toMap1));
        System.out.println(wordCollection.contains(toMap2));
    }

    public static String removeNonAlphanumeric(String str) {
        // replace the given string
        // with empty string
        // except the pattern "[^a-zA-Z0-9]"
        str = str.replaceAll(
                "[^a-zA-Z0-9\\s]", "");

        // return string
        return str;
    }
}
