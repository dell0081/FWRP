package dataaccesslayer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import transferobjects.FoodItem;
import transferobjects.User;

/**
 * The {@code SubscriptionDAO} class implements the Data Access Object (DAO) design pattern 
 * to provide methods for CRUD operations related to subscriptions in the database.
 *
 * @version 1.0
 * @since OpenJDK version 17.0.7
 * @author Luke Mackay
 * @see DAO
 * @see FoodItem
 * @see User
 */
public class SubscriptionDAO implements DAO
{
	/** 
	 * The prepared statement for database queries. 
	 */
	PreparedStatement query = null;
    /**
     * Creates a new subscription for a user and a food item.
     *
     * @param item the food item to subscribe to
     * @param user the user subscribing to the food item
     */
	public void createSubscription(FoodItem item, User user) {
		try {
			query = DBC.useConnection().prepareStatement("INSERT INTO subscriptions(userId,foodItemId,isSubscribed) VALUES(?,?,?)");
			query.setInt(1, user.getUserID());
			query.setInt(2, item.getItemID());
			query.setBoolean(3,true);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Updates the subscription status for a user and a food item.
     *
     * @param item the food item to update the subscription for
     * @param user the user whose subscription to update
     */
	public void updateSubscription(FoodItem item, User user) {
		boolean subOrNot = true;
		ResultSet results;
		try {
			query = DBC.useConnection().prepareStatement("Select isSubscribed From subscriptions WHERE userId = ?,foodItemId = ?");
			results = query.executeQuery();
			while (results.next()) {
				if (results.getBoolean("subscribed")) {
					subOrNot = false;
				}
				else {
					subOrNot = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			query = DBC.useConnection().prepareStatement("Update subscriptions SET isSubscribed = ? WHERE userId = ?,foodItemId = ?");
			query.setBoolean(3,subOrNot);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Retrieves the subscription status for a user and a food item.
     *
     * @param item the food item to check the subscription for
     * @param user the user to check the subscription for
     * @return true if the user is subscribed to the food item, false otherwise
     */
	public boolean retrieveSubscription(FoodItem item, User user){
		boolean subOrNot = true;
		ResultSet results;
		try {
			query = DBC.useConnection().prepareStatement("Select isSubscribed From subscriptions WHERE userId = ?,foodItemId = ?");
			results = query.executeQuery();
			while (results.next()) {
				if (results.getBoolean("subscribed")) {
					subOrNot = true;
				}
				else {
					subOrNot = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subOrNot;
	}
	
    /**
     * Deletes a subscription for a user and a food item.
     *
     * @param item the food item to delete the subscription for
     * @param user the user to delete the subscription for
     */
	public void deleteSubscription(FoodItem item, User user) {
		try {
			query = DBC.useConnection().prepareStatement("DELETE FROM subscriptions WHERE userId = ?,foodItemId = ?");
			query.setInt(1, user.getUserID());
			query.setInt(2, item.getItemID());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
