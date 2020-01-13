package UI.Announcement;
/**
 *
 * @author Thomas Faure
 */
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AnnouncementManagementUI implements UIGlobal {
	public AnnouncementManagementUI(){
	}
	public Scene loadScene(){
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader();
			root = loader.load(getClass().getResourceAsStream("AnnouncementUI.fxml"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);
		return scene;
	}
}
