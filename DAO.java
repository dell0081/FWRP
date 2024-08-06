package dataaccesslayer;


/**
 * The {@code DAO} interface provides a contract for Data Access Objects (DAOs).
 *
 * @version 1.0
 * @since OpenJDK version 17.0.7
 * @author Luke Mackay
 * @see DatabaseConnection
 */
public interface DAO 
{
	
	/** 
	 * The DatabaseConnection object for accessing the database. 
	 */
	DatabaseConnection DBC = DatabaseConnection.useDBC();
	
	
}
