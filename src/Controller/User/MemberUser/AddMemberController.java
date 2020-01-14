package Controller.User.MemberUser;

import BusinessLogic.Project.AbstractProject;
import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.Member;
import BusinessLogic.User.User;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;
import Facade.User.MemberUser.IMemberFacade;
import Facade.User.MemberUser.MemberFacade;
import Main.App;
import UI.UIError;
import UI.User.Global.AllUsersUI;
import UI.User.Member.AddMemberUI;
import UI.User.Member.AllMembersUI;
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
 * Cette Classe correspond au contrôleur qui gère la vue "AddMemberUI".
 * Il va servir aux utilisateurs (admins) pour créer/ajouter un membre au projet
 * qui a été donné en paramètre.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class AddMemberController implements Initializable {

    private AbstractProject project;

    public AddMemberController(){
    }
    public AddMemberController(AbstractProject project){
        this.project = project;
    }


    @FXML
    private ListView<User> usersList ;

    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
    private IMemberFacade memberFacade = MemberFacade.getInstance();

    private static Member toManage;

    //permet de garder la liste de base
    private static ObservableList<User> listViewTemp;

    /**
     * Sous fonction utilisée pour regarder si un utilisateur
     * passé en paramètre de la fonction est un membre du projet (qui a été
     * passé en paramètre lors de la création du controller)
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public boolean estMembre(AbstractUser user){

        for (int i = 0; i < memberFacade.getListMembers().size(); i++){
            if (memberFacade.getListMembers().get(i).getId() == user.getId() ){
                return true;
            }

        }
        return false;

    }

    /**
     * Permet d'initialiser la page avec
     * tous les utilisateurs existants dans l'application. Ils pourront
     * par la suite être ajoutés au projet et devenir membre ou non
     * Utilise la classe Cell propre à AddMemberController
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(usersList != null){
            if(userFacade.getAllUsers()) {
                ArrayList<User> listeElement = new ArrayList<>();
                for (int i = 0; i < userFacade.getListUsers().size(); i++){
                    if (! estMembre(userFacade.getListUsers().get(i))){
                        listeElement.add((User) userFacade.getListUsers().get(i));
                    }
                }

                ObservableList<User> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                usersList.setItems(listView);
                usersList.setCellFactory(param -> new AddMemberController.Cell(this.project));
            }
        }

    }

    /**
     * Permet de valider l'ajout courrant d'un membre au projet.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(memberFacade.addMember(toManage))){
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
            AddMemberUI user = new AddMemberUI(this.project);
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(user.loadScene().getRoot());
        }
    }

    /**
     * Permet d'annuler l'ajout courrant d'un membre au projet.
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
     * Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Back".
     * Elle permet de rediriger l'utilisateur sur la page de la "AllMembersUI" qui
     * était la page précédente avant qu'il arrive sur celle-ci ("AddMemberUI").
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void backToPage(ActionEvent actionEvent) {
        AllMembersUI user = new AllMembersUI(this.project);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }


    /**
     * Classe propre à AddMemberController qui servira à donner
     * la liste des utilisateurs existants dans la base. Chaque utilisateur
     * correspondra à une cellule dans laquelle on pourra trouver
     * les boutons nécessaires à la gestion. Ainsi les utilisateurs
     * de l'application existants peuvent être ajoutés en tant que membre du projet.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public static class Cell extends ListCell<User> {
        AbstractProject cellProject;
        User user;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnA = new Button("Add");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell(AbstractProject project) {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);
            this.cellProject = project;

            hbox.getChildren().addAll(img, label, pane, btnA);

            btnA.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    cellAddMember (user);
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
        }

        /**
         * Fonction associée à l'appuie du boutton "add" de la cellule.
         * Permet de créer un membre à partir des informations de l'utilisateur
         * de cette cellule. Transmet ce membre à l'attribut "toManage" du controller
         * pour pouvoir l'ajouter lors de la validation.
         * @author Lauren Unquera - Polytech Montpellier IG4
         */
        public void cellAddMember (User user){
            Member newMember = new Member(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
                    user.getCity(), user.getPhoneNumber(), user.getEmail(), user.getPosition(), user.isAdmin());
            newMember.setProject(this.cellProject);
            toManage = newMember;
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
}
