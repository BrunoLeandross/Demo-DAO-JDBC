package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB
{
    private static Connection conn = null;
    public static Connection getConnection ()
    {
        if (conn == null)
        {
            try
            {
                Properties Props = LoadProperties();
                String url = Props.getProperty("db.url");
                Props.setProperty("user", Props.getProperty("db.user"));
                Props.setProperty("password", Props.getProperty("db.password"));

                conn = DriverManager.getConnection(url, Props);
            }
            catch (SQLException e)
            {
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }
    public static void closeConnection()
    {
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                throw new DBException(e.getMessage());
            }
        }
    }

    private static Properties LoadProperties()
    {
        try(FileInputStream fs = new FileInputStream("src\\DB\\DB.Properties"))
        {
            Properties Props = new Properties();
            Props.load(fs);
            return Props;
        }
        catch (IOException e)
        {
            throw new DBException(e.getMessage());
        }
    }
}
