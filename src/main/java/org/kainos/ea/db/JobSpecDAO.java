package org.kainos.ea.db;

import org.kainos.ea.cli.JobSpecRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JobSpecDAO {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobSpecRequest getJobspecById(int id) throws SQLException {

        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT JobRoleName, Specification FROM JobRole where JobID = ?";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1,id);

        ResultSet rs = st.executeQuery();

        while(rs.next()){
            return new JobSpecRequest(
                    rs.getString("JobRoleName"),
                    rs.getString("Specification")
            );
        }

        return null;
    }
}
