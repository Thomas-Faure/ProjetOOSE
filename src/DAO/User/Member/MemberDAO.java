package DAO.User.Member;

import BuisnessLogic.User.Member;

import java.util.List;

public interface MemberDAO {

    public Member createMemberById(int id);
    public boolean save(Member member);
    public boolean update(Member member);
    public boolean delete(int id);
    List<Member> getAllMembers();
    Member getMemberById(int id);
    List<Member> getMemberByName(String name);
}
