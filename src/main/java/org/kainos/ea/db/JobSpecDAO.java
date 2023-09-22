package org.kainos.ea.db;

import org.kainos.ea.cli.JobSpecResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class JobSpecDAO {

    public JobSpecResponse getJobSpecById(int id, Connection c) throws SQLException {


        String selectStatement = "SELECT JobRoleName, Specification, Responsibilities, SharePointLink FROM JobRole where JobID = ?;";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1,id);

        ResultSet rs = st.executeQuery();
        List<String> convertedResponsibilitiesList;

        while(rs.next()){
           String responsibilities = rs.getString("Responsibilities");
           convertedResponsibilitiesList = Arrays.asList(responsibilities.split(",", -1));
            return new JobSpecResponse(
                    rs.getString("JobRoleName"),
                    rs.getString("Specification"),
                    convertedResponsibilitiesList,
                    rs.getString("SharePointLink")
            );
        }

        return null;
    }
}
