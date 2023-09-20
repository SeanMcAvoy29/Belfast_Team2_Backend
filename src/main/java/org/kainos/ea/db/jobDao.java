package org.kainos.ea.db;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.client.InvalidJobException;
import org.kainos.ea.client.JobRoleDoesNotExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class jobDao {
    public int createJob(JobRequest job, Connection connector) throws SQLException {
        String insertStatement = "INSERT INTO JobRole (JobRoleName, Band, Responsibilities, Specification) VALUES (?,?,?,?);";
        PreparedStatement st = connector.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, job.getJobRole());
        st.setString(2, job.getBand());
        st.setString(3, job.getSpecifications());
        st.setString(4, job.getResponsibilities());

        int affectedRows = st.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        int empNo = 0;

        try (ResultSet rs = st.getGeneratedKeys()) {
            if (rs.next()) {
                empNo = rs.getInt(1);
            }
        }

        return empNo;
    }

    public List<Job> getAllJobRoles(Connection c) throws SQLException, FailedToGetJobRolesException {
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

    public Job getJobRoleByID(int id, Connection c) throws SQLException, JobRoleDoesNotExistException, DatabaseConnectionException {
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT JobID, JobRoleName, Band, Responsibilities, Specification" +
                " FROM JobRole WHERE JobID = " + id);


        while (rs.next()) {
            Job jobRole = new Job(
                    rs.getInt("JobID"),
                    rs.getString("JobRoleName"),
                    rs.getString("Band"),
                    rs.getString("Responsibilities"),
                    rs.getString("Specification")
            );
            jobRole.setJobID(id);
            return jobRole;
        }
        return null;
    }

    public Job updateJobRole(int id, JobRequest job, Connection c) throws SQLException, DatabaseConnectionException {

        String updateStatement = "UPDATE JobRole SET JobRoleName = ?, Band = ?, Responsibilities = ?, Specification = ? WHERE JobID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, job.getJobRole());
        st.setString(2, job.getBand());
        st.setString(3, job.getResponsibilities());
        st.setString(4, job.getSpecifications());
        st.setInt(5, id);

        st.executeUpdate();

        return null;
    }
    public Job deleteJobRole(int id, Connection c) throws SQLException {

        String deleteStatement = "DELETE FROM JobRole WHERE JobID = ?";

        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(1, id);

        st.executeUpdate();

        return null;
    }
}
