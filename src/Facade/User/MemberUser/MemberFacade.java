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
        daoFactory = MySQLDAOFactory.getInstance().getMemberDAO();
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

    public List<Member> getListMembers(AbstractProject project){
        List<Member> newlist = new ArrayList<>();
        for (int i= 0; i < members.size(); i ++){
            if (this.members.get(i).getProject() == project){
                newlist.add(members.get(i));
            }
        }
        return newlist;

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
            int i = 0;
            while(this.members.get(i).getId() != member.getId()){
                i++;
            }
            this.members.set(i, member);
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

    public Member getMemberById(int id) {
        Member member;
        member = daoFactory.getMemberById(id);
        return member;
    }
}
