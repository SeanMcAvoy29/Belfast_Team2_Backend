package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.jobDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobService {
    private jobDao jobDao;
    public DatabaseConnector connector;
    private JobValidator jobValidator = new JobValidator();

    public JobService(org.kainos.ea.db.jobDao jobDao, DatabaseConnector connector) {
        this.jobDao = jobDao;
        this.connector = connector;
    }

    public int createJob(JobRequest jobRequest) throws DatabaseConnectionException, SQLException {
        return jobDao.createJob(jobRequest, connector.getConnection());
    }

    public List<Job> getAllJobRoles() throws DatabaseConnectionException, SQLException, FailedToGetJobRolesException {
        return jobDao.getAllJobRoles(connector.getConnection());
    }

    public Job getJobRoleByID(int id) throws SQLException, DatabaseConnectionException, JobRoleDoesNotExistException {
        return jobDao.getJobRoleByID(id, connector.getConnection());
    }

    public Job deleteJobRole(int id) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException, DatabaseConnectionException, SQLException {
        return jobDao.deleteJobRole(id, connector.getConnection());
    }

    public Job updateJobRole(int id, JobRequest job) throws InvalidJobException, JobRoleDoesNotExistException, FailedToUpdateJobRoleException, DatabaseConnectionException, SQLException {
        return jobDao.updateJobRole(id, job, connector.getConnection());
    }
}
