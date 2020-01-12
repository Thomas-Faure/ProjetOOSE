package DAO.User.GlobalUser;

import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.User;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Interface correspond au DAO qui gère les globalUser.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 */
public interface GlobalUserDAO {
    /**
     * @author Thomas Faure / Lauren Unquera - Polytech Montpellier IG4
     * @Description Fonction utilisée pour le login (réalisé par Thomas)
     */
    public User createUser(String identifiant, String username);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet d'insérer dans la base de donnée un utilisateur (globalUser)
     * passé en paramètre
     * @Param user : AbstractUser - utilisateur qu'on veut insérer
     */
    public boolean save(AbstractUser user);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de modifier dans la base de donnée un utilisateur (globalUser)
     * passé en paramètre
     * @Param user : Utilisateur qu'on veut modifier
     */
    public boolean update(AbstractUser user);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de supprimer dans la base de donnée un utilisateur dont
     * l'id est passé en paramètre
     * @Param id : ID de l'utilisateur qu'on veut supprimer
     */
    public boolean delete(int id);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Retourne la liste de tous les utilisateurs (GlobalUser)
     * présents dans la base de données
     */
    List<AbstractUser> getAllUsers();

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Retourne l'utilisateur dont l'id est passé en paramètre
     */
    AbstractUser getUserById(int id);
}
