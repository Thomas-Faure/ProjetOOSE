package Controller.User.MemberUser;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.Role.Role;
import BuisnessLogic.User.Member;
import Facade.Role.IRoleFacade;
import Facade.Role.RoleFacade;
import Facade.User.MemberUser.IMemberFacade;
import Facade.User.MemberUser.MemberFacade;
import Main.App;
import UI.Role.AllRolesUI;
import UI.UIError;
import UI.UIGlobal;
import UI.User.Member.AllMembersUI;
import UI.User.Member.ReadAMemberUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
 * @Description Cette Classe correspond au contrôleur qui gère la vue "ReadAMemberUI".
 * Il va servir pour lire le membre courrant qui a été
 * passée en paramètre.
 */
public class ReadAMemberController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    private Member member;

    public ReadAMemberController (Member newmember){

        this.member = newmember;

    }

    @FXML
    private ListView<Role> rolesList ;
    private IRoleFacade roleFacade = RoleFacade.getInstance();
    private IMemberFacade memberFacade = MemberFacade.getInstance();
    private static Role toManage;
    private static ObservableList<Role> listViewTemp;

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
     * @Description Cette fonction permet d'initialiser la page en entrant
     * dans les champs correspondants les valeurs des attributs du membre et
     * de présenter le rôle que le memr=bre a dans le projet associé
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        username.setText(member.getUsername());
        firstName.setText(member.getFirstName());
        lastName.setText(member.getLastName());
        if(rolesList != null){
            if(roleFacade.getAllRoles()) {
                ArrayList<Role> listeElement = new ArrayList<>();
                if (member.getRole() != null){
                    for (int i = 0; i < roleFacade.getListRoles().size(); i++){
                        if (member.getRole().getId() == roleFacade.getListRoles().get(i).getId()){
                            listeElement.add( (Role) roleFacade.getListRoles().get(i));
                        }
                    }
                }

                ObservableList<Role> listView = FXCollections.observableArrayList(listeElement);
                listViewTemp = FXCollections.observableArrayList(listeElement);
                rolesList.setItems(listView);
                rolesList.setCellFactory(param -> new ReadAMemberController.Cell());
            }
        }


    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de valider la suppression courrante d'un membre du projet.
     * En cas d'erreur, le signifie avec un UIError.
     */
    public void validation(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        AbstractProject project = this.member.getProject();
        if(!(memberFacade.deleteMember(this.member))){
            UIError error = new UIError( new AllMembersUI(project));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            AllMembersUI allm = new AllMembersUI(this.member.getProject());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(allm.loadScene().getRoot());
            member = null;
        }
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet d'annuler la suppression courrante d'un membre du projet.
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
     * @Description Permet de valider la suppression courrante du role du membre.
     * En cas d'erreur, le signifie avec un UIError.
     */
    public void validationDelRole(ActionEvent actionEvent) {
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        this.member.setRole(null);
        if(!(memberFacade.modifyMember(this.member))){
            UIError error = new UIError((UIGlobal) new ReadAMemberController(this.member));
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(2);
        }else {
            ReadAMemberUI allm = new ReadAMemberUI(this.member);
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);
            box.getChildren().add(allm.loadScene().getRoot());
        }
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet d'annuler la suppression courrante du role du membre.
     * En cas d'erreur, le signifie avec un UIError.
     */
    public void refuseDelRole(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#confirm2");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toShow.setVisible(true);
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Back".
     * Elle permet de rediriger l'utilisateur sur la page de la "AllMembersUI" qui
     * était la page précédente avant qu'il arrive sur celle-ci ("ReadAMemberUI").
     */
    public void backToPage(ActionEvent actionEvent) {
        AllMembersUI allm = new AllMembersUI(this.member.getProject());
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(allm.loadScene().getRoot());
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Sous fonction qui permet de gérer les UI correspondantes qui
     * doivent s'ouvrir ou se fermer pour la confirmation de la suppression du membre
     */
    public void deleteMember(ActionEvent actionEvent) {
        AnchorPane toHide = (AnchorPane) App.getInstanceScene().lookup("#manager");
        toHide.setVisible(false);
        AnchorPane toShow = (AnchorPane) App.getInstanceScene().lookup("#confirm");
        toShow.setVisible(true);

    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de rediriger l'utilisateur sur la page d'ajout/de création de role (AllRolesUI)
     */
    public void addRole(ActionEvent actionEvent) {
        AllRolesUI allm = new AllRolesUI(this.member);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(allm.loadScene().getRoot());
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Classe propre à ReadAMemberController qui servira à donner
     * la liste des roles du membre. Dans cette version de l'application
     * le membre n'a qu'un seul rôle, mais si on en trouve l'utilité,
     * tout a été arrangé pour pouvoir gérer le fait qu'il ait plusieurs
     * roles dans une future version. Chaque role
     * correspondra à une cellule dans laquelle on pourra trouver
     * les boutons nécessaires à la gestion de ces role (notamment
     * la suppression du role pour le membre)
     */
    static class Cell extends ListCell<Role> {
        Role role;
        HBox hbox = new HBox();
        Image image = new Image("megaphone.png");
        ImageView img = new ImageView(image);
        Button btnD = new Button("Delete");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.setSpacing(10);
            img.setFitHeight(20);
            img.setFitWidth(20);

            hbox.getChildren().addAll(img, label, pane, btnD);

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
         * @author Lauren Unquera - Polytech Montpellier IG4
         * @Description Permet de donner des information sur les roles
         * des cellules, ici le nom.
         */
        public void updateItem(Role name, boolean empty){
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);
            if(name != null && !empty){
                role = name;
                label.setText(name.getId()+" "+name.getName());
                setGraphic(hbox);
            }

        }

    }
}
