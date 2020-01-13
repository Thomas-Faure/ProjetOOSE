package DAO.Role;

import BusinessLogic.Role.AbstractRole;
import BusinessLogic.Role.Role;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Interface correspond au DAO qui gère les roles.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 */
public interface RoleDAO {

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Fonction non utile pour cette version de l'application
     * Cette fonction premet de créer et retourner
     * un role par rapport à une role de la base de donnée
     * dont l'id est passé en paramètre.
     * @Param id : L'id du role qu'on veut récuperer de la base de donnée
     */
    public Role createRoleById(int id);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet d'insérer dans la base de donnée un role passée
     * en paramètre
     * @Param role : AbstractRole - role qu'on veut insérer
     */
    boolean save(AbstractRole role);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de modifier dans la base de donnée un role passé
     * en paramètre
     * @Param role : Role qu'on veut modifier
     */
    boolean update(AbstractRole role);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de supprimer dans la base de donnée un role dont
     * l'id est passé en paramètree
     * @Param id : ID du role qu'on veut supprimer
     */
    boolean delete(int id);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Retourne la liste de tous les roles présents dans
     * la base de données
     */
    List<AbstractRole> getAllRoles();

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Retourne le role dont l'id est passé en paramètre
     */
    AbstractRole getRoleById(int id);
}
