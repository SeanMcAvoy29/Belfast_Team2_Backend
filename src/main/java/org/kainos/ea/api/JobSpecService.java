package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobSpecRequest;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobDoesNotExistException;
import org.kainos.ea.db.JobSpecDAO;

import java.sql.SQLException;

public class JobSpecService {
    final private JobSpecDAO jobspecDAO = new JobSpecDAO();

    public JobSpecRequest getJobspecById(int id) throws FailedToGetJobSpecException, JobDoesNotExistException {
        try{
            JobSpecRequest jobspec = jobspecDAO.getJobspecById(id);

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


