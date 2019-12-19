package UI.Announcement;

import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.User.User;
import Controller.AnnouncementController;
import Controller.TaskController;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class UIAddAnnouncement implements UIGlobal {

	AnnouncementController AnnouncementController;

	public UIAddAnnouncement(){
		this.AnnouncementController=new AnnouncementController();
	}

	public boolean addAnnouncement(String title,String description){
		//return AnnouncementController.addAnnouncement(title,description);
		return true;
	}


	public Scene loadScene(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AddAnnouncementUI.fxml"));
		}catch(Exception e){
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);

		TextField title = (TextField) scene.lookup("#title");
		TextArea description = (TextArea) scene.lookup("#description");

		Button btn = (Button) scene.lookup("#addAnnouncement");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addAnnouncement(title.getText(),description.getText());




			}
		});

		return scene;
	}


}
