package Facade.User.MemberUser;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.User.Member;

import java.util.List;

public interface IMemberFacade {
    boolean addMember(Member member);

    boolean modifyMember(Member member);

    boolean deleteMember(Member member);

    boolean getAllMembers();

    Member getMemberById(int id);

    List<Member> getListMembers();

    public boolean getAllMembersProject(AbstractProject project);
}
