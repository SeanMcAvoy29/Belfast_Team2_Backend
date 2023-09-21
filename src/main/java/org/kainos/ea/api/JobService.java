package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;

import java.sql.SQLException;
import java.util.List;

public class JobService {
    private DatabaseConnector connector;

    public JobService(JobDao jobDao, DatabaseConnector connector) {
        this.jobDao = jobDao;
        this.connector = connector;
    }

    private JobDao jobDao;
    public List<Job> getAllJobRoles() throws DatabaseConnectionException, SQLException {
        return jobDao.getAllJobRoles(connector.getConnection());
    }

}
