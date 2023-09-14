package org.kainos.ea.db;

import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobSpecRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JobSpecDAO {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobSpecRequest getJobspecById(int id) throws SQLException {

        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT JobRoleName, Specification, Responsibilities FROM JobRole where JobID = ?";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1,id);

        ResultSet rs = st.executeQuery();
        ArrayList<String> convertedResponsibilitiesList;

        while(rs.next()){
           String responsibilities = rs.getString("Responsibilities");
           convertedResponsibilitiesList = (ArrayList<String>) Arrays.asList(responsibilities.split(",", -1));
            return new JobSpecRequest(
                    rs.getString("JobRoleName"),
                    rs.getString("Specification"),
                    convertedResponsibilitiesList
            );
        }

        return null;
    }
}
