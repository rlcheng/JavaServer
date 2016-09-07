package com.richardcheng.FileHelper;

import com.richardcheng.FileHelper.mock.MockFile;
import com.richardcheng.FileHelper.mock.MockFileInputStream;
import com.richardcheng.FileHelper.mock.MockFileInputStreamThrowIOException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileNotFoundException;

public class FileReadHelperTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void verifyInitialization() {
        MockFile mockFile = new MockFile("path");

        FileReadHelper subject = new FileReadHelper(mockFile);

        Assert.assertEquals(10, subject.getFileSize());
        Assert.assertEquals(10, subject.getSize());
    }

    @Test
    public void parseRange_rangeEndsWithDash() {
        String testRange = "2-";
        MockFile mockFile = new MockFile("path");
        FileReadHelper subject = new FileReadHelper(mockFile);
        int expectedStart = 2;
        int expectedStop = 9;
        int expectedSize = 8;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedSize, subject.getSize());
    }

    @Test
    public void parseRange_rangeStartsWithDash() {
        String testRange = "-4";
        MockFile mockFile = new MockFile("path");
        FileReadHelper subject = new FileReadHelper(mockFile);
        int expectedStart = 6;
        int expectedStop = 9;
        int expectedSize = 4;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedSize, subject.getSize());
    }

    @Test
    public void parseRange_rangeContainsDash() {
        String testRange = "1-4";
        MockFile mockFile = new MockFile("path");
        FileReadHelper subject = new FileReadHelper(mockFile);
        int expectedStart = 1;
        int expectedStop = 4;
        int expectedSize = 4;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedSize, subject.getSize());
    }

    @Test
    public void parseRange_rangeNoDash() {
        String testRange = "4";
        MockFile mockFile = new MockFile("path");
        FileReadHelper subject = new FileReadHelper(mockFile);
        int expectedStart = 4;
        int expectedStop = 9;
        int expectedSize = 6;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedSize, subject.getSize());
    }

    @Test
    public void readbytes_ReturnsContentsOfMockFile() {
        String currentDirectory = System.getProperty("user.dir");
        MockFile mockFile = new MockFile(currentDirectory + "/README.md");
        FileReadHelper subject = new FileReadHelper(mockFile);
        MockFileInputStream mockFileInputStream = null;
        try{
            mockFileInputStream = new MockFileInputStream(mockFile);
        } catch(FileNotFoundException e){
        }

        subject.parseRange("0-1");
        byte[] actualContent = subject.readBytes(mockFileInputStream);
        String contentString = new String(actualContent);

        Assert.assertEquals(2, actualContent.length);
        Assert.assertEquals("hi", contentString);
    }

    @Test
    public void readBytes_ThrowsException() {
        String currentDirectory = System.getProperty("user.dir");
        MockFile mockFile = new MockFile(currentDirectory + "/README.md");
        FileReadHelper subject = new FileReadHelper(mockFile);
        MockFileInputStreamThrowIOException mockFileInputStreamThrowIOException = null;
        try{
            mockFileInputStreamThrowIOException = new MockFileInputStreamThrowIOException(mockFile);
        } catch(FileNotFoundException e){
        }
        subject.parseRange("0-1");

        thrown.expect(RuntimeException.class);
        byte[] actualContent = subject.readBytes(mockFileInputStreamThrowIOException);
    }
}