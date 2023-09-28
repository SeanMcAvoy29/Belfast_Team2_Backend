package org.kainos.ea.api;

import org.kainos.ea.cli.JobRequest;
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

    public int createJob(JobRequest jobRequest) throws SQLException {
        return jobDao.createJob(jobRequest, connector.getConnection());
    }
}
