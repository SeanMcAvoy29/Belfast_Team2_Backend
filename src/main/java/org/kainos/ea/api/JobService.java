package org.kainos.ea.api;

import org.kainos.ea.client.*;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;

import java.sql.SQLException;

public class JobService {
    private JobDao jobDao;
    public DatabaseConnector connector;

    public JobService(JobDao jobDao, DatabaseConnector connector) {
        this.jobDao = jobDao;
        this.connector = connector;
    }

    public int createJob(JobRequest jobRequest) throws SQLException, DatabaseConnectionException, JobRoleTooLongException, ResponsibilitiesTooLongException, SpecificationsTooLongException, BandIDDoesNotExistException, InvalidJobException {
        JobValidator jobValidator = new JobValidator();
        if (jobValidator.isValidJob(jobRequest)) {
            return jobDao.createJob(jobRequest, connector.getConnection());
        } else {
            return -1;
        }

    }
}