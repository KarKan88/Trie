package org.dalhousie.trie;

import org.dalhousie.trie.mapping.CharacterNaturalMapping;
import org.dalhousie.trie.store.IntegerArrayList;
import org.dalhousie.trie.store.IntegerList;
import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HashMap {
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
