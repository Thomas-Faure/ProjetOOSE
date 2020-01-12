package Facade.User.MemberUser;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.User.Member;

import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Interface correspond à la façade qui gère les membres.
 * Il aura une instance statique à partir de laquelle on pourra récupérer
 * les membres présents dans l'application. Est en lien avec le DAO MemberDAO
 * correspondant pour récupérer les données depuis la base.
 */
public interface IMemberFacade {
    boolean addMember(Member member);

    boolean modifyMember(Member member);

    boolean deleteMember(Member member);

    boolean getAllMembers();

    Member getMemberById(int id);

    public List<Member> getListMembers(AbstractProject project);

    List<Member> getListMembers();
}
