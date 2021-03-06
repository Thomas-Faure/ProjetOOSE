package Controller.Role;

import BusinessLogic.Role.AbstractRole;
import BusinessLogic.Role.Role;
import BusinessLogic.User.Member;
import Facade.Role.IRoleFacade;
import Facade.Role.RoleFacade;
import Facade.User.MemberUser.IMemberFacade;
import Facade.User.MemberUser.MemberFacade;
import Main.App;
import UI.Role.AddRoleUI;
import UI.Role.AllRolesUI;
import UI.UIError;
import UI.UIGlobal;
import UI.User.Member.ReadAMemberUI;
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
 * Cette Classe correspond au contrôleur qui gère la vue "AllRolesUI".
 * Il va servir aux utilisateurs pour regarder l'ensemble des roles qui ont été
 * créés sur l'application.
 * Les membres pourront choisir un role pour leur projet dans les roles qui sont proposés par
 * l'application
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public class AllRolesController implements Initializable {

    private Member member;

    /*
    public AllRolesController(){
    }*/
    public AllRolesController(Member newmember){
        this.member = newmember;
    }


    @FXML
    private ListView<Role> rolesList ;

    private IRoleFacade roleFacade = RoleFacade.getInstance();
    private IMemberFacade memberFacade = MemberFacade.getInstance();

    private static Role toManage;

    private static ObservableList<Role> listViewTemp;

    /**
     * Permet d'initialiser l'UI correspondant avec
     * tous les roles existantes dans l'application.
     * Utilise la classe Cell propre à AllRolesController
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(rolesList != null){
            //si on peut récuperer les tickets
            if(roleFacade.getAllRoles()) {
                ArrayList<Role> listeElement = ((ArrayList) roleFacade.getListRoles());
                ObservableList<Role> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);

                rolesList.setItems(listView);
                rolesList.setCellFactory(param -> new AllRolesController.Cell());
            }
        }

    }

    /**
     * Permet de valider le choix du role pour le membre.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void validation(ActionEvent actionEvent) {
        member.setRole(toManage);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(memberFacade.modifyMember(member))){
            UIError error = new UIError( new AllRolesUI(this.member));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            rolesList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    /**
     * Permet d'annuler le choix du role pour le membre.
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
     * Permet de revenir sur la page précédente / sur la page correspondant
     * à la vue du membre (ReadAMemberUI)
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param actionEvent
     */
    public void backToPage(ActionEvent actionEvent) {
        ReadAMemberUI user = new ReadAMemberUI(this.member);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(user.loadScene().getRoot());
    }

    /**
     * Permet de valider la suppression du role courrant de l'application.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void validationD(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(!(roleFacade.deleteRole(toManage))){
            UIError error = new UIError((UIGlobal) new AllRolesUI(this.member));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm2");
            toHide.setVisible(false);
            AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
            toShow.setVisible(true);
            rolesList.getItems().remove(toManage);
            listViewTemp.remove(toManage);
            toManage = null;
        }
    }

    /**
     * Permet d'annluer la suppression du role courrant de l'application.
     * En cas d'erreur, le signifie avec un UIError.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void refuseD(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm2");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }


    /**
     * Classe propre à AllRoles qui servira à donner
     * la liste des roles existants dans la base. Chaque role
     * correspondra à une cellule dans laquelle on pourra trouver
     * les boutons nécessaires à la gestion de ces roles.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    static class Cell extends ListCell<Role> {
        Role role;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnA = new Button("Chose");
        Button btnD = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnA, btnD);

            btnA.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    cellAddRole (role);
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
                    toShow.setVisible(true);
                }
            });
            btnD.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    toManage = role;
                    AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
                    toHide.setVisible(false);
                    AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm2");
                    toShow.setVisible(true);
                }
            });
        }

        /**
         * Permet de mieux gérer les actions liées à l'appuie du bouton
         * addRole (qui permet d'associer le role au membre)
         * @author Lauren Unquera - Polytech Montpellier IG4
         */
        public void cellAddRole (AbstractRole role){
            Role newRole = new Role(role.getId(), role.getName());
            toManage = newRole;
        }

        /**
         * Permet de donner des information sur les roles
         * des cellules, ici le nom des roles.
         * @author Lauren Unquera - Polytech Montpellier IG4
         */
        public void updateItem(Role name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                this.role = name;
                label.setText(name.getId()+" "+name.getName());
                setGraphic(hbox);
            }

        }

    }

    /**
     * Redirige l'utilisateur sur la page de création de role.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public void CreateRole(ActionEvent actionEvent) {
        AddRoleUI role = new AddRoleUI(this.member);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(role.loadScene().getRoot());
    }
}
