package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.db.jobDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public List<Job> getAllJobRoles() throws FailedToGetJobRolesException {
        try {
            List<Job> jobRoleList = jobDao.getAllJobRoles();

            return jobRoleList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetJobRolesException();
        }
    }

    public Job getJobRoleByID(int id) throws FailedToGetJobRolesException, JobRoleDoesNotExistException {
        try {
            Job jobRole = jobDao.getJobRoleByID(id);

            if (jobRole == null) {
                throw new JobRoleDoesNotExistException();
            }

            return jobRole;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetJobRolesException();
        }
    }

    public void deleteJobRole(int id) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        try {
            Job jobRoleToDelete = jobDao.getJobRoleByID(id);

            if (jobRoleToDelete == null) {
                throw new JobRoleDoesNotExistException();
            }

            jobDao.deleteJobRole(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteJobRoleException();
        }
    }

    public void updateJob(int id, JobRequest job) throws InvalidJobException, JobRoleDoesNotExistException, FailedToUpdateJobRoleException {
        try {
            String validation = jobValidator.isValidJob(job);

            if (validation != null) {
                throw new InvalidJobException(validation);
            }
            Job jobToUpdate = jobDao.getJobRoleByID(id);

            if (jobToUpdate == null) {
                throw new JobRoleDoesNotExistException();
            }

            jobDao.updateJobRole(id, job);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateJobRoleException();
        }
    }
}
