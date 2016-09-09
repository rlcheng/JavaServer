package com.richardcheng.FileHelper;

import com.richardcheng.FileHelper.mock.MockBufferedWriter;
import com.richardcheng.FileHelper.mock.MockBufferedWriterThrowIOException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteHelperTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void init_setupFile_andFileWriter() {
        String path = System.getProperty("user.dir") + "/test.txt";
        FileWriteHelper subject = new FileWriteHelper();

        subject.init(path);

        Assert.assertNotNull(subject.getBufferedWriter());
    }

    @Test
    public void init_throwsException() {
        FileWriteHelper subject = new FileWriteHelper();

        thrown.expect(RuntimeException.class);
        subject.init(".");
    }

    @Test
    public void write_success() {
        String path = System.getProperty("user.dir") + "/test.txt";
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriteHelper subject = new FileWriteHelper();
        subject.setBufferedWriter(new MockBufferedWriter(fileWriter));

        boolean writeSuccess = subject.write("hi");

        Assert.assertTrue(writeSuccess);
    }

    @Test
    public void write_throwsException() {
        String path = System.getProperty("user.dir") + "/test.txt";
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriteHelper subject = new FileWriteHelper();
        subject.setBufferedWriter(new MockBufferedWriterThrowIOException(fileWriter));

        thrown.expect(RuntimeException.class);
        subject.write("hi");
    }
}