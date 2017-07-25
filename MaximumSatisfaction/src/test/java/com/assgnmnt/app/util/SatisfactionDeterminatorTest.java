package com.assgnmnt.app.util;

import static org.testng.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.assgnmnt.app.dataProviders.TestNgStarter;
import com.assgnmnt.app.model.CanonicalFileContent;

/**
 * 
 * Tests the {@link SatisfactionDeterminator} for its validity
 * 
 * @author Arshal
 *
 */
public class SatisfactionDeterminatorTest extends TestNgStarter {

	@Autowired
	SatisfactionDeterminator satisfactionDeterminator;

	@Test(testName = "testFindMaximumSatisfationWithinTime", priority = 1, dataProvider = "dishesProvider")
	public void testFindMaximumSatisfationWithinTime(CanonicalFileContent canonicalFileContent) {
		Integer maxSatisfaction = satisfactionDeterminator.findMaximumSatisfationWithinTime(
				canonicalFileContent.getTimeLimit(), canonicalFileContent.getDishes());
		assertTrue(2493893 == maxSatisfaction);
	}
}
