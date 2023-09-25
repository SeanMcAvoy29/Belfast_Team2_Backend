package org.kainos.ea.api;

import org.kainos.ea.cli.JobSpecResponse;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.JobDoesNotExistException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDAO;

import java.sql.SQLException;

public class JobSpecService {
    private JobSpecDAO jobspecDAO;
    private final DatabaseConnector connector;

    public JobSpecService(JobSpecDAO jobspecDAO, DatabaseConnector connector) {
        this.jobspecDAO = jobspecDAO;
        this.connector = connector;
    }


    public JobSpecResponse getJobSpecById(int id) throws SQLException, JobDoesNotExistException, DatabaseConnectionException {

        JobSpecResponse jobSpec = jobspecDAO.getJobSpecById(id,connector.getConnection());

        if (jobSpec == null){
            throw new JobDoesNotExistException();
        }
        return jobSpec;
    }
}
