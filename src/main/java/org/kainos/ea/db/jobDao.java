package org.kainos.ea.db;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class jobDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public int createJob(JobRequest job) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO JobRole (JobRoleName, Band, Responsibilities, Specification) VALUES (?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, job.getJobRole());
        st.setString(2, job.getBand());
        st.setString(3, job.getSpecifications());
        st.setString(4, job.getResponsibilities());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public List<Job> getAllJobRoles() throws SQLException {
        Connection c = databaseConnector.getConnection();
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

            jobRoleList.add(jobRole);

        }
        return jobRoleList;
    }

    public Job getJobRoleByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT JobID, JobRoleName, Band, Responsibilities, Specification" +
                " FROM JobRole WHERE JobID = " + id);


        while (rs.next()) {
            return new Job(
                    rs.getInt("JobID"),
                    rs.getString("JobRoleName"),
                    rs.getString("Band"),
                    rs.getString("Responsibilities"),
                    rs.getString("Specification")
            );
        }
        return null;
    }
    public void deleteJobRole(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String deleteStatement = "DELETE FROM JobRole WHERE JobID = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1, id);

        st.executeUpdate();
    }
}
