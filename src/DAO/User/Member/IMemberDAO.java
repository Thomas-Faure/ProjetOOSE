package DAO.User.Member;

import BusinessLogic.User.Member;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Interface correspond au DAO qui gère les membres.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 */
public interface IMemberDAO {

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Fonction non utile pour cette version de l'application
     * Cette fonction premet de créer et retourner
     * un membre par rapport à un utilisateur de la base de donnée
     * dont l'id est passé en paramètre.
     * @Param id : L'id de l'utilisateur qu'on veut récuperer de la base de donnée
     */
    public Member createMemberById(int id);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet d'insérer dans la base de donnée un utilisateur (globalUser)
     * passé en paramètre
     * @Param user : AbstractUser - utilisateur qu'on veut insérer
     */
    public boolean save(Member member);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de modifier dans la base de donnée un membre
     * passé en paramètre
     * @Param user : Le membre qu'on veut modifier
     */
    public boolean update(Member member);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Permet de supprimer dans la base de donnée un membre dont
     * l'id est passé en paramètre
     * @Param id : ID du membre qu'on veut supprimer
     */
    public boolean delete(int id);

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Retourne la liste de tous les membres
     * présents dans la base de données
     */
    List<Member> getAllMembers();

    /**
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @Description Retourne le membre dont l'id est passé en paramètre.
     * Fonction non utile dans cette version de l'application.
     */
    Member getMemberById(int id);
}
