package DAO.User.GlobalUser;

import BusinessLogic.User.AbstractUser;
import BusinessLogic.User.User;

import java.util.List;

/**
 * Cette Interface correspond au DAO qui gère les globalUser.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public interface GlobalUserDAO {
    /**
     * Fonction utilisée pour le login (réalisé par Thomas)
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public User createUser(String identifiant, String username);

    /**
     * Permet d'insérer dans la base de donnée un utilisateur (globalUser)
     * passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param user : AbstractUser - utilisateur qu'on veut insérer
     */
    public boolean save(AbstractUser user);

    /**
     * Permet de modifier dans la base de donnée un utilisateur (globalUser)
     * passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param user : Utilisateur qu'on veut modifier
     */
    public boolean update(AbstractUser user);

    /**
     * Permet de supprimer dans la base de donnée un utilisateur dont
     * l'id est passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param id : ID de l'utilisateur qu'on veut supprimer
     */
    public boolean delete(int id);

    /**
     * Retourne la liste de tous les utilisateurs (GlobalUser)
     * présents dans la base de données
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    List<AbstractUser> getAllUsers();

    /**
     * Retourne l'utilisateur dont l'id est passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    AbstractUser getUserById(int id);
}
