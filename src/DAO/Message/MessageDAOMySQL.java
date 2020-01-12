package DAO.Message;

import BuisnessLogic.Message.AbstractMessage;
import BuisnessLogic.Message.Message;
import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Ressource.Resource;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.GlobalUser;
import DAO.MySQLConnector;
import DAO.User.GlobalUser.GlobalUserDAOMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guillaume Tessier
 */
public class MessageDAOMySQL implements MessageDAO {

    private static final String INSERT = "INSERT INTO message (contenu, idChat, createur) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM message WHERE idMessage=?";
    private static final String MESSAGEBYID = "SELECT * FROM message where idMessage=?";
    private static final String MESSAGEBYCHAT = "SELECT idMessage FROM message WHERE idChat=?";


    public MessageDAOMySQL(){
    }

    @Override
    public boolean save(AbstractMessage message) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, message.getContenu());
            ps.setInt(2, message.getIdChat());
            ps.setInt(3, message.getCreateur().getId());
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean delete(int idMessage) {
        boolean success = false;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
            ps.setInt(1, idMessage);
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public AbstractMessage getMessageById(int idMessage) {
        AbstractMessage newMessage = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(MESSAGEBYID);
            ps.setInt(1, idMessage);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                GlobalUserDAOMySQL daoUser = new GlobalUserDAOMySQL();
                int idUser = rs.getInt("createur");
                AbstractUser createur = daoUser.getUserById(idUser);

                newMessage = new Message(
                        rs.getInt("idChat"),
                        rs.getString("contenu"),
                        rs.getInt("idChat"),
                        createur);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newMessage;
    }

    @Override
    public List<AbstractMessage> getAllMessageByChat(int idChat) {
        List<AbstractMessage> messageList = new ArrayList<AbstractMessage>();
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(MESSAGEBYCHAT);
            ps.setInt(1, idChat);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int newMessageID = rs.getInt("idMessage");
                AbstractMessage newMessage = getMessageById(newMessageID);
                messageList.add(newMessage);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messageList;
    }
}
