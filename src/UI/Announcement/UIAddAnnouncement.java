package UI.Announcement;

import UI.UIGlobal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIAddAnnouncement extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		//� remplacer avec la bonne page
		Parent root = FXMLLoader.load(getClass().getResource("AddAnnouncementPage.fxml"));
		Scene scene = new Scene(root, 1000, 600);
		   
        stage.setResizable(false);
		TextField titleField = (TextField) scene.lookup("#inputTitle");
		TextField messageField = (TextField) scene.lookup("#inputMessage");
		Button btn = (Button) scene.lookup("#addNewAnnouncementButton");
		final Text actiontarget = (Text) scene.lookup("#info");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Button pushed !");
			}
		});
		Button btnBack = (Button) scene.lookup("#backButton");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Button pushed ! back to menu");
			}
		});
		
		stage.setScene(scene);
		
		stage.show();
		
	}

}