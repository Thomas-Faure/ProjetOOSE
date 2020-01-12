package Controller.Role;

import BuisnessLogic.Role.AbstractRole;
import BuisnessLogic.Role.Role;
import BuisnessLogic.User.Member;
import Facade.Role.RoleFacade;
import Main.App;
import UI.Role.AllRolesUI;
import UI.UIError;
import UI.UIGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au contrôleur qui gère la vue "AddRoleUI".
 * Il va servir aux utilisateurs pour créer/ajouter un role.
 */
public class AddRoleController {

    private Member member;

    @FXML
    private TextField roleName;


    public AddRoleController(Member newMember){
        this.member = newMember;
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Create Role".
     * Elle permet donc de créer un role.
     * Une fois executée, elle redirigera l'utilisateur sur la page de la "AllRolesUI".
     */
    @FXML
    void addNewRole(ActionEvent actionEvent){
        AbstractRole role = new Role(0, roleName.getText());
        if(RoleFacade.getInstance().addRole(role)){
            AllRolesUI roleui = new AllRolesUI(this.member);
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            if(box.getChildren().size() >1 ) {
                box.getChildren().remove(1);
            }
            box.getChildren().add(roleui.loadScene().getRoot());
        }else{
            System.out.println("Bug ajout idée ");
            UIError error = new UIError((UIGlobal) new AllRolesUI(this.member));
            HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
            box.getChildren().add(error.loadScene().getRoot());
            if(box.getChildren().size() >1 )
                box.getChildren().remove(1);


        }
    }

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Cette fonction est appelée lorsque l'utilisateur appuie
     * sur le bouton "Back".
     * Elle permet de rediriger l'utilisateur sur la page de la "AllRolesUI" qui
     * était la page précédente avant qu'il arrive sur celle-ci ("AddRoleUI").
     */
    @FXML
    public void backToRole(ActionEvent actionEvent) {
        AllRolesUI roleui = new AllRolesUI(this.member);
        HBox box = (HBox) App.getInstanceScene().lookup("#HBOX");
        if(box.getChildren().size() >1 )
            box.getChildren().remove(1);
        box.getChildren().add(roleui.loadScene().getRoot());
    }
}