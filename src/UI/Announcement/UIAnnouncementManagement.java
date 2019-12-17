package UI.Announcement;

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

public class UIAnnouncementManagement extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		//à remplacer avec la bonne page
		Parent root = FXMLLoader.load(getClass().getResource("AnnouncementManagerPage.fxml"));
		Scene scene = new Scene(root, 1000, 600);
		   
        stage.setResizable(false);
        
        //search bar
		TextField searchField = (TextField) scene.lookup("#inputSearch");
		Button searchButton = (Button) scene.lookup("#buttonSearch");
		
		
		//add an announcement
		Button btnAddAnnouncement = (Button) scene.lookup("#addNewAnnouncementButton");
		btnAddAnnouncement.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Button pushed !");
			}
		});
		
		//go to previous page
		Button btnPrevious = (Button) scene.lookup("#PreviousButton");
		btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Button pushed !");
			}
		});
		//go to the next page
		Button btnNext = (Button) scene.lookup("#NextButton");
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Button pushed !");
			}
		});
		
		
		

		
		stage.setScene(scene);
		
		stage.show();
		
	}

}
