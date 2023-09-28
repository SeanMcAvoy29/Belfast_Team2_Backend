package org.kainos.ea.db;
import org.kainos.ea.cli.BandResponse;
import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JobRoleDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<JobRole> getAllJobRoles() throws SQLException {

        try {
            Connection c = databaseConnector.getConnection();
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT JobID, JobRoleName, JobSpecification, BandID, CapabilityID, Responsibilities, SharePointLink FROM JobRoles;");

            List<JobRole> jobRolesList = new ArrayList<>();

            while (rs.next()) {
                JobRole jobRole = new JobRole(
                        rs.getInt("JobID"),
                        rs.getString("JobRoleName"),
                        rs.getString("JobSpecification"),
                        rs.getString("BandID"),
                        rs.getString("CapabilityID"),
                        rs.getString("Responsibilities"),
                        rs.getString("SharePointLink")

                );
                jobRole.setJobID(rs.getInt("JobID"));
                jobRolesList.add(jobRole);

            }
            return jobRolesList;


        }
        catch (Exception e){

            throw new SQLException(e);
        } catch (DatabaseConnectionException e) {
            throw new RuntimeException(e);
        }


    }
}
