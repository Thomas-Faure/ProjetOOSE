package UI.Ticket;

import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * @author Rémi Salmi
 */

public class MyTicketUI implements UIGlobal {
    @Override
    public Scene loadScene() {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            root = loader.load(getClass().getResourceAsStream("MyTicketsUI.fxml"));
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
