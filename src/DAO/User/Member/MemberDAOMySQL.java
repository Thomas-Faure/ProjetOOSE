package DAO.User.Member;

import BuisnessLogic.User.Member;
import DAO.MySQLConnector;
import Facade.Project.IProjectFacade;
import Facade.Project.ProjectFacade;
import Facade.Role.IRoleFacade;
import Facade.Role.RoleFacade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOMySQL implements MemberDAO {

    private static final String INSERT = "INSERT INTO member (idProject, idUser) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE member SET idProject=?, idRole=?, idUser = ? WHERE idUser=?";
    private static final String DELETE = "DELETE FROM member WHERE idUser=?";
    private static final String ALL = "SELECT user.* from user,member WHERE user.idUser = member.idUser";
    private static final String MEMBER = "SELECT member.* from member WHERE idUser = ?";
    private static final String ALLPROJECT = "SELECT user.* from user, member WHERE user.idUser = member.idUser AND member.idProject = ?";
    private static final String MEMBERBYID = "SELECT * from member where idUser=?";

    public MemberDAOMySQL() {

    }

    public Member createMemberById(int id) {
        Member member=null;
        try {
            String query = "SELECT user.* FROM member, user WHERE member.idUser = user.idUser AND member.idUser="+id;
            ResultSet result = MySQLConnector.getSQLConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(query);
            if(result.first()) {
                System.out.println("correct");
                //� changer
                member= new Member(
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public boolean save(Member member) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            //ps.setInt(1, member.getRole().getId());
            ps.setInt(1, member.getProject().getId());
            ps.setInt(2, member.getId());
            ps.executeUpdate();
            ps.close();


            return true;
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Member member) {

        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setInt(1, member.getProject().getId());
            if (member.getRole() == null){
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            else {
                ps.setInt(2, member.getRole().getId());
            }
            ps.setInt(3, member.getId());
            ps.setInt(4, member.getId());
            /*
            if(member.getRole() == null){
                ps.setInt(3, null);
            }
            else {

             */

            //}
            ps.executeUpdate();
            ps.close();


            return true;
/*
            int i = ps.executeUpdate();
            ps.close();
            return i > 0;

 */

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(int id) {

        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            ps.close();
            return i > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Member> getAllMembers() {

        List<Member> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Member member = new Member(
                        rs.getInt("idUser"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("city"),
                        rs.getString("phoneNumber"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getBoolean("isAdmin")
                );
                ps = MySQLConnector.getSQLConnection().prepareStatement(MEMBER);
                ps.setInt(1,member.getId());
                rs = ps.executeQuery();

                IRoleFacade roleFacade = RoleFacade.getInstance();
                IProjectFacade projetFacade = ProjectFacade.getInstance();

                while(rs.next()){
                    member.setProject(projetFacade.getProjectById(rs.getInt("idProject")));
                    member.setRole(roleFacade.getRoleById(rs.getInt("idRole")));
                }
                list.add(member);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }
/*
    public List<Member> getAllMembersProject(AbstractProject project) {

        List<Member> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALLPROJECT);
            ps.setInt(1, project.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Member member = new Member(
                        rs.getInt("idUser"),
                        rs.getString("username"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("password")
                );
                list.add(member);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }

 */



    public Member getMemberById(int id) {
        Member member = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(MEMBERBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                 member = new Member(
                        rs.getInt("idUser"),
                        rs.getString("username"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("password")
                );
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return member;
    }

    //Peut être non nécessaire
    public List<Member> getMemberByName(String name) {
        return null;
    }
}
