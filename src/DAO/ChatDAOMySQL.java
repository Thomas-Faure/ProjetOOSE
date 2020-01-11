package DAO;

import BuisnessLogic.Chat.AbstractChat;
import BuisnessLogic.Chat.Chat;
import BuisnessLogic.Ressource.AbstractResource;
import BuisnessLogic.Ressource.Resource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatDAOMySQL implements ChatDAO {

	private static final String INSERT = "INSERT INTO chat (chatName, idProject) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE chat SET chatName=? WHERE idChat=?";
	private static final String DELETE = "DELETE FROM chat WHERE idChat=?";
	private static final String CHATBYPROJECT = "SELECT * FROM chat where idProject=?";


	@Override
	public boolean save(AbstractChat chat) {
		boolean success = false;
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
			ps.setString(1,chat.getChatName());
			ps.setInt(2,chat.getIdProject());
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
	public boolean update(AbstractChat chat) {
		boolean success = false;
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
			ps.setString(1,chat.getChatName());
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
	public boolean delete(int idChat) {
		boolean success = false;
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
			ps.setInt(1,idChat);
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
	public AbstractChat getChatByProjectId(int idProject) {
		AbstractChat newChat = null;
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(CHATBYPROJECT);
			ps.setInt(1, idProject);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				newChat = new Chat(
						rs.getString("chatName"),
						rs.getInt("idProject"));
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return newChat;
	}
}
