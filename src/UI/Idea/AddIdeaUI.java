package UI.Idea;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AddIdeaUI {
    
    //private AddIdeaController iController;
    
    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddIdeaUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }

    public void backToIdeaPage(ActionEvent actionEvent) {
    }

    public void addNewIdea(ActionEvent actionEvent) {
    }
}
