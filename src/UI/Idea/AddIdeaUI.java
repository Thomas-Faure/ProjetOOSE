package UI.Idea;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Cette Classe correspond à l'UI qui gère la page AddIdeaUI.fxml
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
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

}
