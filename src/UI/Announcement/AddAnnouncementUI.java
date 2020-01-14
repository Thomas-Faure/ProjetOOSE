package UI.Announcement;
/**
 *
 * @author Thomas Faure
 */
import UI.UIGlobal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class AddAnnouncementUI implements UIGlobal {
	public AddAnnouncementUI(){
	}
	public boolean addAnnouncement(String title,String description){
		return false;
	}
	public Scene loadScene(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AddAnnouncementUI.fxml"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);
		return scene;
	}
}
