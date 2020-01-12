package UI.Role;

import BuisnessLogic.User.Member;
import Controller.Role.AddRoleController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond à l'UI qui gère la page AddRoleUI.fxml.
 * Gere le paramètre qui lui a été donné (cf.Constructeur).
 */
public class AddRoleUI {

    private Member member;

    public AddRoleUI(Member newmember) {
        this.member = newmember;
    }

    public Scene loadScene(){
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(AddRoleController.class , new Callable<AddRoleController>() {
            public AddRoleController call() throws Exception {
                return new AddRoleController(member);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRoleUI.fxml"));
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
