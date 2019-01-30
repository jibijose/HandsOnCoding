package com.anagram;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(BlockJUnit4ClassRunner.class)
public class Anagram2Test {

    Anagram2 anagram2;

    @Before
    public void init() {
        anagram2 = new Anagram2();
    }

    @Test
    public void shouldPrintAnagrams() {
        List<String> inputs = Arrays.asList("edit", "pencil", "now", "won", "diet", "own");
        List<List<String>> anagrams = anagram2.findAllAnagrams(inputs);
        assertEquals("Should be same", 2, anagrams.size());
        assertEquals("Should be same", 2, anagrams.get(0).size());
        assertEquals("Should be same", 3, anagrams.get(1).size());
    }
}
