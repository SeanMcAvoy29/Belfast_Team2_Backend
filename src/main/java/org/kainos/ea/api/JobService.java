package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.FailedToGetJobRolesException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.jobDao;

import java.sql.SQLException;
import java.util.List;

public class JobService {
    public DatabaseConnector connector;

    public JobService(org.kainos.ea.db.jobDao jobDao, DatabaseConnector connector) {
        this.jobDao = jobDao;
        this.connector = connector;
    }

    private jobDao jobDao;
    public List<Job> getAllJobRoles() throws DatabaseConnectionException, SQLException, FailedToGetJobRolesException {
        return jobDao.getAllJobRoles(connector.getConnection());
    }

}
