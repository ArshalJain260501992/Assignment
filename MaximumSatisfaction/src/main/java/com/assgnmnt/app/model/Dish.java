package com.assgnmnt.app.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Represents each Dish item in the file 
 * @author Arshal
 *
 */
@Getter
@Setter
@EqualsAndHashCode(exclude={"consumeTime", "satisfaction"})
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

	private String id;
	private int consumeTime;
	private int satisfaction;
}
