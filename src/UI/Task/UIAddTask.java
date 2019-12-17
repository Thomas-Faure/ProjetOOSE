package UI.Task;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UIAddTask extends Application{

	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("../PPMUI.fxml"));
		
		Scene scene = new Scene(root, 1000, 600);
		
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("testUI.fxml"));
		AnchorPane pane = loader2.load();
		AnchorPane actualPane = (AnchorPane) scene.lookup("#toFill");
		actualPane.getChildren().add(pane);
        stage.setResizable(false);

		stage.setScene(scene);
		
		stage.show();
		
	}



}
