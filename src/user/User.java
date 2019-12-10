package user;

public class User implements IUser{
	String username;
	String firstname;
	String lastname;
	public User(String username,String firstname,String lastname) {
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
	}
	public String getUsername() {
		return this.username;
	}
	public String getName() {
		return this.firstname+" "+this.lastname;
	}
}
