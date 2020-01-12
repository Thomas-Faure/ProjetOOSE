package UI.Idea;

import BuisnessLogic.Idea.AbstractIdea;
import Controller.Idea.ModifyIdeaController;
import UI.UIGlobal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond à l'UI qui gère la page AddIdeaUI.fxml.
 * Gere le paramètre qui lui a été donné (cf.Constructeur).
 */
public class ModifyIdeaUI implements UIGlobal {

    private AbstractIdea idea;

    public ModifyIdeaUI(AbstractIdea idea){
        this.idea= idea;
    }

    @Override
    public Scene loadScene() {
        Map<Class, Callable<?>> creators = new HashMap<>();
        creators.put(ModifyIdeaController.class , new Callable<ModifyIdeaController>() {
            public ModifyIdeaController call() throws Exception {
                return new ModifyIdeaController(idea);
            }
        });
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyIdeaUI.fxml"));
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
