package group38.ritik;
import java.util.Scanner;
import group38.apoorv.*;

public class User {
	private int userID;
	private long phoneNumber;
	private String name;
	private int age;
	private char gender;
	private String email;
	private String password;

	public User() {
		userID = -1;
		phoneNumber = -1L;
		name = "";
		age = -1;
		gender = '?';
		email = "";
		password = "";
	}

	public User(int userID, long phoneNumber, String name, int age, char gender, String email, String password) {
		this.userID = userID;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.password = password;
	}

	public void signup() {
		System.out.print("Enter name: ");
		Scanner sc = new Scanner(System.in);
		name = sc.nextLine();
		System.out.print("Enter phone number: ");
		phoneNumber = sc.nextLong();
		System.out.print("Enter age: ");
		age = sc.nextInt();
		System.out.print("Enter gender (M or F): ");
		sc.nextLine();
		gender = sc.nextLine().charAt(0);
		while (gender != 'M' && gender != 'F') {
			System.out.print("Please enter M or F: ");
			gender = sc.nextLine().charAt(0);
		}

		System.out.print("Enter email ID: ");
		email = sc.next();
		System.out.print("Enter password (should not contain spaces): ");
		password = sc.next();
		Repository repository = new Repository();
		while (!repository.isUniqueEmail(email)) {
			System.out.print("Email ID already registered, enter new email ID: ");
			email = sc.next();
			System.out.print("Enter password (should not contain spaces): ");
			password = sc.next();
		}

		if (repository.addUser(this))
			System.out.println("Signed up successfully.");
		else
			System.out.println("Sign up failed.");
	}

	public void displayDetails(int userID) {
		Repository repository = new Repository();
		User user = repository.displayUserDetails(userID);
		if (user == null) {
			System.out.println("Could not display user details.");
			return;
		}

		System.out.println("Name: " + user.name);
		System.out.println("Age: " + user.age);
		System.out.println("Gender: " + user.gender);
		System.out.println("Phone number: " + user.phoneNumber);
	}

	public int getUserID() {
  		return userID;
	}
	
	public long getPhoneNumber() {
  		return phoneNumber;
	}

	public void setName(String name) {
  		this.name = name;
	}
	
	public String getName() {
  		return name;
	}
	
	public int getAge() {
  		return age;
	}
	
	public char getGender() {
  		return gender;
	}
	
	public String getEmail() {
  		return email;
	}

	public String getPassword() {
  		return password;
	}
}
