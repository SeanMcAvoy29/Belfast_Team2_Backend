package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.jobDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobService {
    private jobDao jobDao;
    private final DatabaseConnector connector;
    private JobValidator jobValidator = new JobValidator();

    public JobService(org.kainos.ea.db.jobDao jobDao, DatabaseConnector connector) {
        this.jobDao = jobDao;
        this.connector = connector;
    }

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

        } catch (BandLengthTooLongException e) {
            throw new RuntimeException(e);
        } catch (JobRoleTooLongException e) {
            throw new RuntimeException(e);
        } catch (FailedToUpdateJobRoleException e) {
            throw new RuntimeException(e);
        } catch (ResponsibilitiesTooLongException e) {
            throw new RuntimeException(e);
        } catch (JobRoleDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (SpecificationsTooLongException e) {
            throw new RuntimeException(e);
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

    public Job getJobRoleByID(int id, Connection c) throws FailedToGetJobRolesException, JobRoleDoesNotExistException {
        try {
            Job jobRole = jobDao.getJobRoleByID(id, c);

            if (jobRole == null) {
                throw new JobRoleDoesNotExistException();
            }

            return jobRole;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetJobRolesException();
        }
    }

    public void deleteJobRole(int id, Connection c) throws JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        try {
            Job jobRoleToDelete = jobDao.getJobRoleByID(id, c);

            if (jobRoleToDelete == null) {
                throw new JobRoleDoesNotExistException();
            }

            jobDao.deleteJobRole(id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToDeleteJobRoleException();
        }
    }

    public void updateJob(int id, Connection c, JobRequest job) throws InvalidJobException, JobRoleDoesNotExistException, FailedToUpdateJobRoleException {
        try {
            Job jobToUpdate = jobDao.getJobRoleByID(id, c);

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
