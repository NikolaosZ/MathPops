package com.example.mathpops;

public class Balloon {
	
	/** These are the member fields (values) */
	private int id;
	private int value;
	private String color;
	private String pop_color;
	
	/** This is the constructor. It generates a balloon of a specified color */
	public Balloon(int myId,int myValue) {
		switch (myId) {
		case 1: {
			id = myId;
			value = myValue;
			color = "yellow_unpop";
			pop_color = "yellow_pop";
			break;
		}
		case 2: {
			id = myId;
			value = myValue;
			color = "green_unpop";
			pop_color = "green_pop";
			break;
		}
		case 3: {
			id = myId;
			value = myValue;
			color = "red_unpop";
			pop_color = "red_pop";
			break;
		}
		case 4: {
			id = myId;
			value = myValue;
			color = "purple_unpop";
			pop_color = "purple_pop";
			break;
		}
		case 5: {
			id = myId;
			value = myValue;
			color = "blue_unpop";
			pop_color = "blue_pop";
			break;
		}
		}
	}
	
	/*public void generateValues(int noOfPops, int [] balloonIds, int total, Balloon balloon) {
		int [] answerBalloonIds = Calculations.pickNRandom(balloonIds, noOfPops);
		for (int i = 1; i<noOfPops; i++)
		{
			if (balloon.id== answerBalloonIds[i])
			{
				balloon.value = total - RNG.randInt(1,50);
				total = total - balloon.value;
			}
		}
	}*/
	
	
	/** This updates the values of the balloon */
	/*public void updateValue(int myId, int randNum) {
		switch (myId) {
		case 1: {
			setValue (randNum);
			break;
		}
		case 2: {
			setValue (randNum);
			break;
		}
		case 3: {
			setValue (randNum);
			break;
		}
		case 4: {
			setValue (randNum);
			break;
		}
		case 5: {
			setValue (randNum);
			break;
		}
		}
	}*/
	
	/** This sets the value of the balloon */
	public void updateValue (int myValue)
	{
		value = myValue;
	}
	
	/** This returns the value of the balloon */
	public int getValue() {
		int myValue = value;
		return myValue;
	}

	/** This calculates either the sum or difference of the balloons */
	public int calcSum(int sum, int operator) {
		int mySum = 0;
		switch (operator) {
		case 1: //Addition
			mySum = sum + value;
			break;
		case 2: //Subtraction
			mySum = sum - value;
			break;
		}
		return mySum;
	}
	
	//Returns the pop color of the balloon
	public String get_color() {
		String colour = color;
		return colour;
	}
	
	//Returns the pop color of the balloon
	public String get_pop_color() {
		String pop_colour = pop_color;
		return pop_colour;
	}
	
}
