package util;

import com.beard.util.ConnectorDB;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class ConnectorDBTest {

    @Test
    public void shouldConnectToDB() throws SQLException {
        ConnectorDB connectorDB = new ConnectorDB();
        Connection con = connectorDB.getDataSource().getConnection();
        assertNotNull(con);
    }
}
