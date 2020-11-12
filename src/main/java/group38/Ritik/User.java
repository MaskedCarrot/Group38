package Ritik;

import java.util.Scanner;

import apoorv.*;

public class User {
	private int userID;
	private long phoneNumber;
	private String name;
	private int age;
	private char gender;
	private String email;
	private String password;
	
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

	public boolean addUser(){
		Repository repository = new Repository();
		Scanner sc = new Scanner(System.in);
		User user = new User();
		System.out.print("Enter name: ");
		user.name = sc.nextLine();
		System.out.print("Enter phone number: ");
		user.phoneNumber = sc.nextLong();
		System.out.print("Enter age: ");
		user.age = sc.nextInt();
		System.out.print("Enter gender (M or F): ");
		user.gender = sc.nextLine().charAt(0);
		while(user.gender!='M'&&user.gender!='F'){
			System.out.print("Enter gender (M or F): ");
			user.gender = sc.nextLine().charAt(0);
		}
		System.out.print("Enter Password (No Spaces Allowed)");
		user.password = sc.next();
		System.out.println("Enter Email");
		user.email = sc.nextLine();
		while (!repository.isUniqueEmail(user.email)) {
			System.out.println("Email Already Registered!, Enter New Email");
			user.email=sc.nextLine();
		}
		boolean b=repository.addUser(this);
		return b;
	} 
	public boolean displayDetails(int userID)
	{
		Repository repository = new Repository();
		boolean b=repository.addUser(user);
		return b;
	}
}
