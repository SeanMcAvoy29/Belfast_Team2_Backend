package org.kainos.ea.db;

import org.kainos.ea.cli.JobRequest;

import java.sql.*;


public class JobDao {
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
}
