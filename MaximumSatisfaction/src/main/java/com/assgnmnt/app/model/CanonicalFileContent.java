package com.assgnmnt.app.model;

import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the model for the input file to be processed
 * @author Arshal
 *
 */
@Getter
@Setter
@ToString
public class CanonicalFileContent {

	private int timeLimit;
	private int dishCount;
	private LinkedList<Dish> dishes;

}
