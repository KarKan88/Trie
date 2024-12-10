package org.dalhousie.trie;

import org.dalhousie.trie.mapping.CharacterNaturalMapping;
import org.dalhousie.trie.store.IntegerArrayList;
import org.dalhousie.trie.store.IntegerList;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws RunnerException, IOException {
        org.openjdk.jmh.Main.main(args);
    }
}
