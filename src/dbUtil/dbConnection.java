package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection
{
  /*private static final String USERNAME = "dbuser";
  private static final String PASSWORD = "dbpassword";
  private static final String CONN = "jdbc:mysql://localhost/login";
  private static final String SCONN = "jdbc:sqlite:startup.sqlite";
  */
  public static Connection getConnection()
    throws SQLException
  {
    try
    {
      Class.forName("org.sqlite.JDBC");
      //System.out.println("Conncted!");
      return DriverManager.getConnection("jdbc:sqlite:startup.sqlite");
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      ex.printStackTrace();
    }
    return null;
  }
}
