package org.kainos.ea.api;

import org.kainos.ea.client.*;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;

import java.sql.SQLException;

public class JobService {
    private JobDao jobDao;
    private DatabaseConnector connector;
    private JobValidator jobValidator;

    public JobService(JobDao jobDao, DatabaseConnector connector, JobValidator jobValidator) {
        this.jobDao = jobDao;
        this.connector = connector;
        this.jobValidator = jobValidator;
    }

    public int createJob(JobRequest jobRequest) throws SQLException, DatabaseConnectionException, FailedToCreateJobException {
        if (jobValidator.isValidJob(jobRequest)) {
            return jobDao.createJob(jobRequest, connector.getConnection());
        } else {
            throw new FailedToCreateJobException();
        }

    }
}