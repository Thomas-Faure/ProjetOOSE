package UI.User.Global;

import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AllUsersUI implements UIGlobal {
    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AllUsersUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
