package com.lottery.dto;

/**
 * This class holds User information.
 * 
 * @author Vikas
 *
 */
public class UserDto {

	private String name;
	private int number;
	
	public UserDto(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
