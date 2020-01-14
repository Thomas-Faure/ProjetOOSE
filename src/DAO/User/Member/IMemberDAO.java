package DAO.User.Member;

import BusinessLogic.User.Member;

import java.util.List;

/**
 * Cette Interface correspond au DAO qui gère les membres.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 * @author Lauren Unquera - Polytech Montpellier IG4
 */
public interface IMemberDAO {

    /**
     * Fonction non utile pour cette version de l'application
     * Cette fonction premet de créer et retourner
     * un membre par rapport à un utilisateur de la base de donnée
     * dont l'id est passé en paramètre.
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param id : L'id de l'utilisateur qu'on veut récuperer de la base de donnée
     */
    public Member createMemberById(int id);

    /**
     * Permet d'insérer dans la base de donnée un utilisateur (globalUser)
     * passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param member : AbstractUser - utilisateur qu'on veut insérer
     */
    public boolean save(Member member);

    /**
     * Permet de modifier dans la base de donnée un membre
     * passé en paramètre
     * member : Le membre qu'on veut modifier
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    public boolean update(Member member);

    /**
     * Permet de supprimer dans la base de donnée un membre dont
     * l'id est passé en paramètre
     * @author Lauren Unquera - Polytech Montpellier IG4
     * @param id : ID du membre qu'on veut supprimer
     */
    public boolean delete(int id);

    /**
     * Retourne la liste de tous les membres
     * présents dans la base de données
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    List<Member> getAllMembers();

    /**
     * Retourne le membre dont l'id est passé en paramètre.
     * Fonction non utile dans cette version de l'application.
     * @author Lauren Unquera - Polytech Montpellier IG4
     */
    Member getMemberById(int id);
}