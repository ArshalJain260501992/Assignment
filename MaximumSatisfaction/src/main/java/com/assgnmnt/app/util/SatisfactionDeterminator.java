package com.assgnmnt.app.util;

import java.util.LinkedList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.assgnmnt.app.model.Dish;

/**
 * This class entails behaviors to determine satisfaction
 * @author Arshal
 *
 */
@Component
@Scope("prototype")
public class SatisfactionDeterminator {

	
	
	/**
	 * 
	 * This method calculates maximum satisfaction for a given time-limit {@link Integer}, 
	 * that can be achieved with a given domain of dishes {@link LinkedList}<{@link Dish}>
	 * @param {@link Integer} givenTimeLimit
	 * @param {@link Integer} dishes
	 * @return {@link Integer} maximumSatisfaction
	 */
	public Integer findMaximumSatisfationWithinTime(Integer givenTimeLimit, LinkedList<Dish> dishes) {

		Integer dishCount = dishes.size();

		/*
		 * create a 2D array of 1 extra row and column from dishes and
		 * givenTimeLimit
		 */
		Integer[][] satisfactionMatrix = new Integer[dishCount + 1][givenTimeLimit + 1];

		/*
		 * fill the extra column with 0's to cover the case where the timelimit
		 * is 0
		 */ for (int col = 0; col <= givenTimeLimit; col++) {
			satisfactionMatrix[0][col] = 0;
		}

		/*
		 * fill the extra row with 0's to cover the case where the satisfaction
		 * is 0
		 */
		for (int row = 0; row <= dishCount; row++) {
			satisfactionMatrix[row][0] = 0;
		}

		/*
		 * iterates on all dishes(identified by row index) for every timeLimit
		 * (specified by column index)
		 */
		for (int dishIndex = 1; dishIndex <= dishCount; dishIndex++) {

			/*
			 * iterates on every timeLimit for one dish(dish at row index =
			 * dishIndex)
			 */
			for (int runningTimeLimit = 1; runningTimeLimit <= givenTimeLimit; runningTimeLimit++) {

				// if running dish takes less or equal time to running timeLimit
				if (dishes.get(dishIndex - 1).getConsumeTime() <= runningTimeLimit) {

					/*
					 * Satisfaction value is max out of satisfaction from
					 * (running dish + satMatrix[remaining dishes][remaining
					 * time]) OR satMatrix[dishes except running dish][running
					 * timeLimit]
					 */
					satisfactionMatrix[dishIndex][runningTimeLimit] = Math.max(
							dishes.get(dishIndex - 1).getSatisfaction()
									+ satisfactionMatrix[dishIndex - 1][runningTimeLimit
											- dishes.get(dishIndex - 1).getConsumeTime()],
							satisfactionMatrix[dishIndex - 1][runningTimeLimit]);
				} else {
					/*
					 * Satisfaction value is max out of satisfaction from
					 * (running dish + satMatrix[remaining dishes][remaining
					 * time]) OR satMatrix[dishes except running dish][running
					 * timeLimit]
					 */
					satisfactionMatrix[dishIndex][runningTimeLimit] = satisfactionMatrix[dishIndex
							- 1][runningTimeLimit];
				}
			}
		}

		return satisfactionMatrix[dishCount][givenTimeLimit];
	}

}
