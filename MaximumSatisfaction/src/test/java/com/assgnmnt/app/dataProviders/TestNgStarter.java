package com.assgnmnt.app.dataProviders;

import java.io.IOException;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

import com.assgnmnt.app.model.CanonicalFileContent;
import com.assgnmnt.app.util.FileReader;

/**
 * This class is a super class to be treated as data provider as well as the TestNg test 
 * marker(in a sense that there are no test cases in it, but actually in its childs)
 * for all test classes there are
 * @author Arshal
 *
 */
@ActiveProfiles("test")
@SpringBootTest
public class TestNgStarter extends AbstractTestNGSpringContextTests {

	
	/**
	 * This reads the data.txt file from tes/resources and generates {@link CanonicalFileContent} 
	 * to be used by child class test methods
	 * @return
	 * @throws Exception 
	 */
	@DataProvider(name = "dishesProvider")
	public static Object[][] provideDishes() throws Exception {
		CanonicalFileContent canonicalFileCon = null;
		try {
			canonicalFileCon = FileReader.readContent(FileReader.getAbsoluteFilePath("data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Object[][] result = new Object[1][1];
		result[0] = new Object[] { canonicalFileCon };
		return result;
	}
}
