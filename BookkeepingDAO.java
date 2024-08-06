package dataaccesslayer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import constants.UserType;
import transferobjects.BookkeepingEntry;
import transferobjects.FoodItem;
import transferobjects.User;
/**
 * The {@code BookkeepingDAO} class implements the Data Access Object (DAO) design pattern 
 * to provide methods for CRUD operations related to bookkeeping entries in the database.
 *
 * @version 1.0
 * @since OpenJDK version 17.0.7
 * @author Luke Mackay
 * @see DAO
 * @see BookkeepingEntry
 * @see UserType
 * @see DBC
 */
public class BookkeepingDAO implements DAO {
	/** 
	 * The prepared statement for database queries. 
	 */
	PreparedStatement query = null;
	/** 
	 * The transaction type for the entry. 
	 */
	int transactionType;
	/** 
	 * The current date for the entry. 
	 */
	Date currentDate = new Date(System.currentTimeMillis());
	 /**
     	* Creates a new bookkeeping entry in the database.
     	*
     	* @param item the food item to create an entry for
     	* @param user the user associated with the entry
     	*/
	public void createEntry(FoodItem item, User user) {
		try {
			query = DBC.useConnection().prepareStatement("INSERT INTO Bookkeeping(TransactionType,TransactionDate,foodItemId,itemName,quantity,price,surplus,expiryDate,userId,address,ownerId) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			if(user.getUsertype().equals(UserType.getType(2))) {transactionType = 1;}
			else if (user.getUsertype().equals(UserType.getType(3))) {transactionType = 2;}
			else {transactionType = 0;}
			query.setInt(1, transactionType);
			query.setDate(2, currentDate);
			query.setInt(3,item.getItemID());
			query.setString(4,item.getName());
			query.setInt(5,item.getQuantity());
			query.setInt(6,item.getPrice());
			query.setInt(7,item.getSurplus());
			query.setDate(8,item.getExpDate());
			query.setInt(9,user.getUserID());
			query.setString(10,item.getAddress());
			query.setInt(11, item.getOwnerID());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
     	* Updates an existing bookkeeping entry in the database.
     	*
     	* @param entry the bookkeeping entry to update
     	*/
	public void updateEntry(BookkeepingEntry entry) {
	try {
		query = DBC.useConnection().prepareStatement("UPDATE Bookkeeping SET foodItemId = ?,itemName = ?,quantity = ?,price = ?,surplus = ?,expiryDate = ?,userId = ?,address = ?,ownerId = ? WHERE logID = ?");
		query.setInt(1,entry.getFoodItemId());
		query.setString(2,entry.getItemName());
		query.setInt(3,entry.getQuantity());
		query.setInt(4,entry.getPrice());
		query.setInt(5,entry.getSurplus());
		query.setDate(6,entry.getExpiryDate());
		query.setInt(7,entry.getUserId());
		query.setString(8,entry.getAddress());
		query.setInt(9, entry.getOwnerID());
		query.setInt(10, entry.getLogId());
		query.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	}
 	/**
     	* Retrieves a bookkeeping entry from the database based on the specified ID.
     	*
     	* @param id the ID of the bookkeeping entry to retrieve
     	* @return the retrieved bookkeeping entry
     	*/
	public BookkeepingEntry retrieveEntry(int id) {
		BookkeepingEntry entry = new BookkeepingEntry();
		ResultSet results = null;
		try {
			query = DBC.useConnection().prepareStatement("SELECT FROM Bookkeeping WHERE logId = ?");
			query.setInt(1, id);
			results = query.executeQuery();
			while (results.next()) {
				entry.setLogId(results.getInt("logId"));
				entry.setTransactionType(results.getInt("TransactionType"));
				entry.setTransactionDate(results.getDate("TransactionDate"));
				entry.setFoodItemId(results.getInt("foodItemId"));
				entry.setItemName(results.getString("itemName"));
				entry.setQuantity(results.getInt("quantity"));
				entry.setPrice(results.getInt("price"));
				entry.setSurplus(results.getInt("surplus"));
				entry.setExpiryDate(results.getDate("expiryDate"));
				entry.setUserId(results.getInt("userId"));
				entry.setAddress(results.getString("address"));
				entry.setOwnerID(results.getInt("ownerId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entry;

	}
	/**
     	* Retrieves all bookkeeping entries from the database.
     	*
     	* @return an ArrayList of all bookkeeping entries
     	*/
	public ArrayList<BookkeepingEntry> retrieveAllEntries() {
		ArrayList<BookkeepingEntry> entries = new ArrayList<>();
		ResultSet results = null;
		try {
			query = DBC.useConnection().prepareStatement("SELECT * Bookkeeping");
			results = query.executeQuery();
			while (results.next()) {
				BookkeepingEntry entry = new BookkeepingEntry();
				entry.setLogId(results.getInt("logId"));
				entry.setTransactionType(results.getInt("TransactionType"));
				entry.setTransactionDate(results.getDate("TransactionDate"));
				entry.setFoodItemId(results.getInt("foodItemId"));
				entry.setItemName(results.getString("itemName"));
				entry.setQuantity(results.getInt("quantity"));
				entry.setPrice(results.getInt("price"));
				entry.setSurplus(results.getInt("surplus"));
				entry.setExpiryDate(results.getDate("expiryDate"));
				entry.setUserId(results.getInt("userId"));
				entry.setAddress(results.getString("address"));
				entry.setOwnerID(results.getInt("ownerId"));
				entries.add(entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entries;
	}
	/**
     	* Deletes a bookkeeping entry from the database.
     	*/
	public void deleteEntry(BookkeepingEntry entry) {
		try {
			query = DBC.useConnection().prepareStatement("DELETE FROM Bookkeeping WHERE logId = ?");
			query.setInt(1, entry.getLogId());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
