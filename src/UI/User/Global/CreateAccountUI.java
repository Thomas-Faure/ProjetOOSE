package UI.User.Global;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CreateAccountUI {
    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("CreateAccountUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
