class user{
	int userID;
	long phoneNumber;
	String name;
	int age;
	private enum Gender { MALE, FEMALE };
	Gender gender;
	String email;
	String password;

	public setUserID(int userID) {
  	this.userID = userID;
	}
	
	public getUserID() {
  	return userID;
	}
	public setPhoneNumber(long phoneNumber) {
  	this.phoneNumber = phoneNumber;
	}
	
	public getPhoneNumber() {
  	return phoneNumber;
	}
	public setName(String name) {
  	this.name = name;
	}
	
	public getName() {
  	return name;
	}
	public setAge(int age) {
  	this.age = age;
	}
	
	public getAge() {
  	return age;
	}
	public setGender(Gender gender) {
  	this.gender = gender;
	}
	
	public getGender() {
  	return gender;
	}
	public setEmail(String email) {
  	this.email = email;
	}
	
	public getEmail() {
  	return email;
	}
	public setPassword(String password) {
  	this.password = password;
	}
	
	public getPassword() {
  	return password;
	}

}

