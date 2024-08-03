package DataAccessLayer;

import Model.ItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharityDAO extends UserDAO {
    private Connection connection;

    public CharityDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    public List<ItemDTO> getAllAvailableItemsForUser() {
        List<ItemDTO> items = new ArrayList<>();
        String query = "SELECT i.inventory_id, i.item_name, i.quantity, i.price, i.user_id AS retailer_id, u.retailer_name " +
                       "FROM Inventory i " +
                       "JOIN Users u ON i.user_id = u.user_id " +
                       "WHERE u.Users = 'retailer' AND for_charity = 0 ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ItemDTO item = new ItemDTO();
                item.setItemId(resultSet.getInt("inventory_id"));
                item.setItemName(resultSet.getString("item_name"));
                item.setItemQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getFloat("price"));
                item.setRetailerId(resultSet.getInt("retailer_id"));
                item.setRetailerName(resultSet.getString("retailer_name"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<ItemDTO> getAllAvailableItemsForCharity() {
        List<ItemDTO> items = new ArrayList<>();
        String query = "SELECT i.inventory_id, i.item_name, i.quantity, i.price, i.user_id AS retailer_id, u.retailer_name " +
                       "FROM Inventory i " +
                       "JOIN Users u ON i.user_id = u.user_id " +
                       "WHERE u.Users = 'retailer' AND for_charity = 1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ItemDTO item = new ItemDTO();
                item.setItemId(resultSet.getInt("inventory_id"));
                item.setItemName(resultSet.getString("item_name"));
                item.setItemQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getFloat("price"));
                item.setRetailerId(resultSet.getInt("retailer_id"));
                item.setRetailerName(resultSet.getString("retailer_name"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
