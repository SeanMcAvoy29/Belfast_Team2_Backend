package com.kainos.ea.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.jobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    jobDao JobDao = Mockito.mock(jobDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobValidator jobValidator = new JobValidator();
    JobService jobService = new JobService(JobDao, databaseConnector);

    JobRequest jobRequest = new JobRequest(
            5,
            "EmployeeTest",
            "FullTime",
            "Please Work",
            "PLEASE WORK"
    );

    Connection conn;

    @Test
    void createJob_shouldReturnId_whenDaoReturnsId() throws SQLException, DatabaseConnectionException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.createJob(jobRequest, conn)).thenReturn(expectedResult);

        int result = jobService.createJob(jobRequest);

        assertEquals(result, expectedResult);
    }

    @Test
    void createJob_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.createJob(jobRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> JobDao.createJob(jobRequest, conn));
    }

    @Test
    void getJobRoleByID_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException, JobRoleDoesNotExistException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        int id = 1;
        Mockito.when(JobDao.getJobRoleByID(id, conn) == null).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> JobDao.getJobRoleByID(id, conn));
    }

    @Test
    void getJobRoleByID_shouldReturnJobRole_whenDaoReturnsJobRole() throws DatabaseConnectionException, SQLException, JobRoleDoesNotExistException {
        int id = 1;
        Job expectedResult = jobService.getJobRoleByID(id);
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.getJobRoleByID(id, conn)).thenReturn(expectedResult);

        Job result = jobService.getJobRoleByID(id);

        assertEquals(result, expectedResult);
    }

    @Test
    void GetJobRoleByID_ShouldThrowJobRoleDoesNotExistException_whenJobRoleDoesNotExist() throws SQLException, JobRoleDoesNotExistException, DatabaseConnectionException {
        int id = -1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.getJobRoleByID(id, conn)).thenThrow(JobRoleDoesNotExistException.class);

        assertThrows(JobRoleDoesNotExistException.class,
                () -> JobDao.getJobRoleByID(id, conn));
    }

    @Test
    void getAllJobRoles_shouldReturnJobRolesList_whenDaoReturnsJobRoleList() throws SQLException, FailedToGetJobRolesException, DatabaseConnectionException {
            List<Job> expectedResult = JobDao.getAllJobRoles(conn);
            Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
            Mockito.when(jobService.getAllJobRoles()).thenReturn(expectedResult);

            List<Job> result = JobDao.getAllJobRoles(conn);

            assertEquals(result, expectedResult);
    }

    @Test
    void deleteJobRole_shouldDeleteJobRole_whenDaoDeletesJobRole() throws SQLException, DatabaseConnectionException, JobRoleDoesNotExistException, FailedToDeleteJobRoleException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobService.deleteJobRole(1)).thenReturn(null);

        Job result = JobDao.deleteJobRole(1, conn);

        assertEquals(result, null);
    }

    @Test
    void updateJobRole_shouldUpdateJobRole_whenDaoUpdatesJobRole() throws SQLException, DatabaseConnectionException, JobRoleDoesNotExistException, FailedToUpdateJobRoleException, InvalidJobException {
        JobRequest jobRequest = new JobRequest(8, "UpdateThis", "test", "test", "test");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobService.updateJobRole(1, jobRequest)).thenReturn(null);

        Job result = JobDao.deleteJobRole(1, conn);

        assertEquals(result, null);
    }
}
