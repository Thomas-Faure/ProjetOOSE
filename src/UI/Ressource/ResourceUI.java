package UI.Ressource;

import Controller.Resource.ResourceController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ResourceUI implements UIGlobal {
    private ResourceController rController;

    @Override
    public Scene loadScene(){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(getClass().getResourceAsStream("ResourceUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
