package UI.User.Global;

import BusinessLogic.User.GlobalUser;
import Controller.User.GlobalUser.ReadAUserController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Cette Classe correspond à l'UI qui gère la page ReadAUserUI.fxml.
 * Gere le paramètre qui lui a été donné (cf.Constructeur).
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class ReadAUserUI implements UIGlobal {
    private GlobalUser user;

    public ReadAUserUI(GlobalUser user){
        this.user= user;
    }


    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ReadAUserController.class , new Callable<ReadAUserController>() {
            public ReadAUserController call() throws Exception {
                return new ReadAUserController(user);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReadAUserUI.fxml"));
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
