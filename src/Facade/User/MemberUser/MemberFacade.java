package Facade.User.MemberUser;

import BuisnessLogic.Project.AbstractProject;
import BuisnessLogic.User.Member;
import DAO.MySQLDAOFactory;
import DAO.User.Member.MemberDAO;

import java.util.ArrayList;
import java.util.List;

public class MemberFacade implements IMemberFacade{

    private List<Member> members;

    private MemberDAO daoFactory;

    public static MemberFacade instance;

    private MemberFacade(){
        daoFactory = MySQLDAOFactory.getMemberDAO();
        this.members = new ArrayList<>();
    }

    public static MemberFacade getInstance(){
        if(instance == null){
            instance = new MemberFacade();
        }
        return instance;
    }

    public List<Member> getListMembers(){
        return this.members;
    }

    public MemberDAO getDao(){
        return this.daoFactory;
    }


    public boolean addMember(Member member) {
        if(instance.getDao().save(member)){
            //on ajouter la nouvelle idee à la liste
            instance.members.add(member);
            return true;
        }else {
            return false;
        }
    }

    public boolean modifyMember(Member member) {
        if(daoFactory.update(member)){
            this.members.set(member.getId(), member);
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteMember(Member member){
        if(daoFactory.delete(member.getId())){
            return true;
        }
        else {
            return false;
        }
    }

    /*public List<AbstractMember> getMemberByName(String name) {
        return null;
    }*/

    public boolean getAllMembers() {
        this.members = daoFactory.getAllMembers();
        return true;
    }

    public boolean getAllMembersProject(AbstractProject project) {
        this.members = daoFactory.getAllMembersProject(project);
        return true;
    }

    public Member getMemberById(int id) {
        Member member;
        member = daoFactory.getMemberById(id);
        return member;
    }
}
