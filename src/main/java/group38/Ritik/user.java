package Ritik;

public class user{
	int userID;
	long phoneNumber;
	String name;
	int age;
	private enum Gender { MALE, FEMALE };
	Gender gender;
	String email;
	String password;

	public void setUserID(int userID) {
  	this.userID = userID;
	}
	
	public int getUserID() {
  	return userID;
	}
	public void setPhoneNumber(long phoneNumber) {
  	this.phoneNumber = phoneNumber;
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
	public void setAge(int age) {
  	this.age = age;
	}
	
	public int getAge() {
  	return age;
	}
	public void setGender(Gender gender) {
  	this.gender = gender;
	}
	
	public Gender getGender() {
  	return gender;
	}
	public void setEmail(String email) {
  	this.email = email;
	}
	
	public String getEmail() {
  	return email;
	}
	public void setPassword(String password) {
  	this.password = password;
	}
	
	public String getPassword() {
  	return password;
	}

}

