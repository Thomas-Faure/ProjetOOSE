package Main;
/**
 *
 * @author Thomas Faure
 */
import UI.Menu;
import UI.Project.ProjectUI;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This class is the Application , to manage store/manage the different scenes of JavaFX
 */
public class App {
    private App(){

    }
    public static App instance;
    private Stage stage;
    private Scene scene;
    private Menu menu;

    /**
     * Method to get the instance of the application
     * @return
     */
    public static App getInstance(){
        if (instance == null){
            instance = new App();
        }
        return instance;
    }

    /**Method to get the scene menu
     * @return
     */
    public Scene getMenuScene(){
        return this.menu.loadScene();
    }

    /**
     *
     * Method to set the scene menu
     */
    public static void setMenuScene(){
        if(getInstance().menu == null){
            getInstance().menu=new Menu();
        }
        getInstance().setInstanceScene(getInstance().getMenuScene());

    }

    /**Method to get the stage of the application
     * @return
     */
    public Stage getStage(){
        return this.stage;
    }

    /**Method to get the current scene
     * @return
     */
    public Scene getScene(){
        return this.scene;
    }

    /**Method to set a stage
     * @param stage
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }

    /**Method to set a scene on the application
     * @param scene
     */
    public void setSceneApp(Scene scene){
        this.scene=scene;
    }

    /**Static method to get the stage
     * @return
     */
    public static Stage getInstanceStage(){
        return instance.getStage();
    }

    /**Static method to get the scene
     * @return
     */
    public static Scene getInstanceScene(){
        return instance.getScene();
    }

    /**Static method to set the scene
     * @param scene
     */
    public static void setInstanceScene(Scene scene){
        getInstanceStage().setScene(scene);
        getInstance().setSceneApp(scene);
    }

    /**Static method to set the stage
     * @param stage
     */
    public static void setInstanceStage(Stage stage){
        instance.setStage(stage);
    }
}
