package group38;

import group38.apoorv.*;
import group38.aniket.*;
import java.util.*;
import group38.himanshu.*;
import group38.ritik.User;

public class Main {

	private static Repository repository = new Repository();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		/*
		 * 
		 * System.out.println("  ____ ____   ___  _   _ ____ _  ");
		 * System.out.println(" / ___|  _   / _  | | | |  _  _  ");
		 * System.out.println("| |  _| |_) | | | | | | | |_) |  ");
		 * System.out.println("| |_| |  _ <| |_| | |_| |  __/   ");
		 * System.out.println("  ____|_|  _  ___/  ___/|_|      ");
		 */

		loginMenu();
	}

	private static void loginMenu() {
		System.out.println("\n1. Login as user");
		System.out.println("2. Login as admin");
		System.out.println("3. Sign up");
		System.out.println("0. Exit");
		System.out.print("Enter a choice: ");

		int choice = sc.nextInt();
		while (!(choice >= 0 && choice <= 3)) {
			System.out.print("Please enter a valid choice: ");
			choice = sc.nextInt();
		}
		if (choice == 0)
			return;

		if (choice == 3) {
			sc.nextLine();
			new User().signup();
			loginMenu();
			return;
		}

		System.out.print("Enter email ID: ");
		String emailID = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		if (choice == 1) {
			while (!repository.checkUserCredentials(emailID, password)) {
				System.out.println("Invalid login details.");
				System.out.print("Enter email ID: ");
				emailID = sc.next();
				System.out.print("Enter password: ");
				password = sc.next();
			}

			int userID = repository.getUserID(emailID);
			System.out.println("User login successful.");
			userFunctionality(userID, password);
		} else {
			while (!repository.checkAdminCredentials(emailID, password)) {
				System.out.println("Invalid login details.");
				System.out.print("Enter email ID: ");
				emailID = sc.next();
				System.out.print("Enter password: ");
				password = sc.next();
			}

			int adminID = repository.getAdminID(emailID);
			System.out.println("Admin login successful.");
			adminFunctionality(adminID, password);
		}
	}

	private static void userFunctionality(int userID, String password) {
		while (true) {
			System.out.println("\n1. Book a cruise ship");
			System.out.println("2. Book a cargo ship");
			System.out.println("3. Search cruise ships");
			System.out.println("4. Search cargo ships");
			System.out.println("5. Cancel booking");
			System.out.println("6. List your bookings");
			System.out.println("7. Check booking status");
			System.out.println("8. Display profile");
			System.out.println("0. Logout");

			System.out.print("Enter a choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			if (choice == 0) {
				System.out.println("Logged out successfully.");
				loginMenu();
				break;
			}

			if (choice == 1)
				new CruiseBooking().book(userID);
			else if (choice == 2)
				new CargoBooking().book(userID);
			else if (choice == 3)
				new CruiseShip().listShips();
			else if (choice == 4)
				new CargoShip().listShips();
			else if (choice == 5) {
				while (true) {
					System.out.print(
							"Select class of ship\n1 for cruise ship\n2 for cargo ship\n0 to return\nEnter your choice: ");
					choice = sc.nextInt();
					sc.nextLine();
					if (choice == 1) {
						new CruiseBooking().cancelBooking();
						break;
					} else if (choice == 2) {
						new CargoBooking().cancelBooking();
						break;
					} else if (choice == 0)
						break;
					else
						System.out.println("Invalid choice entered.");
				}
			} else if (choice == 6) {
				while (true) {
					System.out.print(
							"Select class of ship\n1 for cruise ship\n2 for cargo ship\n0 to return\nEnter your choice: ");
					choice = sc.nextInt();
					sc.nextLine();
					if (choice == 1) {
						new CruiseBooking().listBookings(userID);
						break;
					} else if (choice == 2) {
						new CargoBooking().listBookings(userID);
						break;
					} else if (choice == 0)
						break;
					else
						System.out.println("Invalid choice entered.");
				}
			} else if (choice == 7) {
				while (true) {
					System.out.print(
							"Select class of ship\n1 for cruise ship\n2 for cargo ship\n0 to return\nEnter your choice: ");
					choice = sc.nextInt();
					sc.nextLine();
					if (choice == 1) {
						new CruiseBooking().getBookingStatus();
						break;
					} else if (choice == 2) {
						new CargoBooking().getBookingStatus();
						break;
					} else if (choice == 0)
						break;
					else
						System.out.println("Invalid choice entered.");
				}
			} else if (choice == 8)
				new User().displayDetails(userID);
			else
				System.out.println("Invalid choice.");
		}
	}

	private static void adminFunctionality(int adminID, String password) {
		while (true) {
			System.out.println("\n1. Add a cruise ship");
			System.out.println("2. Add a cargo ship");
			System.out.println("3. Remove a cruise ship");
			System.out.println("4. Remove a cargo ship");
			System.out.println("5. Update a cruise ship");
			System.out.println("6. Update a cargo ship");
			System.out.println("7. List all cruise ships");
			System.out.println("8. List all cargo ships");
			System.out.println("0. Logout");

			System.out.print("Enter a choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			if (choice == 0) {
				System.out.println("Logged out successfully.");
				loginMenu();
				break;
			}

			CruiseShip cruiseShip = new CruiseShip();
			CargoShip cargoShip = new CargoShip();
			if (choice == 1)
				cruiseShip.addShip();
			else if (choice == 2)
				cargoShip.addShip();
			else if (choice == 3)
				cruiseShip.removeShip();
			else if (choice == 4)
				cargoShip.removeShip();
			else if (choice == 5)
				cruiseShip.updateShip();
			else if (choice == 6)
				cargoShip.updateShip();
			else if (choice == 7)
				new CruiseShip().listFleetOfCruiseShips();
			else if (choice == 8)
				new CargoShip().listFleetOfCargoShips();
			else
				System.out.println("Invalid choice.");
		}
	}
}

/*
 * change repo = new to new Repos.
 */