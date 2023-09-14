package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.FailedToGetJobException;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.FailedToCreateJobException;
import org.kainos.ea.client.InvalidJobException;
import org.kainos.ea.db.jobDao;

import java.sql.SQLException;
import java.util.List;

public class JobService {
    private jobDao jobDao = new jobDao();
    private JobValidator jobValidator = new JobValidator();

    public int createJob(JobRequest job) throws FailedToCreateJobException, InvalidJobException {
        try {
            String validation = jobValidator.isValidJob(job);

            if (validation != null) {
                throw new InvalidJobException(validation);
            }
            int id = jobDao.createJob(job);

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return -1;
    }

    public List<Job> getAllJobs() throws FailedToGetJobException {
        try {
            List<Job> jobList = jobDao.getAllJobs();

            return jobList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetJobException();
        }
    }

}
