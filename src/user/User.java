package User;

public class User implements IUser{
	int id;
	String username;
	String firstname;
	String lastname;
	String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public User(int id,String username,String firstname,String lastname,String password) {
		this.id=id;
		this.username=username;
		this.firstname=firstname;
		this.lastname=lastname;
		this.password=password;
	}
	public User() {
		
	}

	public String getUsername() {
		return this.username;
	}
	public String getName() {
		return this.firstname+" "+this.lastname;
	}
}
