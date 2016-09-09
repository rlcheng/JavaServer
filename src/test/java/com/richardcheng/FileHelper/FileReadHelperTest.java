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
    public void init_setsFile_andsetSizes() {
        String path = System.getProperty("user.dir") + "/README.md";
        FileReadHelper subject = new FileReadHelper();

        subject.init(path);

        Assert.assertEquals(subject.getReadSize(), subject.getFileSize());
    }

    @Test
    public void initFile_throwsException_whenFileNotFound() {
        FileReadHelper subject = new FileReadHelper();

        thrown.expect(RuntimeException.class);
        subject.init("IDontExist.txt");
    }

    @Test
    public void parseRange_rangeEndsWithDash() {
        String testRange = "2-";
        FileReadHelper subject = new FileReadHelper();
        subject.setFileSize(10);
        int expectedStart = 2;
        int expectedStop = 9;
        int expectedReadSize = 8;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedReadSize, subject.getReadSize());
    }

    @Test
    public void parseRange_rangeStartsWithDash() {
        String testRange = "-4";
        FileReadHelper subject = new FileReadHelper();
        subject.setFileSize(10);
        int expectedStart = 6;
        int expectedStop = 9;
        int expectedReadSize = 4;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedReadSize, subject.getReadSize());
    }

    @Test
    public void parseRange_rangeContainsDash() {
        String testRange = "1-4";
        FileReadHelper subject = new FileReadHelper();
        subject.setFileSize(10);
        int expectedStart = 1;
        int expectedStop = 4;
        int expectedReadSize = 4;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedReadSize, subject.getReadSize());
    }

    @Test
    public void parseRange_rangeNoDash() {
        String testRange = "4";
        FileReadHelper subject = new FileReadHelper();
        subject.setFileSize(10);
        int expectedStart = 4;
        int expectedStop = 9;
        int expectedReadSize = 6;

        subject.parseRange(testRange);

        Assert.assertEquals(expectedStart, subject.getStart());
        Assert.assertEquals(expectedStop, subject.getStop());
        Assert.assertEquals(expectedReadSize, subject.getReadSize());
    }

    @Test
    public void readbytes_ReturnsContentsOfMockFile() {
        String path = System.getProperty("user.dir") + "/README.md";
        MockFile mockFile = new MockFile(path);
        MockFileInputStream mockFileInputStream = null;
        try{
            mockFileInputStream = new MockFileInputStream(mockFile);
        } catch(FileNotFoundException e){
        }
        FileReadHelper subject = new FileReadHelper();
        subject.setFileInputStream(mockFileInputStream);
        subject.setStop(1);
        subject.setReadSize(2);

        byte[] actualContent = subject.readBytes();
        String contentString = new String(actualContent);

        Assert.assertEquals(2, actualContent.length);
        Assert.assertEquals("hi", contentString);
    }

    @Test
    public void readBytes_ThrowsException() {
        String path = System.getProperty("user.dir") + "/README.md";
        MockFile mockFile = new MockFile(path);
        MockFileInputStreamThrowIOException mockFileInputStreamThrowIOException = null;
        try{
            mockFileInputStreamThrowIOException = new MockFileInputStreamThrowIOException(mockFile);
        } catch(FileNotFoundException e){
        }
        FileReadHelper subject = new FileReadHelper();
        subject.setFileInputStream(mockFileInputStreamThrowIOException);
        subject.setReadSize(2);

        thrown.expect(RuntimeException.class);
        subject.readBytes();
    }
}