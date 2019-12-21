package UI.Announcement;

import Main.App;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class UIAddAnnouncement implements UIGlobal {



	public UIAddAnnouncement(){

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

		TextField title = (TextField) scene.lookup("#title");
		TextArea description = (TextArea) scene.lookup("#message");

		Button btnBack = (Button) scene.lookup("#backButton");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AnnouncementUI announcement = new AnnouncementUI();
				HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
				box.getChildren().remove(1);
				box.getChildren().add(announcement.loadScene().getRoot());
			}
		});
		Button btn = (Button) scene.lookup("#addAnnouncement");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println(title.getText());
				System.out.println(description.getText());
				addAnnouncement(title.getText(),description.getText());


			}
		});

		return scene;
	}


}
