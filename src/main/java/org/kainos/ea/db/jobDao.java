package org.kainos.ea.db;
import org.kainos.ea.cli.JobRequest;

import java.sql.*;

public class jobDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public int createJob(JobRequest job) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO JobRole (JobRoleName, Band, Responsibilities, Specification) VALUES (?,?,?,?)";

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
}
