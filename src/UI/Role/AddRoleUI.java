package UI.Role;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddRoleUI {
    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddRoleUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
