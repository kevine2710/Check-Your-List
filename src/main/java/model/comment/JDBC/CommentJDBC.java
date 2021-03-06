package model.comment.JDBC;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionDB;
import model.activity.Activity;
import model.comment.Comment;
import model.person.User;

/**
 * persistance of comment
 *
 */
public class CommentJDBC extends Comment {
	
	/**
	 * constructor of commentJDBC
	 */
	public CommentJDBC(){
		super();
	}

	/**
	 * constructor of commentJDBC
	 * @param content
	 * @param postingDate
	 */
	public CommentJDBC(String content, String postingDate) {
		super(content, postingDate);
	}
	
	/**
	 * constructor of commentJDBC
	 * @param content
	 * @param postingDate
	 * @param activity
	 */
	public CommentJDBC(String content, String postingDate, Activity activity) {
		super(content, postingDate, activity);
	}
	
	/**
	 * constructor of commentJDBC
	 * @param content
	 * @param postingDate
	 * @param user
	 */
	public CommentJDBC(String content, String postingDate, User user) {
		super(content, postingDate, user);
	}
	
	/**
	 * constructor of commentJDBC
	 * @param content
	 * @param postingDate
	 * @param activity
	 * @param user
	 */
	public CommentJDBC(String content, String postingDate, Activity activity, User user) {
		super(content, postingDate, activity, user);
	}
		
	@Override
	public boolean addCommentActivity() {
		String sql = ("INSERT INTO commentActivity ("
				+ "content, postingdate, pseudo_sender, titre_activity) VALUES ( '" +
				this.content + "','" + this.postingDate + "','" + this.user.getPseudo() + "','" + this.activity.getTitle() + "')");
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			return stm.execute(sql);
		} catch (SQLException e) {}
		return false;
	}
	/*
	@Override
	public List<Comment> getAllCommentsByActivity() {
		String sql = ("SELECT * FROM commentActivity WHERE titre_activity = '" + this.activity.getTitle() + "'");
		List<Comment> list = new ArrayList<Comment>();
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while ( rs.next() ) {
				Comment comment = new CommentJDBC();
				ResultSetMetaData resultMeta = rs.getMetaData();
				if (resultMeta.getTableName(1).equals("commentactivity")) {
					comment.setContent((String) rs.getObject("content"));
					comment.setPostingDate((String) rs.getObject("postingdate"));
					list.add(comment);
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	*/

	@Override
	public boolean addCommentProfil() {
		String sql = ("INSERT INTO commentProfil ("
				+ "content, postingdate, pseudosender, pseudoreceiver) VALUES ( '" +
				this.content + "','" + this.postingDate + "','" + this.user.getPseudo() + "','" + this.activity.getTitle() + "')");
		try {
			Statement stm = ConnectionDB.creetConnectionDB().getConn().createStatement();
			return stm.execute(sql);
		} catch (SQLException e) {}
		return false;
	}
	
	
	
}
