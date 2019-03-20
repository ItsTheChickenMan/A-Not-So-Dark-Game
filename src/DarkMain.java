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
	static int[] location = {1, 7};  //Location on map (1 is x pos, 2 is y pos, START is 1, 7)
	static StringBuffer username = new StringBuffer(""); //Username
	public static void main(String[] args) throws Exception {
		//Initializing Important Variables
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
			String u = input.nextLine();
			if(u.toLowerCase().equals("itsthechickenman")){
				Util.intPrint("You can't be him.     He created this game, and it certainly ain't the debug version.");
				continue;
			} else if(u.toLowerCase().equals("i like em big")){
				Util.intPrint("Do you like em chunky? ...No?  Then you can't have the name.");
				continue;
			} else if(u.toLowerCase().equals("3.14")){
				Util.intPrint("15926535897932384626433832795028841971693993751058209749445923.   ...Sorry.  Pick something else.");
				continue;
			} else if(u.toLowerCase().equals("phoenix")){
				Util.intPrint("Phoenix?  That's a cool name.  So cool that I don't think I'll let you have it.");
				continue;
			}
			username.append(u);
		}
		//End username input
		String msg3 = username.toString() + ".  Good name.";
		Util.secPause(1);
		Util.intPrint(msg3);
		Util.secPause(1);
		Util.intPrint(msg4);
		Util.secPause(2);
		Util.clearScreen();
		Util.intPrint("You are in a forest.  All you can see are trees, which seem to reach the sky.  You cannot tell if it's day or night, because the trees cover the sky.  \nThere is a path.\n");
		Game.scene(); //Forest scene and rest of game opens up
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
		System.out.println("**A NOT SO DARK GAME** Version " + version + "\n");
		System.out.println("Created by ItsTheChickenMan\n");
		System.out.printf("(Note: Play fullscreen for best experience)\nPlay (P)? : ");
		while(true){
			String title = DarkMain.input.nextLine(); //Make sure to reference input as "DarkMain.input"
			if(!title.toLowerCase().equals("p")){
				Util.secPause(1);
				System.out.println("I dunno what that means.  You wanna play or what?\n");
			} else if(title.toLowerCase().equals("p")){
				break;
			}
		}
	}
	public static void scene() throws Exception {  //The main game, based off of location (ex. (1,7) is opening scene)
		boolean end = false;
		boolean axeManDead = false;
		boolean shortcut1 = false;
		while(end != true){
			if((DarkMain.location[0] == 1) && (DarkMain.location[1] == 7)){ //Location 1,7
				String[] actions = {"Travel Path", "Climb Tree", "Back"};
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
								Util.intPrint("You follow the rugged path for about 3 miles.  You come across a fallen tree.  It is blocking your path.");
								DarkMain.location[0] = 1;    
								DarkMain.location[1] = 6;
								break;
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
							} else if(temp == 3){ //If "Back" is picked
								break;
							} else {
								System.out.println("Invalid input, please try again.");
								continue;
							}
							
						}
						if(end == true || !(DarkMain.location[0] == 1 && DarkMain.location[1] == 7)){
							break;
						} else {
							continue;
						}
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
							Util.clearScreen();
							Util.intPrint("You begin to climb over the tree.");
							String[] wor = {"up","slip","fall"}; 
							int reacTst = Util.reactionTest(wor, true, 1500);
							if(reacTst == 0){
								Stats.health = Stats.health - 10;
								Util.intPrint("\nToo slow!  You slip off the tree and lose 10 health.\n");
								if(Stats.health <= 0){
									Util.printDead();
									end = true;
								}
							} else if(reacTst == 1){ //If you actually climb the darned tree
								Util.intPrint("\nYou manage to make it over the tree.");
								Util.intPrint("\nYour exercise improves your strength by 2.");
								Stats.strength += 2;
								Util.clearScreen();
								Util.intPrint("You look behind you and realize the tree is too steep to climb back around.  The path continues on.  There is also a clearing to the right, which seems to have been\n partly caused by the falling tree.\n");
								String[] actionl = {"Continue along the path.", "Explore around the clearing.", "Scream as loudly as you can.", "Back."};
								while(true){
									Util.dispChoice();
									int key = Util.checkChoice(actionl);
									if(key == 1){ 
										while(true){
											while(!DarkMain.input.hasNextInt()){
												System.out.println("Invalid Input.  Try again.");
											}
											int keep = DarkMain.input.nextInt();
											if(keep == 1){
												Util.intPrint("Sorry, but you've reached the limits for what this Dev Build has to offer.  Expect more!");
												Util.intPrint("THE END (For now)");
												Util.secPause(6);
												end = true;
												
											} else if(keep == 2){
												Util.intPrint("Sorry, but you've reached the limits for what this Dev Build has to offer.  Expect more!");
												Util.intPrint("THE END (For now)");
												Util.secPause(6);
												end = true;
												
											} else if(keep == 3){
												Util.intPrint("You scream into the forest.  ...Nothing happens.  I'm not sure what you were expecting.");
												continue;
											} else if(keep == 4){
												
											} else {
												System.out.println("Invalid Input, please try again.");
												continue;
											}
											break;
										}
										if(end == true){
											break;
										} else {
											continue;
										}
									} else if(key == 2){
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
										//If items have special affect, add them here 
										continue;
									} else if(key == 3){
										continue;
									} else {
										System.out.println("Invalid Input, please try again.");
										continue;
									}
									
								}
							}
						} else if(temp == 2){ //If you choose to walk around
							String[] actons = {"Talk", "Slowly Back Away", "Run Away", "Back"};
							Util.clearScreen();
							Util.intPrint("You walk west to get around the tree.  It is very long, and you walk about a mile before you reach a stump.  At the stump is a man, carrying an axe.  He\nappears to have chopped the tree down.");
							while(true){
								Util.dispChoice();
								int check = Util.checkChoice(actons);
								if(check == 1){
									while(true){
										while(!DarkMain.input.hasNextInt()){
											System.out.println("Invalid Input.  Try again.");
											DarkMain.input.next();
										}
										int nex = DarkMain.input.nextInt();
										if(nex == 1){
											Util.clearScreen();
											Util.intPrint("You try to spark conversation.\n");
											Util.intPrint("1.  \"Hello there sir, how are you today?\"");
											Util.intPrint("2.  \"Hey, could you get me some directions?\"");
											Util.intPrint("3.  \"YOU BEST WATCH OUT I GOT AN AXE AND I AIN'T AFRAID TO USE IT\"\n");
											while(!DarkMain.input.hasNextInt()){
												System.out.println("Invalid Input.  Try again.");
												DarkMain.input.next();
											}
											int speak = DarkMain.input.nextInt();
											if(speak == 1){
												Util.clearScreen();
												Util.intPrint(DarkMain.username + ": \"Hello there sir, how are you today?\"\n");
												Util.intPrint("Axeman: \"I'm actually in a rather bad mood.  You'd best be gone, now, unless you're in the mood for an axehead in your side.\"\n");
												Util.intPrint("\n1.  \"Oh, come now, that's a bunch o' rubbish.\"");
												Util.intPrint("2.  \"Okay, okay!  But can I ask you a question first?\"");
												Util.intPrint("3.  Walk away.\n");
												while(!DarkMain.input.hasNextInt()){
													System.out.println("Invalid Input.  Try again.");
													DarkMain.input.next();
												}
												int sped = DarkMain.input.nextInt();
												if(sped == 1){
													Util.clearScreen();
													Util.intPrint("You decide to become British for no reason.\n");
													Util.intPrint("\n" + DarkMain.username + ": \"Oh, come now, that's a bunch o' rubbish.\"");
													Util.intPrint("\nThe Axeman spares you the witty remark and just throws the axe at you out of annoyance.\n");
													String[] wo = {"dodge"};
													int tes = Util.reactionTest(wo, false, 2200);
													if(tes == 0){
														Util.intPrint("You are too slow.  The axe hits and kills you on the spot.");
														Util.printDead();
														end = true;
														break;
													} else {
														Util.intPrint("\nYou dodge the axe, and it plunks down on the ground next to you.  You pick it up.");
														Util.intPrint("\n1. Kill him.");
														Util.intPrint("\n2. Spare him.\n");
														while(!DarkMain.input.hasNextInt()){
															System.out.println("Invalid Input, try again.");
															DarkMain.input.next();
														}
														int kh = DarkMain.input.nextInt();
														if(kh == 1){
															int rando = ThreadLocalRandom.current().nextInt(1, 5);
															if(rando == 1){
																Util.clearScreen();
																Util.intPrint("You go to strike, but he narrowly dodges.  You turn around, and the last thing you see is him holding a hunting knife, lunging for your throat.");
																Util.printDead();
																end = true;
																break;
															} else if(rando == 2){
																Util.clearScreen();
																Util.intPrint("He is not fast enough to dodge, and you kill him.  You find a hunting knife on his body.  It gives you +15 strength when you use it.");
																Util.intPrint("\nA small, vegetation filled trail leads forward.");
																Util.intPrint("1. Follow Path.\n");
																while(!DarkMain.input.hasNextInt()){
																	System.out.println("Invalid Input, try again");
																}
																int m = DarkMain.input.nextInt();
																if(m == 1){
																	axeManDead = true;
																	DarkMain.location[0] = 2;
																	DarkMain.location[1] = 6;
																	break;
																}
					
															} else {
																System.out.println("Invalid Input.  Try again.");
															}
														} else if (kh == 2){
															Util.clearScreen();
															axeManDead = false;
															Util.intPrint("You throw the axe aside and offer a hand.");
															Util.intPrint("Axeman: \"What?  Whaddya want?\"");
															Util.intPrint(DarkMain.username + ": \"Just offering a hand.  You think you could get me outta this forest?\"");
															Util.intPrint("Axeman: \"Unlikely.  I stopped trying 10 years ago when I realized it was too dark for me to navigate, and my eyesight's only getting worse.\n         I could build a fire...but it's too dark.\"");
															Util.intPrint("Axeman: \"I do have a shelter nearby, though.  It's just down this path.  Follow me.\"");
															DarkMain.location[0] = 2;
															DarkMain.location[1] = 6;
															break;
														} else {
															System.out.println("Invalid Input.  Try again.");
														}														
														
													}
												} else if(sped == 2){
													Util.clearScreen();
													Util.intPrint(DarkMain.username + ": \"Okay, okay!  But can I ask you a question first?\"");
													Util.intPrint("Axeman: \"Make it snappy.\"");
													Util.intPrint("1.  \"Who are you?\"");
													Util.intPrint("2.  \"Where am I?\"");
													Util.intPrint("3.  \"Why are you so damn ugly?\"");
													while(true){
														while(!DarkMain.input.hasNextInt()){
															System.out.println("You don't have time to give me invalid input.  Hurry up!");
															DarkMain.input.next();
														}
														int qui = DarkMain.input.nextInt();
														if(qui == 1){
															Util.clearScreen();
															Util.intPrint(DarkMain.username + ":  \"Who are you?\"\n");
															Util.intPrint("Axeman:  \"An old adventurer.\"\n");
															Util.intPrint(DarkMain.username + ":  \"Well then, from one adventurer to another, do you think you could do me a favor?\"\n");
															Util.intPrint("Axeman: \"No.\"\n");
															Util.intPrint("Without even a second thought, he throws the axe.");
															Util.printDead();
															end = true;
															break;
														} else if(qui == 2){
															Util.clearScreen();
															Util.intPrint(DarkMain.username + ": \"Where am I?\n");
															Util.intPrint("He sighs.  Looks like you've hit a sensitive topic.\n");
															Util.intPrint("Axeman: \"You're in an inescapable forest.\"\n");
															Util.intPrint(DarkMain.username + ": \"Inescapable?\"\n");
															Util.intPrint("Axeman: \"Indeed.  I've tried before, you'll never get anywhere.  It's completely endless.\"\n");
															Util.intPrint(DarkMain.username + ": \"Then why does this map say otherwise?\"\n");
															Util.intPrint("\nYou hand him your map.\n");
															Util.intPrint("Axeman:  \"Huh.  This says there's some city northeast...and a home, and mountains...how did I never find this?\"");
															Util.intPrint("Axeman:  \"Well, there is this one problem.  I say this forest never ends because, as far as I know, it literally never ends.\"");
															Util.intPrint("Axeman:  \"You see, there are too many trees down south to get anywhere anyways, so I always head up north.  But whenever I do, I see this marking on a tree.  It's \ntwo letters, 'N' and below it a 'W'.  I always assumed it meant northwest, but if I travel that way it just repeats.  I come back to the symbol, not having gotten anywhere.\"");
															Util.intPrint("Axeman:  \"That place is usually pretty difficult to get to, but I know a shortcut.  Follow me.\"");
															DarkMain.location[0] = 1;
															DarkMain.location[1] = 5;
															shortcut1 = true;
															break;
														} else if(qui == 3){
															Util.clearScreen();
															Util.intPrint(DarkMain.username + ": \"Why are you so damn ugly?\"");
															Util.intPrint("The axeman sniffles a little.");
															Util.intPrint("Axeman:  \"I don't...look that bad, do I?\"");
															Util.intPrint(DarkMain.username + ":  \"Nah, I'm just kid-");
															Util.intPrint("Axeman:  \"Mom always said I looked good...that's true, right?\"");
															Util.intPrint(DarkMain.username + ":  \"Hey, hey, chill, I'm just-");
															System.out.println("Axeman:");
															Util.intPrint("\"You know what?  I can look great if I want to!  If I say I'm great I'm great!  No one \nhas to approve!");
															Util.intPrint("I can do what I want!");
															Util.intPrint("And I won't let you stop me!");
															Util.intPrint(DarkMain.username + ":  \"CALM DOWN, I'M JUST-\"");
															Util.intPrint("He throws his axe at you.");
															Util.printDead();
															end = true;
															break;
														} else {
															System.out.println("Invalid Input.  Try again.");
														}
													}
													break;
												} else if(sped == 3){
													Util.clearScreen();
													Util.intPrint("You decide it would be most beneficial to your livelihood if you walk away.");
													Util.intPrint("You are back at the fallen tree, which still is blocking your path.");
													break;
												} else {
												}
											} else if(speak == 2){
												Util.clearScreen();
												Util.intPrint(DarkMain.username + ": \"Hey, could you get me some directions?\"\n");
												Util.intPrint("Axeman: \"Directions?  To where?  There's no where to go!  You go anywhere 'cept up and there's too many trees!  \nIf you head north, all you get is N, W!  That's all I know, N, W!!!\"\n");
												Util.intPrint("1.  \"Hey, calm down.  No reason to get mad.\"");
												Util.intPrint("2.  \"Uh...yes!  Thanks for the help!\"");
												Util.intPrint("3.  Walk away.");
												while(true){
													while(!DarkMain.input.hasNextInt()){
														System.out.println("Invalid Input.  Try again.");
														DarkMain.input.next();
													}
													
												}
											} else if(speak == 3){
											} else {
											}
										} else if(nex == 2){
											Util.clearScreen();
											Util.intPrint("You decide that you don't want to mess with this guy, and you slowly back away.  Once you are out of his sight, you run.");
											Util.intPrint("You are back at the fallen tree, which is still blocking your path.");
											break;
										} else if(nex == 3){
											Util.clearScreen();
											Util.intPrint("You are scared out of your wits of this guy, and you start running.  He is startled too, and you hear him instinctively throw his axe behind you.");
											String[] oyee = {"dodge"};
											int fasty = Util.reactionTest(oyee, false, 3000);
											if(fasty == 0){
												Util.intPrint("Dang, your reflexes are terrible.");
												Util.printDead();
												end = true;
												break;
											}
										} else if(nex == 4){
											break;
										} else {
											System.out.println("Invalid Input, try again.");
											continue;
										}
						
									}
									continue;
								} else if(check == 2){
									
								} else if(check == 3){
								} else if(check == 4){
								} else {
				
								}
								break;
							}
						} else if(temp == 3){
							continue;
						}
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
						//If items have special affects, put them here
						continue;
					} else if(choice == 3){
						continue;
					} else if(choice == 4){
						continue;
					} 
					break;
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
		Util.secPause(3);
	}
	public static int reactionTest(String[] words, boolean warning, int time) throws Exception { //"time" is in milliseconds
		if(warning == true){
			Util.intPrint("For this action, we will test your reaction speed.");
			Util.intPrint("Words will be printed on the screen, and you will have 1.5 seconds to type them.");
			Util.intPrint("Ready?");
			Util.secPause(1);
			System.out.println("GO!\n");
		} else {
			System.out.println("Reaction test!  GO!\n");
		}
		while(DarkMain.input.hasNext()){
			DarkMain.input.next();
		}
		String clear = DarkMain.input.next();
		System.out.println();
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
				return 0; //If you die, returns zero
			}
		}
		return 1; //if you live, returns one
	}
	public static void fightAnimal(String animal, int dam, int health) throws Exception {   //Work on this later
		System.out.println("You come across a " + animal + ".  Fight or run?");
		
	}		
}