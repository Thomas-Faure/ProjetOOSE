package DAO;

public interface ChatDAO {

	public void createAnnouncement(String identifiant,String username);
	public boolean save();
	public boolean update();

}
