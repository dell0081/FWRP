package dataaccesslayer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import transferobjects.FoodItem;


/**
 * The {@code InventoryDAO} class implements the Data Access Object (DAO) design pattern 
 * to provide methods for CRUD operations related to inventory items in the database.
 *
 * @version 1.0
 * @since OpenJDK version 17.0.7
 * @author Luke Mackay
 * @see DAO
 * @see FoodItem
 */
public class InventoryDAO implements DAO {
	/** 
	 * The prepared statement for database queries. 
	 */
	PreparedStatement query = null;
	
    /**
     * Creates a new item in the inventory.
     *
     * @param item the food item to create
     */

	public void createItem(FoodItem item) {
		try {
			query = DBC.useConnection().prepareStatement("INSERT INTO RetailInventory(itemName,quantity,price,surplus,expiryDate,userId,address) VALUES(?,?,?,?,?,?,?)");
			query.setString(1, item.getName());
			query.setInt(2, item.getQuantity());
			query.setInt(3,item.getPrice());
			query.setInt(4,item.getSurplus());
			query.setDate(5,item.getExpDate());
			query.setInt(6,item.getOwnerID());
			query.setString(7,item.getAddress());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

    /**
     * Updates an existing item in the inventory.
     *
     * @param item the food item to update
     */
	public void updateItem(FoodItem item) {
		try {
			query = DBC.useConnection().prepareStatement("UPDATE RetailInventory SET itemName = ?,quantity = ?,price = ?,surplus = ?,expiryDate = ?,userId = ?,address = ? WHERE foodItemId = ?");
			query.setString(1, item.getName());
			query.setInt(2, item.getQuantity());
			query.setInt(3,item.getPrice());
			query.setInt(4,item.getSurplus());
			query.setDate(5,item.getExpDate());
			query.setInt(6,item.getOwnerID());
			query.setString(7,item.getAddress());
			query.setInt(8, item.getItemID());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    /**
     * Retrieves an item from the inventory based on the specified ID.
     *
     * @param id the ID of the item to retrieve
     * @return the retrieved food item
     */
	public FoodItem retrieveItem(int id) {
		FoodItem item = new FoodItem();
		ResultSet results = null;
		try {
			query = DBC.useConnection().prepareStatement("SELECT * FROM RetailInventory WHERE foodItemId = ?");
			query.setInt(1, id);
			results = query.executeQuery();
			while (results.next()) {
				item.setItemID(results.getInt("foodItemId"));
				item.setName(results.getString("itemName"));
				item.setQuantity(results.getInt("quantity"));
				item.setPrice(results.getInt("price"));
				item.setSurplus(results.getInt("surplus"));
				item.setExpDate(results.getDate("expiryDate"));
				item.setOwnerID(results.getInt("userId"));
				item.setAddress(results.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
    /**
     * Retrieves all items from the inventory.
     *
     * @return an ArrayList of all food items in the inventory
     */
	public ArrayList<FoodItem> retrieveAllItems() {
		ArrayList<FoodItem> items = new ArrayList<>();
		ResultSet results = null;
		try {
			query = DBC.useConnection().prepareStatement("SELECT * FROM RetailInventory");
			results = query.executeQuery();
			while (results.next()) {
				FoodItem item = new FoodItem();
				item.setItemID(results.getInt("foodItemId"));
				item.setName(results.getString("itemName"));
				item.setQuantity(results.getInt("quantity"));
				item.setPrice(results.getInt("price"));
				item.setSurplus(results.getInt("surplus"));
				item.setExpDate(results.getDate("expiryDate"));
				item.setOwnerID(results.getInt("userId"));
				item.setAddress(results.getString("address"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
    /**
     * Deletes an item from the inventory.
     *
     * @param item the food item to delete
     */
	public void deleteItem(FoodItem item) {
		try {
			query = DBC.useConnection().prepareStatement("DELETE FROM RetailInventory WHERE foodItemId = ?");
			query.setInt(1, item.getItemID());
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    /**
     * Retrieves discounted food items from the inventory.
     *
     * @return an ArrayList of discounted food items
     */
    public ArrayList<FoodItem> getDiscountedFoodItems() 
    {
        ArrayList<FoodItem> discountedFoodItems = new ArrayList<>();
        ResultSet results = null;
        try
        {
            query = DBC.useConnection().prepareStatement("SELECT * FROM RetailInventory WHERE surplus = 2");
            results = query.executeQuery();
            while (results.next())
            {
                FoodItem item = new FoodItem();
                item.setItemID(results.getInt("foodItemId"));
                item.setName(results.getString("itemName"));
                item.setQuantity(results.getInt("quantity"));
                item.setPrice(results.getInt("price"));
                item.setSurplus(results.getInt("surplus"));
                item.setExpDate(results.getDate("expiryDate"));
                item.setOwnerID(results.getInt("userId"));
                item.setAddress(results.getString("address"));
                discountedFoodItems.add(item);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return discountedFoodItems;
    }

	public void deleteItemWithId(int foodItemId) {
		try {
			query = DBC.useConnection().prepareStatement("DELETE FROM RetailInventory WHERE foodItemId = ?");
			query.setInt(1, foodItemId);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	}
