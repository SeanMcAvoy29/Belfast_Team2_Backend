package org.kainos.ea.api;

import org.kainos.ea.cli.JobSpecRequest;
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


    public JobSpecRequest getJobSpecById(int id) throws SQLException, JobDoesNotExistException {

        JobSpecRequest jobSpec = jobspecDAO.getJobSpecById(id,connector.getConnection());

        if (jobSpec == null){
            throw new JobDoesNotExistException();
        }
        return jobSpec;
    }
}
