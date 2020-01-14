package Controller.Idea;


import BusinessLogic.Idea.AbstractIdea;
import BusinessLogic.Idea.Idea;
import Facade.Idea.IIdeaFacade;
import Facade.Idea.IdeaFacade;
import Facade.Session.SessionFacade;
import Main.App;
import UI.Idea.AddIdeaUI;
import UI.Idea.IdeaBoxUI;
import UI.Idea.ModifyIdeaUI;
import UI.Idea.ReadAIdeaUI;
import UI.UIError;
import UI.UIGlobal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au contrôleur qui gère la vue "IdeaBoxUI".
 * Il va servir aux utilisateurs pour regarder l'ensemble des idées qui ont été
 * créées et modifiées sur l'application.
 * Les utilisateurs admin pourront valider, refuser ou modifier une idée à
 * partir de cette interfce.
 */
public class IdeaBoxController implements Initializable {

    public IdeaBoxController(){
    }

    @FXML
    private ListView<AbstractIdea> ideasList ;

    private IIdeaFacade ideaFacade = IdeaFacade.getInstance();

    private static AbstractIdea toManage;

    //permet de garder la liste de base
    private static ObservableList<AbstractIdea> listViewTemp;


    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction ne trouve pas son utilité dans cette version de
     * l'application mais elle peut être utilisée ou modifiée plus tard si l'on veut pourvoir
     * revenir sur une certaine page
     * @param actionEvent
     */
    @FXML
    public void backToIdeaPage(ActionEvent actionEvent) {
        IdeaBoxUI idea = new IdeaBoxUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }

    /*
    @FXML
    public void searchBar(KeyEvent keyEvent) {

        if(!(inputSearch.getText().length() == 0)) {
            ArrayList<AbstractProject> array = new ArrayList<>(listViewTemp);
            ArrayList<AbstractProject> toDelete = new ArrayList<>();
            for (int i = 0; i < array.size(); ++i) {
                String inputS =inputSearch.getText();
                if(inputS.charAt(0) == '*'){
                    inputS= "\\"+inputS;
                }
                String regex = "(.*)" + inputS + "(.*)";
                if (array.get(i).getName().matches(regex)) {

                } else {
                    toDelete.add(array.get(i));
                }
            }

            for (AbstractProject i : toDelete) {
                array.remove(i);
            }
            ObservableList<AbstractProject> listViewT = FXCollections.observableArrayList(array);
            projectsList.setItems(listViewT);

        }else{
            projectsList.setItems(listViewTemp);
        }

    }

     */

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet d'initialiser la boite à idée avec
     * toutes les idées existantes dans l'application.
     * Utilise la classe Cell propre à Idea Box
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(ideasList != null){
            //si on peut récuperer les tickets
            if(ideaFacade.getAllIdeas()) {
                ArrayList<Idea> listeElement = ((ArrayList) ideaFacade.getListIdeas());
                ObservableList<AbstractIdea> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                ideasList.setItems(listView);
                ideasList.setCellFactory(param -> new IdeaBoxController.Cell());
            }
        }

    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de valider la suppression courrante d'une idée.
     * En cas d'erreur, le signifie avec un UIError.
     */
    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(ideaFacade.deleteIdea(toManage))){
            UIError error = new UIError((UIGlobal) new IdeaBoxUI());
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            ideasList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet d'annuler la suppression courrante d'une idée.
     * En cas d'erreur, le signifie avec un UIError.
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }


    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Classe propre à IdeaBox qui servira à donner
     * la liste des idées existantes dans la base. Chaque idée
     * correspondra à une cellule dans laquelle on pourra trouver
     * les boutons nécessaires à la gestion de ces idées.
     * Ces boutons dependent de si l'utilisateur de la session est un
     * simple utilisateur sans droit (il aura donc accès seulement à la
     * lecture des idées), ou si c'est un admin (il pourra donc refuse,
     * validate, read et modify n'importe quelle idée)
     */
    static class Cell extends ListCell<AbstractIdea> {
        AbstractIdea idea;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Refuse");
        Button btnV = new Button("Validate");
        Button btnU = new Button("Modify");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            if (SessionFacade.getInstance().getUser().isAdmin()) {
                hbox.getChildren().addAll(img, label, pane, btnR, btnV, btnD, btnU);
            }
            else {
                hbox.getChildren().addAll(img, label, pane, btnR);
            }
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    toManage = idea;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
            btnU.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ModifyIdeaUI updatePage = new ModifyIdeaUI(idea);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(updatePage.loadScene().getRoot());

                }
            });
            btnV.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    idea.setState("Validated");
                    IdeaFacade.getInstance().modifyIdea(idea);
                    IdeaBoxUI updatePage = new IdeaBoxUI();
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
                    box.getChildren().remove(1);
                    box.getChildren().add(updatePage.loadScene().getRoot());
                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    ReadAIdeaUI read = new ReadAIdeaUI(idea);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });
        }

        /**
         * @author Lauren Unquera - Polytech Montpellier IG4
         * @Description Permet de donner des information sur les idées
         * des cellules, ici le nom des idée.
         */
        public void updateItem(AbstractIdea name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                idea = name;
                label.setText(name.getId()+" "+name.getName());
                setGraphic(hbox);
            }

        }

    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Redirige l'utilisateur sur la page de création d'idée.
     */
    public void addNewIdea(ActionEvent actionEvent) {
        AddIdeaUI idea = new AddIdeaUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(idea.loadScene().getRoot());
    }
}
