package com.heartofthestone.gtm;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.kohsuke.args4j.CmdLineException;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GrepTest {

    @Test
    void test1() throws IOException, CmdLineException {
        String[] command = "мама .\\src\\test\\resources\\Files\\input1.txt".split(" ");

        PrintStream old = System.out;
        PrintStream toFile = new PrintStream(new File(".\\src\\test\\resources\\FilesOutput\\output1.txt"));
        System.setOut(toFile);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);

        File file1 = new File(".\\src\\test\\resources\\FilesOutput\\output1.txt");
        File file2 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected1.txt");
        File file3 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected2.txt");

        assertTrue(FileUtils.contentEquals(file1, file2));
        assertFalse(FileUtils.contentEquals(file1, file3));
    }

    @Test
    void test2() throws IOException, CmdLineException {
        String[] command = "жди -v -i .\\src\\test\\resources\\Files\\input2.txt".split(" ");

        PrintStream old = System.out;
        PrintStream toFile = new PrintStream(new File(".\\src\\test\\resources\\FilesOutput\\output2.txt"));
        System.setOut(toFile);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);

        File file1 = new File(".\\src\\test\\resources\\FilesOutput\\output2.txt");
        File file2 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected2.txt");
        File file3 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected1.txt");

        assertTrue(FileUtils.contentEquals(file1, file2));
        assertFalse(FileUtils.contentEquals(file1, file3));
    }

    @Test
    void test3() throws IOException, CmdLineException {
        String[] command = "[a-z]+\\d+ -r -v .\\src\\test\\resources\\Files\\input3.txt".split(" ");

        PrintStream old = System.out;
        PrintStream toFile = new PrintStream(new File(".\\src\\test\\resources\\FilesOutput\\output3.txt"));
        System.setOut(toFile);
        Grep.main(command);
        System.out.flush();
        System.setOut(old);

        File file1 = new File(".\\src\\test\\resources\\FilesOutput\\output3.txt");
        File file2 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected3.txt");
        File file3 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected1.txt");
        File file4 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected2.txt");

        assertTrue(FileUtils.contentEquals(file1, file2));
        assertFalse(FileUtils.contentEquals(file1, file3));
        assertFalse(FileUtils.contentEquals(file1, file4));
    }

    @Test
    void test4() throws IOException, CmdLineException {
        String[] command = "[a-z]+\\d+ -r -v .\\src\\test\\resources\\Files\\input228.txt".split(" ");

        PrintStream old = System.out;
        PrintStream toFile = new PrintStream(new File(".\\src\\test\\resources\\FilesOutput\\output4.txt"));
        System.setOut(toFile);
        System.setErr(toFile);

        Grep.main(command);

        System.err.flush();
        System.setOut(old);

        File file1 = new File(".\\src\\test\\resources\\FilesOutput\\output4.txt");
        File file2 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected4.txt");

        assertTrue(FileUtils.contentEquals(file1, file2));
    }

    @Test
    void test5() throws IOException, CmdLineException {
        String[] command = "lol -v".split(" ");

        PrintStream old = System.out;
        PrintStream toFile = new PrintStream(new File(".\\src\\test\\resources\\FilesOutput\\output5.txt"));
        System.setOut(toFile);
        System.setErr(toFile);

        try {
            Grep.main(command);
        } catch (CmdLineException e) {

        }

        System.err.flush();
        System.setOut(old);

        File file1 = new File(".\\src\\test\\resources\\FilesOutput\\output5.txt");
        File file2 = new File(".\\src\\test\\resources\\ExpectedOutput\\expected5.txt");

        assertTrue(FileUtils.contentEquals(file1, file2));
    }
}