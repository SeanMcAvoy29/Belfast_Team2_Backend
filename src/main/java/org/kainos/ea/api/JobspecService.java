package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobDoesNotExistException;
import org.kainos.ea.db.JobspecDAO;

import java.sql.SQLException;

public class JobspecService {
    private JobspecDAO jobspecDAO = new JobspecDAO();

    public Job getJobspecById(int id) throws FailedToGetJobSpecException, JobDoesNotExistException {
        try{
            Job jobspec = jobspecDAO.getJobspecById(id);

            if (jobspec == null){
                throw new FailedToGetJobSpecException();
            }
            return jobspec;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            throw new JobDoesNotExistException();
        }
    }
}


