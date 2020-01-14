package UI.Ticket;

import Controller.Ticket.ReadTicketController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Rémi Salmi
 */

public class ReadTicketUI implements UIGlobal {

    private int id;
    private boolean isAdmin = false;

    public ReadTicketUI(int id, boolean isAdmin){
        this.id=id;
        this.isAdmin = isAdmin;
    }

    @Override
    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ReadTicketController.class , new Callable<ReadTicketController>() {
            @Override
            public ReadTicketController call() throws Exception {
                return new ReadTicketController(id, isAdmin);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadTicketUI.fxml"));
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> param) {
                    Callable<?> callable = creators.get(param);
                    if (callable == null) {
                        try {
                            // default handling: use no-arg constructor
                            return param.newInstance();
                        } catch (InstantiationException | IllegalAccessException ex) {
                            throw new IllegalStateException(ex);
                        }
                    } else {
                        try {
                            return callable.call();
                        } catch (Exception ex) {
                            throw new IllegalStateException(ex);
                        }
                    }
                }
            });
            root = loader.load();
        }catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 1000, 600);
        return scene;
    }
}
