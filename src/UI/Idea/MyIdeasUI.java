package UI.Idea;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Cette Classe correspond à l'UI qui gère la page MyIdeasUI.fxml
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class MyIdeasUI {
    public Scene loadScene(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MyIdeasUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
