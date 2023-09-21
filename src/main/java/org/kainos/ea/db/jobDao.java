package org.kainos.ea.db;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.FailedToGetJobRolesException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class jobDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Job> getAllJobRoles(Connection c) throws SQLException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT JobID, JobRoleName, Band, Responsibilities, Specification FROM JobRole;");

        List<Job> jobRoleList = new ArrayList<>();

        while (rs.next()) {
            Job jobRole = new Job(
                    rs.getInt("JobID"),
                    rs.getString("JobRoleName"),
                    rs.getString("Band"),
                    rs.getString("Responsibilities"),
                    rs.getString("Specification")
            );
            jobRole.setJobID(rs.getInt("JobID"));
            jobRoleList.add(jobRole);

        }
        return jobRoleList;
    }
}
