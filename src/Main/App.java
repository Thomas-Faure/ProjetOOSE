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

public class App {
    private App(){

    }

    public static App instance;
    private Stage stage;
    private Scene scene;
    private Menu menu;
    public static App getInstance(){
        if (instance == null){
            instance = new App();
        }
        return instance;
    }
    public Scene getMenuScene(){
        return this.menu.loadScene();
    }
    public static void setMenuScene(){
        if(getInstance().menu == null){
            getInstance().menu=new Menu();
        }
        getInstance().setInstanceScene(getInstance().getMenuScene());
        ProjectUI meetingsPage = new ProjectUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(meetingsPage.loadScene().getRoot());
    }
    public Stage getStage(){
        return this.stage;
    }
    public Scene getScene(){
        return this.scene;
    }
    public void setStage(Stage stage){
        this.stage=stage;
    }
    public void setSceneApp(Scene scene){
        this.scene=scene;
    }
    public static Stage getInstanceStage(){
        return instance.getStage();
    }
    public static Scene getInstanceScene(){
        return instance.getScene();
    }

    public static void setInstanceScene(Scene scene){
        getInstanceStage().setScene(scene);
        getInstance().setSceneApp(scene);
    }
    public static void setInstanceStage(Stage stage){
        instance.setStage(stage);
    }
}
