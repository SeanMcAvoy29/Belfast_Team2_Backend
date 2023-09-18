package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobSpecRequest;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobDoesNotExistException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class JobSpecService {
    private JobSpecDAO jobspecDAO;
    private final DatabaseConnector connector;

    public JobSpecService(JobSpecDAO jobspecDAO, DatabaseConnector connector) {
        this.jobspecDAO = jobspecDAO;
        this.connector = connector;
    }





    public JobSpecRequest getJobspecById(int id) throws SQLException, JobDoesNotExistException {

        JobSpecRequest jobspec = jobspecDAO.getJobspecById(id,connector.getConnection());

        if (jobspec == null){
            throw new JobDoesNotExistException();
        }
        return jobspec;
    }
}


