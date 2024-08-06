package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code CouponsDAO} class implements the Data Access Object (DAO) design pattern
 * to provide methods for CRUD operations related to coupons in the database.
 *
 * @version 1.0
 * @since OpenJDK version 17.0.7
 * @author Luke Mackay
 * @see DAO
 * @see DatabaseConnection
 * @see Connection
 * @see PreparedStatement
 * @see SQLException
 * @see ResultSet
 */
public class CouponsDAO implements DAO
{
    /**
     * The database connection used for CRUD operations on coupons.
     */
    private final Connection connection;

    /**
     * The instance of CouponsDAO, following the Singleton pattern.
     */
    private static CouponsDAO couponsDAOInstance;

    /**
     * Constructs a new CouponsDAO object and initializes the database connection.
     */
    private CouponsDAO()
    {
        connection = DatabaseConnection.useDBC().useConnection();
    }

    /**
     * Retrieves the singleton instance of CouponsDAO.
     *
     * @return the singleton instance of CouponsDAO
     */
    public static CouponsDAO getCouponsDAOInstance()
    {
        if (couponsDAOInstance == null)
        {
            couponsDAOInstance = new CouponsDAO();
        }
        return couponsDAOInstance;
    }

    /**
     * Creates a new coupon record in the database for the specified user.
     *
     * @param userId the ID of the user for whom the coupon is created
     */
   public void createCoupon (int userId)
   {
       try
       {
           String sql = "INSERT INTO Coupons (userId) VALUES ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, userId);
           statement.executeUpdate();
       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
   }

    /**
     * Updates the coupon threshold for a specific coupon ID in the database.
     *
     * @param couponId the ID of the coupon to be updated
     * @param couponThreshold the new threshold value for the coupon
     */
   public void updateCoupon(int couponId, int couponThreshold)
   {
       try
       {
           String sql = "UPDATE Coupons SET couponThreshold = ? WHERE couponId = ?";
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1, couponThreshold);
           statement.setInt(2, couponId);
           statement.executeUpdate();
       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
   }

    /**
     * Deletes a coupon record from the database based on the coupon ID.
     *
     * @param couponId the ID of the coupon to be deleted
     */
   public void deleteCoupon (int couponId)
   {
       try
       {
            String sql = "DELETE FROM Coupons WHERE couponId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, couponId);
            statement.executeUpdate();
       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
   }

    /**
     * Retrieves the total coupon amount for a specific user from the database.
     *
     * @param userId the ID of the user whose coupon amount is retrieved
     * @return the total coupon amount for the specified user
     */
    public int retrieveCouponAmount(int userId)
    {
        int couponAmount = 0;
        try
        {
            String sql = "SELECT SUM(couponThreshold) AS total FROM Coupons WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                couponAmount = resultSet.getInt("total");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return couponAmount;
    }


}
