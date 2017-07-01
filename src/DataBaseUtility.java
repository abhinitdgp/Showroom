import java.sql.Connection;
import java.sql.DriverManager;

//public class DataBaseUtility
//{
//	public static Connection dbConnector()
//	{
//		Connection con = null;
//		try
//		{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return con;
//	}
//}
public class DataBaseUtility
{
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","honda","showroom");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
