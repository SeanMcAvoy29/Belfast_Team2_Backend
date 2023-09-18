package org.kainos.ea.db;

import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobSpecRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JobSpecDAO {



    public JobSpecRequest getJobspecById(int id, Connection c) throws SQLException {


        String selectStatement = "SELECT JobRoleName, Specification FROM JobRole where JobID = ?;";

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
