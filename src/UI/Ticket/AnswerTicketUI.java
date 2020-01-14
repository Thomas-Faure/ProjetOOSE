package UI.Ticket;

import Controller.Ticket.AnswerTicketController;
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

public class AnswerTicketUI implements UIGlobal {

    private int id;

    public AnswerTicketUI(int id){
        this.id=id;
    }

    @Override
    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(AnswerTicketController.class , new Callable<AnswerTicketController>() {
            @Override
            public AnswerTicketController call() throws Exception {
                return new AnswerTicketController(id);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnswerTicketUI.fxml"));
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
