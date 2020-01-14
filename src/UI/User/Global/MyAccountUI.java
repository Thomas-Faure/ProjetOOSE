package UI.User.Global;

import BusinessLogic.User.GlobalUser;
import BusinessLogic.User.User;
import Controller.User.GlobalUser.AccountController;
import Facade.SessionFacade;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Cette Classe correspond à l'UI qui gère la page MyAccountUI.fxml
 * Gère le paramètre user qui correspond à l'utilisateur de la session qu'il
 * donnera au controller de la page
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class MyAccountUI implements UIGlobal {
    private GlobalUser user = new GlobalUser();

    public MyAccountUI(){
        User sessionUser =  SessionFacade.getInstance().getUser();
        /*System.out.println("SESSION");
        System.out.println(sessionUser.getId() + sessionUser.getUsername());

         */
        this.user.setId(sessionUser.getId());
        this.user.setUsername(sessionUser.getUsername());
        this.user.setPassword(sessionUser.getPassword());
        this.user.setFirstName(sessionUser.getFirstName());
        this.user.setLastName(sessionUser.getLastName());
        this.user.setCity(sessionUser.getCity());
        this.user.setPhoneNumber(sessionUser.getPhoneNumber());
        this.user.setEmail(sessionUser.getEmail());
        this.user.setPosition(sessionUser.getPosition());
        this.user.setAdmin(sessionUser.isAdmin());
    }

    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(AccountController.class , new Callable<AccountController>() {
            public AccountController call() throws Exception {
                /*System.out.println("MyAccountUI");
                System.out.println(user.getId() + user.getUsername());

                 */
                return new AccountController(user);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyAccountUI.fxml"));
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
