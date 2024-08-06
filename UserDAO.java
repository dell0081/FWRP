package dataaccesslayer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.SubscriptionPreference;
import constants.UserType;
import transferobjects.User;

/**
 * The {@code UserDAO} class implements the Data Access Object (DAO) design pattern 
 * to provide methods for CRUD operations related to users in the database.
 *
 * @version 1.0
 * @since OpenJDK version 17.0.7
 * @author Luke Mackay
 * @see DAO
 * @see User
 * @see UserType
 */
public class UserDAO implements DAO 
{
	
	/** 
	 * The prepared statement for database queries. 
	 */
	PreparedStatement query = null;

    /**
     * Creates a new user in the database.
     *
     * @param user the user to create
     */
	public void createUser(User user) {
		try {
			query = DBC.useConnection().prepareStatement(
					"INSERT INTO user(userName, password, email, phoneNumber, address, subscriptionPreference, displayName, userType) VALUES(?,?,?,?,?,?,?,?)");
			query.setString(1, user.getUsername());
			query.setString(2, user.getPassword());
			query.setString(3, user.getEmail());
			query.setString(4, user.getPhonenumber());
			query.setString(5, user.getAddress());
			query.setInt(6, user.getSubscriptionPreference().ordinal());
			query.setString(7, user.getDisplayName());
			query.setInt(8, user.getUsertype().ordinal());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * Updates an existing user in the database.
     *
     * @param user the user to update
     */
	public void updateUser(User user) {
		try {
			query = DBC.useConnection().prepareStatement(
					"UPDATE user SET userName = ?, password = ?, email = ?, phoneNumber = ?, address = ?, subscriptionPreference = ?, displayName = ?, userType = ? WHERE userID = ?");
			query.setString(1, user.getUsername());
			query.setString(2, user.getPassword());
			query.setString(3, user.getEmail());
			query.setString(4, user.getPhonenumber());
			query.setString(5, user.getAddress());
			query.setInt(6, user.getSubscriptionPreference().ordinal());
			query.setString(7, user.getDisplayName());
			query.setInt(8, user.getUsertype().ordinal());
			query.setInt(9, user.getUserID());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

    /**
     * Retrieves a user from the database based on the username.
     *
     * @param username the username of the user to retrieve
     * @return the retrieved user
     */
	public User retrieveUser(String username) {
		User user = new User();
		ResultSet results = null;
		try {
			query = DBC.useConnection().prepareStatement("SELECT * FROM user WHERE userName = ?");
			query.setString(1, username);
			results = query.executeQuery();
			while (results.next()) {
				user.setUserID(results.getInt("userId"));
				user.setUsername(results.getString("userName"));
				user.setPassword(results.getString("password"));
				user.setEmail(results.getString("email"));
				user.setPhonenumber(results.getString("phoneNumber"));
				user.setAddress(results.getString("address"));
				user.setSubscriptionPreference(SubscriptionPreference.getType(results.getInt("subscriptionPreference")));
				user.setDisplayName(results.getString("displayName"));
				user.setUsertype(UserType.getType(results.getInt("userType")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

    /**
     * Deletes a user from the database.
     *
     * @param user the user to delete
     */
	public void deleteUser(User user) {
		try {
			query = DBC.useConnection().prepareStatement("DELETE FROM user WHERE userID = ?");
			query.setInt(1, user.getUserID());
			query.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
