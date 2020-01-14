package DAO.Role;

import BusinessLogic.Role.AbstractRole;
import BusinessLogic.Role.Role;

import java.util.List;

/**
 * Cette Interface correspond au DAO qui gère les roles.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public interface IRoleDAO {

    /**
     * Fonction non utile pour cette version de l'application
     * Cette fonction premet de créer et retourner
     * un role par rapport à une role de la base de donnée
     * dont l'id est passé en paramètre.
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param id : L'id du role qu'on veut récuperer de la base de donnée
     */
    public Role createRoleById(int id);

    /**
     * Permet d'insérer dans la base de donnée un role passée
     * en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param role : AbstractRole - role qu'on veut insérer
     */
    boolean save(AbstractRole role);

    /**
     * Permet de modifier dans la base de donnée un role passé
     * en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param role : Role qu'on veut modifier
     */
    boolean update(AbstractRole role);

    /**
     * Permet de supprimer dans la base de donnée un role dont
     * l'id est passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param id : ID du role qu'on veut supprimer
     */
    boolean delete(int id);

    /**
     * Retourne la liste de tous les roles présents dans
     * la base de données
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    List<AbstractRole> getAllRoles();

    /**
     * Retourne le role dont l'id est passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    AbstractRole getRoleById(int id);
}

