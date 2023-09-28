package org.kainos.ea.api;

import org.kainos.ea.cli.JobRole;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobRoleDao;

import java.sql.SQLException;
import java.util.List;

public class JobRoleService {

    private JobRoleDao jobRoleDao = new JobRoleDao();
    public DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobRoleService(JobRoleDao jobRoleDao, DatabaseConnector connector) {
        this.jobRoleDao = jobRoleDao;
        this.databaseConnector = databaseConnector;
    }
    public List<JobRole> getAllJobRoles() throws DatabaseConnectionException, SQLException, JobRoleDoesNotExistException {
        try {
           return jobRoleDao.getAllJobRoles();
        } catch (SQLException e) {
            throw new JobRoleDoesNotExistException();
        }
    }
}
