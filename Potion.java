package assignment4;
// Kelvin Bhual
// Corey Pittman
// COP3330-0004

import java.util.LinkedList;
import java.util.Scanner;

public class Potion {
	// Initialize Potion properties
	private LinkedList<Ingredient> ingredients;
	private static int potionCounter;
	
	// Constructor for a Potion that starts empty
	public Potion() {
		this.ingredients = new LinkedList<Ingredient>();
		potionCounter++;
	}
	
	// Constructor for a Potion that starts with an initial Ingredient
	public Potion(LinkedList<Ingredient> ingredients) {
		this.ingredients = ingredients;
		potionCounter++;
	}
	
	// Creates a new Ingredient object and add it to the list 
	void addIngredient(String name, int power) {
		Ingredient newIngredient = new Ingredient(name, power);
		this.ingredients.add(newIngredient);
	}
	
	// Creates multiples of a new Ingredient object and adds them to the list
	void addIngredient(String name, int power, int quantity) {
		for(int i = 0; i < quantity; i++) {
			Ingredient newIngredient = new Ingredient(name, power);
			this.ingredients.add(newIngredient);
		}
	}
	
	// Adds Ingredients from a new list to the Potion's current Ingredient list
	void addManyMixed(LinkedList<Ingredient> newIngredients) {
		this.ingredients.addAll(newIngredients);
	}
	
	// Calculates the value of the Potion, starting with a base price of $50.00
	public double calculateValue() {
		double value = 50.00;
		
		for(Ingredient ingredient : ingredients) {
			// Each letter in the Ingredient's name is worth $0.25
			value += ingredient.getName().length() * 0.25;
			
			// Each level of power is worth $1.50
			value += ingredient.getPower() * 1.50;
		}
		
		// Returns base price + name length price + power level price
		return value;
	}
	
	// Prints the current Potion's list of Ingredients, formatted into a numbered list
	void printIngredients() {
		// Counter variable starts at 1 for formatting purposes
		int ingredientCount = 1;
		for(Ingredient ingredient : ingredients) {
			System.out.println(ingredientCount + ". " + ingredient.getName() + " (Power: " + ingredient.getPower() + ")");
			ingredientCount++;
		}
	}
	
	// Overridden method that returns the statement below whenever the current Potion is printed
	@Override
	public String toString() {
		return "This potion contains " + this.ingredients.size() + " ingredients and is worth $" + String.format("%.2f", calculateValue());
	}
	
	// Returns the current number of Potions crafted
	static int getPotionCount() {
		return potionCounter;
	}
	
	public static void main(String[] args) {
		// Initialize Scanner and a String to control the loop
		Scanner input = new Scanner(System.in);
		String userInput = "";
		
		// Greet user and ask them if they want to create a Potion, starting with an ingredient
		System.out.print("Welcome, Mage. What potion will you craft today?\nStart with an ingredient? (yes/no): ");
		userInput = input.nextLine();
		
		// If user responds with 'yes', create a new Potion and begin asking them to add Ingredients
		// If user responds with 'no', skip do-while loops and display total amount of Potions crafted
		// Side note: Specific behavior regarding a "no" input was not mentioned in the assignment's requirements,
		// 	so I just used it as the keyword to end the outer loop
		if(userInput.equals("yes")) {
			// Nested do-while loop allows for the creation of multiple Potions with separate Ingredients
			do {
				// Initialize variables and a Potion
				String name;
				int power, quantity;
				Potion potion = new Potion();
				
				// Prompt user to enter their first ingredient
				System.out.print("Enter your first ingredient name and power level (e.g. DragonScale 10): ");
				name = input.next();
				power = input.nextInt();
				potion.ingredients.add(new Ingredient(name, power));
				
				// Ask user if they want to add more ingredients
				do {
					System.out.print("\nAdd more ingredients? (single/multiple/mix/done): ");
					userInput = input.next();
					
					// Add a single Ingredient to the Potion
					if(userInput.equals("single")) {
						System.out.print("Enter ingredient name and power level: ");
						name = input.next();
						power = input.nextInt();
						potion.addIngredient(name, power);
					}
					
					// Add a specified quantity of a single Ingredient to the Potion
					if(userInput.equals("multiple")) {
						System.out.print("Enter ingredient name, power level, and quantity (e.g. GlimmeringMoss 5 3): ");
						name = input.next();
						power = input.nextInt();
						quantity = input.nextInt();
						potion.addIngredient(name, power, quantity);
					}
					
					// Add multiple different Ingredients to the Potion
					if(userInput.equals("mix")) {
						System.out.println("Enter ingredients to mix in (type 'done' to finish): ");
						do {
							name = input.next();
							// Check for 'done' keyword
							if(name.equals("done")) {
								break;
							}
							power = input.nextInt();
							potion.addIngredient(name, power);
							
						}while(true);
					}
					
				// Check for 'done' keyword
				}while(!userInput.equals("done"));
				
				// When user is done adding Ingredients, print the list of Ingredients, amount of Ingredients, and the cost of the Potion
				System.out.println("Your potion is ready!\nYour potion contains:");
				potion.printIngredients();
				System.out.println("\n" + potion + ".");
				
				// Use input.nextLine() to stop program from continuing ahead of itself
				input.nextLine();
				
				// Ask user if they want to create another Potion
				System.out.print("\nWould you like to create another potion? (yes/no): ");
				userInput = input.nextLine();
				
			// Check for 'no' keyword
			}while(!userInput.equals("no"));
		}
		
		// Print the amount of Potions crafted by the user
		System.out.println("Total potions crafted: " + getPotionCount());
		
		// Close Scanner
		input.close();
	}
}


