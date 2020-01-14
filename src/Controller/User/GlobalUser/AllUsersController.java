package Controller.User.GlobalUser;


import BusinessLogic.User.GlobalUser;
import BusinessLogic.User.User;
import Facade.Session.SessionFacade;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;
import Main.App;
import UI.UIError;
import UI.User.Global.AddUserUI;
import UI.User.Global.AllUsersUI;
import UI.User.Global.ModifyUserUI;
import UI.User.Global.ReadAUserUI;
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
 * Cette Classe correspond au contrôleur qui gère la vue "AllUsersUI".
 * Il va servir aux utilisateurs pour regarder l'ensemble des utilisateurs qui ont été
 * créées et modifiées sur l'application.
 * Les utilisateurs admin pourront créer, ajouter ou modifier un utilisateur
 * partir de cette interfce.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class AllUsersController implements Initializable {

    int id;

    public AllUsersController(){
    }
    public AllUsersController(int id){
        this.id=id;
    }


    @FXML
    private ListView<User> usersList ;

    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();

    private static User toManage;

    private static ObservableList<User> listViewTemp;

    /**
     * Permet d'initialiser la page avec
     * tous les utilisateurs existants dans l'application.
     * Utilise la classe Cell propre à AllUsersController
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(usersList != null){
            //si on peut récuperer les tickets
            if(userFacade.getAllUsers()) {
                ArrayList<User> listeElement = ((ArrayList) userFacade.getListUsers());
                ObservableList<User> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                usersList.setItems(listView);
                usersList.setCellFactory(param -> new AllUsersController.Cell());
            }
        }

    }

    /**
     * Permet de valider la suppression courrante d'un utilisateur.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(userFacade.deleteUser(toManage))){
            UIError error = new UIError( new AllUsersUI());
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            usersList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    /**
     * Permet d'annuler la suppression courrante d'un utilisateur.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void refuse(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    /**
     * Classe propre à AllUsers qui servira à donner
     * la liste des utilisateurs existants dans la base. Chaque utilisateur
     * correspondra à une cellule dans laquelle on pourra trouver
     * les boutons nécessaires à la gestion de ces utilisateurs.
     * Ces boutons dependent de si l'utilisateur de la session est un
     * simple utilisateur sans droit (il n'aura accès qu'aux noms et prénoms des
     * utilisateurs lisibles dans les cellules), ou si c'est un admin (il pourra donc
     * modifier, ajouter, ou supprimer un utilisateur)
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    static class Cell extends ListCell<User> {
        User user;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnR = new Button("Read");
        Button btnD = new Button("Delete");
        Button btnU = new Button("Modify");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            if (SessionFacade.getInstance().getUser().isAdmin()) {
                hbox.getChildren().addAll(img, label, pane, btnR, btnD, btnU);
            }
            else {
                hbox.getChildren().addAll(img, label, pane, btnR);
            }

            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    toManage = user;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
            btnU.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ModifyUserUI updatePage = new ModifyUserUI((GlobalUser) user);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(updatePage.loadScene().getRoot());

                }
            });
            btnR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    ReadAUserUI read = new ReadAUserUI((GlobalUser) user);
                    HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");

                    box.getChildren().remove(1);
                    box.getChildren().add(read.loadScene().getRoot());

                }
            });


        }

        /**
         * Permet de donner des information sur les utilisateurs
         * des cellules, ici le nom et prénom des utilisateurs.
         * @author Lauren Unquera - Polytech Montpellier IG4
         */
        public void updateItem(User name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                user = name;
                label.setText(name.getId()+" "+name.getFirstName()+" "+ name.getLastName());
                setGraphic(hbox);
            }

        }

    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * Redirige l'utilisateur sur la page de création d'un
     * utilisateur.
     */
    public void addNewUser(ActionEvent actionEvent) {
        AddUserUI user = new AddUserUI();
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }
}
