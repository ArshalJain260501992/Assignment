package com.assgnmnt.app.util;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.testng.annotations.Test;

import com.assgnmnt.app.dataProviders.TestNgStarter;
import com.assgnmnt.app.model.CanonicalFileContent;
import com.assgnmnt.app.util.FileReader;

/**
 * Test for {@link FileReader}
 * 
 * @author Arshal
 *
 */
public class FileReaderTest extends TestNgStarter {

	/**
	 * Tests if the file reader is able to read files as per expected
	 * 
	 * @param canonicalFileContent
	 * @throws Exception
	 */
	@Test(testName = "testFileContentReader", dataProvider = "dishesProvider")
	public void testFileContentReader(CanonicalFileContent canonicalFileContent) throws Exception {
		try {
			assertTrue(FileReader.readContent(FileReader.getAbsoluteFilePath("data.txt")).getDishCount() > 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests if the file reader is throwing exception if dishCount or time limit
	 * or satisfaction is empty
	 * 
	 * @param canonicalFileContent
	 * @throws Exception
	 */
	@Test(testName = "testFileContentReader", dataProvider = "dishesProvider", expectedExceptions = Exception.class)
	public void testFileContentReaderWithoutContentMetadata(CanonicalFileContent canonicalFileContent)
			throws Exception {
		try {
			FileReader.readContent(FileReader.getAbsoluteFilePath("data_without_dishcount.txt")).getDishCount();
			FileReader.readContent(FileReader.getAbsoluteFilePath("data_without_satisfaction.txt")).getDishCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
