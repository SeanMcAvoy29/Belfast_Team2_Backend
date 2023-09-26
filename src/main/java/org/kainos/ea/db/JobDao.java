package org.kainos.ea.db;
import org.kainos.ea.cli.JobRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


    public class JobDao {

        public int createJob(JobRequest job, Connection connector) throws SQLException {
            String insertStatement = "INSERT INTO JobRoles (JobRoleName, BandID, JobSpecification, CapabilityID, Responsibilities, SharePointLink) VALUES (?,?,?,?,?,?);";
            PreparedStatement st = connector.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, job.getJobRole());
            st.setInt(2, job.getBandID());
            st.setString(3, job.getJobSpecification());
            st.setInt(4, job.getCapabilityID());
            st.setString(5, job.getResponsibilities());
            st.setString(6, job.getSharePointLink());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            int jobID = 0;

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    jobID = rs.getInt(1);
                }
            }

            return jobID;
        }
}
