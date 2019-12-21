package UI;

import Main.App;
import UI.Announcement.AnnouncementUI;
import UI.Announcement.UIAddAnnouncement;
import UI.Login.UILogin;
import UI.Task.TaskUI;
import UI.Task.UIAddTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class Menu implements UIGlobal {

    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("PPMUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);

        return scene;
    }
}
