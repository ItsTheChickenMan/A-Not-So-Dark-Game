//**A NOT SO DARK GAME** - The Project that was never worth it but I'll continue it anyways
package game;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Runtime;

public class DarkMain {	
	static final Scanner input = new Scanner(System.in); //Input used for entire program
	static String[] items = {"","","","","","","","","",""}; //Items List
	static int[] location = {1, 7};  //Location on map (1 is x pos, 2 is y pos)
	public static void main(String[] args) throws Exception {
		//Initializing Important Variables
		StringBuffer username = new StringBuffer(""); //Username
		String msg1 = "Oh good.  You know how to type.  We're getting somewhere.";
		String msg2 = "What will your name be?";
		String msg4 = "Well then, I wish you the best of luck on your journey.";
		//Game start
		String version = "Development";  //The Game isn't done so this stays Alpha until I have a testable version
		Util.clearScreen(); //Clears screen
		Game.printTitle(version); //Prints title, parameter is version number
		Util.secPause(1); //Pauses 
		Util.intPrint(msg1);
		Util.secPause(1); //Pauses 
		Util.intPrint(msg2);
		//Username input
		while(username.toString().equals("")){	
			username.append(input.nextLine());
		}
		//End username input
		String msg3 = username.toString() + ".  Good name.";
		Util.secPause(1);
		Util.intPrint(msg3);
		Util.secPause(1);
		Util.intPrint(msg4);
		Util.secPause(2);
		Util.clearScreen();
		Game.scene(); //Forest scene
		input.close();  //This comes at the end of the code in order to close the input stream (DON'T CLOSE MID PROGRAM)
	}
}
	
class Stats {
	public static int health = 100; //Out of 100
	public static int strength = 5; //Out of 100
	public static int charisma = 0; //Out of 100
}
class Game {
	public static void printTitle(String version) throws Exception {  //Prints Title and Crap
		char play = ' ';
		System.out.println("**A NOT SO DARK GAME** Version " + version + "\n");
		System.out.println("Created by ItsTheChickenMan\n");
		System.out.printf("\nPlay (P)? : ");
		while(true){
			play = DarkMain.input.next().charAt(0); //Make sure to reference input as "DarkMain.input"
			if(play != 'P'){
				Util.secPause(1);
				System.out.println("I dunno what that means.  You wanna play or what?\n");
			} else if(play == 'P'){
				break;
			}
		}
	}
	public static void scene() throws Exception {  //The main game, based off of location (ex. (1,7) is opening scene)
		boolean end = false;
		while(end != true){
			if((DarkMain.location[0] == 1) && (DarkMain.location[1] == 7)){ //Location 1,7
				String[] actions = {"Travel Path", "Climb Tree", "Back"};
				Util.intPrint("You are in a forest.  All you can see are trees, which seem to reach the sky.  You cannot tell if it's day or night, because the trees cover the sky.  \nThere is a path.\n");
				while(true){
					Util.dispChoice();
					int choice = Util.checkChoice(actions);
					if(choice == 1){
						while(true){
							while(!DarkMain.input.hasNextInt()){  //Check to make sure it is valid int
								System.out.println("Invalid input.  Try again.");
								DarkMain.input.next();
							}
							int temp = DarkMain.input.nextInt(); //Testing to see which action is chosen
							if(temp == 1){   //If "Travel Path" is picked, change location
								Util.clearScreen();
								Util.intPrint("This is the end of the current dev build.  More is to come!");
								Util.printDead();
								end = true;
								break;
								//DarkMain.location[0] = 1;    
								//DarkMain.location[1] = 6;
								//break;
							} else if(temp == 2){  //If "Climb Tree" is picked, do all this
								Stats.health = Stats.health - 2;
								String youClimb = "\nYou start to climb the tree.  You cut your arm on the branches and lose 2 health.";  
								Util.intPrint(youClimb);
								if(Stats.health <= 0){
									Util.printDead();
									end = true;
									break;
								}
								Util.intPrint("Do you continue?\n");
								System.out.println("1: Yes");
								System.out.println("2: No\n");
								while(!DarkMain.input.hasNextInt()){
									System.out.println("\nInvalid input.  Try again.");
									DarkMain.input.next();
								}		
								int si = DarkMain.input.nextInt();
								if(si == 1){
									String contin = "\nYou continue up the tree.  You can feel yourself tiring, but you can see daylight...";
									Util.intPrint(contin);
									Util.intPrint("Do you continue?\n");
									System.out.println("1: Yes");
									System.out.println("2: No\n");
									while(!DarkMain.input.hasNextInt()){
										System.out.println("\nInvalid input.  Try again.");
										DarkMain.input.next();
									}
									int no = DarkMain.input.nextInt();
									if(no == 1){
										if(Stats.strength < 10){
											String die = "\nYou continue climbing.  You are near the top, but you are not strong enough and you fall to the ground.";
											Util.intPrint(die);
											Util.printDead();
											end = true;
											break;
										} else {
											Util.intPrint("\nYou climb to the top, and see that there is fruit.  Grab it?\n");
											System.out.println("1: Yes");
											System.out.println("2: No\n");
											while(!DarkMain.input.hasNextInt()){
												System.out.println("\nInvalid input.  Try again.");
												DarkMain.input.next();
			         						}
											int ey = DarkMain.input.nextInt();
											if(ey == 1){
												for(int i = 0; i < DarkMain.items.length; i++){
													if(DarkMain.items[i].equals("")){
														DarkMain.items[i] = "Fruit";
														Util.intPrint("\nYou take the fruit, and climb back down the tree.");
														continue;
													} else {
														continue;
													}
												}
											} else if(ey == 2){
												Util.intPrint("\nYou climb back down the tree.");
												break;
											}
										}
									} else {
										Util.intPrint("\nYou climb back down the tree.\n");
										break;
									}
								} else {
									Util.intPrint("\nYou climb back down the tree.\n");
									break;
								}
							} else if(temp == 3){
								break;
							} else {
								System.out.println("Invalid input, please try again.");
								continue;
							}
							
						}
						if(end = true){
							break;
						}
						continue;
					} else if(choice == 2){
						while(true){
							while(!DarkMain.input.hasNextInt()){
								System.out.println("\nInvalid input.  Try again.");
								DarkMain.input.next();
							}
							int next = DarkMain.input.nextInt();
							if(next == 1){
								break;
							} else {
								System.out.println("Invalid input, please try again.");	
							}
						}
						//Add code here if items have special affect in this area
						continue;
					} else if(choice == 3){
						continue;
					} else if(choice == 4){
						continue;
					}
					break;
				}
			} 
			if((DarkMain.location[0] == 1) && (DarkMain.location[1] == 6)){ //Location 1,6
				String[] actions = {"Climb Over Tree", "Go Around Tree", "Back"};
				Util.intPrint("You follow the rugged path for about 3 miles.  You come across a fallen tree.  It is blocking your path.");
				while(true){
					Util.dispChoice();
					int choice = Util.checkChoice(actions);
					if(choice == 1){
						while(!DarkMain.input.hasNextInt()){
							System.out.println("Invalid Input.  Try again.");
							DarkMain.input.next();
						}
						int temp = DarkMain.input.nextInt();
						if(temp == 1){ //If you climb over tree, do this
							Util.intPrint("\nYou begin to climb over the tree.");
							String[] wor = {"up","slip","fall"}; 
							int reacTst = Util.reactionTest(wor, true, 1500);
							if(reacTst == 0){
								Stats.health = Stats.health - 10;
								Util.intPrint("\nToo slow!  You slip off the tree and lose 10 health.\n");
								if(Stats.health <= 0){
									Util.printDead();
									end = true;
								}
							} else if(reacTst == 1){
								String[] secAc = {"Leave it be"};
								Util.intPrint("\nYou manage to make it over the tree.");
							}
						} else if(temp == 2){ //If you choose to walk around
							String[] actons = {"Talk", "Slowly Back Away", "Run Away"};
							Util.intPrint("You walk around the tree.  It is very long, and you walk about a mile before you reach a stump.  Waiting for you at the stump is a man, carrying an axe.  He appears to have chopped the tree down.");
							Util.dispChoice();
							int check = Util.checkChoice(actons);
							if(check == 1){
								Util.intPrint("1.  Hello there sir, how are you today?");
								Util.intPrint("2.  Hey, could you get me some directions?");
								Util.intPrint("3.  YOU BEST WATCH OUT I GOT AN AXE AND I AIN'T AFRAID TO USE IT");
								while(!DarkMain.input.hasNextInt()){
									System.out.println("Invalid Input.  Try again.");
									DarkMain.input.next();
								}
								int speak = DarkMain.input.nextInt();
								if(speak == 1){
								} else if(speak == 2){
								} else if(speak == 3){
								} else {
								}
							} else if(check == 2){
								
							}
						} else if(temp == 3){
							
						}
					} else if(choice == 2){
						//If items have special affects, put them here
					} 
				}
			}
		}
	}
}

class Util {
	public static void secPause(int time) throws Exception {
		TimeUnit.SECONDS.sleep(time); 
	}
	public static void clearScreen() throws Exception {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	public static void intPrint(String msg) throws Exception {
		for(int i = 0; i < msg.length(); i++){
			char printChar = msg.charAt(i);
			System.out.printf("%c", printChar);
			if((printChar == '.') || (printChar == '?') || (printChar == '!')){
				TimeUnit.MILLISECONDS.sleep(500);
			}
			TimeUnit.MILLISECONDS.sleep(40);
		}
		System.out.println("");
	}
	public static void listActions(String[] actions) throws Exception {
		System.out.println("");
		for(int i = 0; i < actions.length; i++){
			System.out.printf("%d. %s\n", i + 1, actions[i]);
		}
		System.out.println("");
	}
	public static void listItems() throws Exception {
		if(DarkMain.items[0].equals("")){
			System.out.println("\nYou currently have no items.\n");
			System.out.println("1. Back\n");
		} else {
			for(int i = 0; i < DarkMain.items.length; i++){
				if(DarkMain.items[i].equals("")){
					
				} else {
					System.out.printf("%d. %s\n", i + 1, DarkMain.items[i]);
				}
			}
		}
	}
	public static void dispMap(int location[]) throws Exception {
		File map = new File("game\\FullMap.txt");
		Scanner mapIn = new Scanner(map);
		while (mapIn.hasNextLine()) {
            String i = mapIn.nextLine();
            System.out.println(i);
        }
		System.out.println("\n1. Back\n");
	}
	public static void dispChoice(){
		System.out.println("\n1: Actions");
		System.out.println("2: Items");
		System.out.println("3: Map");
		System.out.println("4: Stats\n");
	}
	public static void dispStats(){
		System.out.println("\nHealth: " + Stats.health);
		System.out.println("Strength: " + Stats.strength);
		System.out.println("Charisma: " + Stats.charisma);
		System.out.println("\n1. Back\n");
	}
	public static int checkChoice(String[] actions) throws Exception {
		int choice = 0;
		while(true){
			while(choice == 0){
				while(!DarkMain.input.hasNextInt()){
					System.out.println("Invalid input.  Try again.");
					DarkMain.input.next();
				}
				choice = DarkMain.input.nextInt();
			}
			if(choice == 1){
				Util.listActions(actions);
				return 1;
			} else if(choice == 2){
				Util.listItems();
				return 2;
			} else if(choice == 3) {
				Util.dispMap(DarkMain.location);
				while(true){
					while(!DarkMain.input.hasNextInt()){
						System.out.println("Invalid input.  Try again.");
						DarkMain.input.next();
					}
					int temp = DarkMain.input.nextInt();
					if(temp == 1){
						break;
					} else {
						System.out.println("\nInvalid Input.  Try again.\n");
					}
				}		
				return 3;
			} else if(choice == 4){
				Util.dispStats();
				while(true){
					while(!DarkMain.input.hasNextInt()){
						System.out.println("Invalid input.  Try again.");
						DarkMain.input.next();
					}
					int temp = DarkMain.input.nextInt();
					if(temp == 1){
						break;
					} else {
						System.out.println("\nInvalid Input.  Try again.\n");
					}
				}
				return 4;
			} else {
				System.out.println("Invalid input.  Try again.");
				choice = 0;
			}
		}
	}
	public static void printDead() throws Exception {
		String dead = "YOU ARE DED.";
		for(int i = 0; i < dead.length(); i++){
			char printChar = dead.charAt(i);
			System.out.printf("%c", printChar);
			if((printChar == '.') || (printChar == '?') || (printChar == '!')){
				TimeUnit.MILLISECONDS.sleep(500);
			}
			TimeUnit.MILLISECONDS.sleep(250);
		}
	}
	public static int reactionTest(String[] words, boolean warning, int time) throws Exception {
		if(warning == true){
			Util.intPrint("For this action, we will test your reaction speed.");
			Util.intPrint("Words will be printed on the screen, and you will have 1.5 seconds to type them.");
			Util.intPrint("Ready?");
			Util.secPause(1);
			System.out.println("GO!\n");
		}
			
		for(int i = 0; i < words.length; i++){
			int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
			Util.secPause(randomNum);
			System.out.println(words[i]);
			long start = System.nanoTime();
			StringBuffer word = new StringBuffer("");
			while(word.toString().equals("")){
				word.append(DarkMain.input.nextLine());
			}
			long fin = System.nanoTime();
			long elapsed = fin - start;
			if(((elapsed/1000000) > time) || !(word.toString().equals(words[i]))){
				return 0;
			}
		}
		return 1;
	}
	public static void fightAnimal(String animal, int dam, int health) throws Exception {   //Work on this later
		System.out.println("You come across a " + animal + ".  Fight or run?");
		
	}		
}