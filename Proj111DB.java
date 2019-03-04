package project111;
import java.sql.Connection;
import java.util.Scanner;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Proj111DB extends queries{
	public static Connection connxn;
	public static Scanner scan = new Scanner(System.in);

	public static void gamblerOptions() { //user
		System.out.println("Where do you want to begin?");
		System.out.println("1: Search all players");
		System.out.println("2: Search specific players"); //can search by team, position,
		//System.out.println("4: Search about teams");
		System.out.println("3: Search about statistics"); //search matches, results in statistics
		// System.out.println("5: Search players by state, position, and 3-point percentage");
		System.out.println("0: Back");
		int userIn = scan.nextInt();

		if (userIn < 0 || userIn > 3) {
			System.out.println("Invalid input, attempt again.\n");
			gamblerOptions();
		}

		// 1: Search all players
		if(userIn == 1) {
			userOpt1();
		}

		// 2: Search specific players
		//can search by team, position
		else if(userIn == 2) {
			System.out.println("Please enter how you want to find players - either by 1 (team) or 2 (position): ");
			int inputType = scan.nextInt();

			if (inputType == 1) {
				System.out.println("You entered: " + inputType);
				System.out.println("Please enter which team:\n");
				System.out.println("1000 (WARRIORS)\t2000 (SPURS)\t3000 (ROCKETS)\t4000 (CLIPPERS)\n"
						+"5000 (JAZZ)\t6000 (THUNDER)\t7000 (GRIZZLIES)8000 (TRAILBLAZERS)");
				inputType = scan.nextInt();
				System.out.println("You entered: " + inputType);
				userOpt2a(inputType);
			}
			else if(inputType == 2) {
				System.out.println("You entered: " + inputType);
				System.out.println("Please enter which position:\n");
				System.out.print("1 (PG)\t2 (SG)\t3 (SF)\t4 (PF)\t5(C): ");
				inputType = scan.nextInt();
				System.out.println("You entered: " + inputType);
				userOpt2b(inputType);
			}
			else {
				System.out.println("Invalid option.");
				gamblerOptions();
			}
		}

		// 4: Search about teams
		// //can search by team name, then by players -> call userOpt1();
		
		/*else if(userIn == 4) {
			System.out.println("Please enter which team:\n");
			System.out.println("1000 (WARRIORS)\t2000 (SPURS)\t3000 (ROCKETS)\t4000 (CLIPPERS)\n"
					+"5000 (JAZZ)\t6000 (THUNDER)\t7000 (GRIZZLIES)8000 (TRAILBLAZERS)");
			int teamSearch = scan.nextInt();
			System.out.println("You entered: " + teamSearch);
			System.out.println("Running userOpt3...");
			userOpt3(teamSearch);
		}*/

		// 3: Search about statistics
		//search matches, results in statistics
		else if (userIn == 3){
			System.out.println("Indicate between which teams with the numbers below:");
			System.out.print("118 (WARRIORS vs. TRAILBLAZERS)\n"
											+"127 (SPURS vs. GRIZZLIES)\n"
											+"136 (ROCKETS vs. THUNDER)\n"
											+"145 (CLIPPERS vs. JAZZ)\n"
											+"215 (WARRIORS vs. JAZZ)\n"
											+"223 (SPURS vs. ROCKETS)\n"
											+"312 (WARRIORS vs. SPURS)\n");
			int matchStat = scan.nextInt();
			System.out.println("\nYou entered: " + matchStat);
			userOpt3(matchStat);
			/*System.out.println("\nWhat would you like to see?");
			System.out.println("1: Final Scores");
			System.out.println("2: Full Box Score"); //need to sort by game
			System.out.println("3: Player-specific Box Score");
			int scoreType = scan.nextInt();
			userOpt4(matchStat, scoreType);
			userOpt4(matchStat);*/
		}

		/*else if(userIn == 5){
			System.out.println("Please enter a state you wish to search in:");
			System.out.print("(You may enter: CALIFORNIA, OkLAHOMA, OREGON, TENNESSEE, TEXAS, or UTAH): ");
			String strState = scan.nextLine();
			System.out.println("Please enter which position:\n");
			System.out.print("1 (PG)\t2 (SG)\t3 (SF)\t4 (PF)\t5 (C): "); //position
			int intPosition = scan.nextInt();
			System.out.print("At least what percentage? (numbers from 0-1)"); //points
			int minScore = scan.nextInt();

			//userOpt5(strState, intPosition, minScore);
		}*/


    // 0: Back
    else if (userIn == 0) {
      System.out.println("Back to the start menu.\n");
      chooseUserDBAdmin();
    }

    gamblerOptions();

	}
	public static void riggerOptions() { //admin
			System.out.println("Pick your poison.");
			System.out.println("1: Insert statistics");
			//System.out.println("2: Update statistics");
			System.out.println("0: Back");
			int adminIn = scan.nextInt();

			if(adminIn < 0 || adminIn > 1) {
				System.out.println("Invalid input, attempt again.\n");
				riggerOptions();
			}

			else if(adminIn == 1) { // Insert
				adminOpt1();
				riggerOptions();
			}
			/*
			else if(adminIn == 2) { // Insert

			}
			*/
			else if (adminIn == 0) {
			      System.out.println("Back to the start menu.\n");
			      chooseUserDBAdmin();
			    }

	}

	public static void end(){
		scan.close();
		System.exit(0);
	}

	public static void chooseUserDBAdmin() {
		System.out.println("Are you here to gamble (user) or here to rig the results (admin)?\n\n"
				+ "(enter 1 for user, 2 for admin, 0 to quit)");

		System.out.println("1. User");
		System.out.println("2. Admin");
		System.out.println("0. Quit");

		int input = scan.nextInt();
		if(input < 0 || input > 2){
			System.out.println("ERROR: Input invalid. Let's try this again.");
			System.out.println();
			chooseUserDBAdmin();
		}
		if(input == 1){		//user
			System.out.println("Good luck user! \n");
			gamblerOptions();
		}
		else if(input == 2){ //admin
			System.out.println("You're slimy... Don't leave any traces. \n");
			riggerOptions();
		}
		else{
			System.out.println("Program Finished");
			end();
		}
	}


	public static void main(String[] args){
		System.out.println("So you're trying to see if your bet on the 2017 Western Conference Finals was correct...");
		chooseUserDBAdmin();
	}
}
