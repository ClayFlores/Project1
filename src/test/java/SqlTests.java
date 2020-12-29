import com.rev.utils.ConnectionUtil;
import com.rev.utils.DAO;
import com.rev.utils.PostgresConnectionUtil;
import com.rev.utils.models.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SqlTests {

    ConnectionUtil util;
    Properties dbProps = null;
    DAO dao;
    @Before
    public void init() throws IOException {
        System.out.println("before...");
        dbProps = new Properties();
        dbProps.load(new FileReader("src\\main\\resources\\db.properties"));
    }
    @After
    public void cleanup(){
        System.out.println("after...");
        util = null;
    }

    @Test
    public void canCreateConnection() throws SQLException {
        util = new PostgresConnectionUtil(dbProps);
        Assert.assertNotNull(util);
        Assert.assertTrue(util instanceof PostgresConnectionUtil);

    }

    @Test
    public void canCreateDAO() throws IOException, SQLException {
        dao = new DAO();
        Assert.assertNotNull(dao);
        Assert.assertTrue(dao instanceof DAO);
    }

    @Test
    public void canLogin() throws IOException, SQLException {
        dao = new DAO();
        Assert.assertTrue(dao.login("test@test.com","testpw"));
    }

    @Test
    public void canFailLogin() throws IOException, SQLException {
        dao = new DAO();
        Assert.assertFalse(dao.login("ase","wew"));
    }

    @Test
    public void canMakeReq() throws IOException, SQLException {
        dao = new DAO();
        Assert.assertTrue(dao.makeReimbRequest("test@test.com",400.00));
    }

    @Test
    public void canSelectDAO() throws IOException, SQLException {
        dao = new DAO();
        ResultSet res = dao.selectDAO("select * from project1.workers");
        Assert.assertNotNull(res);
    }

}
