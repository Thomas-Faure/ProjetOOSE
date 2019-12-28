package UI.Task;

import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TaskUI implements UIGlobal {
    public TaskUI(){
    }
    public Scene loadScene(){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(getClass().getResourceAsStream("TaskManagementUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
