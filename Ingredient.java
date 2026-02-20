package assignment4;
// Kelvin Bhual
// Corey Pittman
// COP3330-0004

public class Ingredient {
	// Declare variables
	private String name;
	private int power;
	
	// Constructor for an Ingredient
	public Ingredient(String name, int power) {
		this.name = name;
		this.power = power;
	}
	
	// Getter for name
	public String getName() {
		return name;
	}
	
	// Getter for power level
	public int getPower() {
		return power;
	}
}
