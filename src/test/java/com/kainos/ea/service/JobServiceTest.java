package com.kainos.ea.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.jobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

        jobDao JobDao = Mockito.mock(jobDao.class);
        DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

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
    void createJob_shouldReturnId_whenDaoReturnsId() throws SQLException, InvalidJobException, FailedToCreateJobException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.createJob(jobRequest)).thenReturn(expectedResult);

        int result = jobService.createJob(jobRequest);

        assertEquals(result, expectedResult);
    }

    @Test
    void createJob_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.createJob(jobRequest)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> JobDao.createJob(jobRequest));
    }

    @Test
    void getJobRoleByID_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        int id = 1;
        Mockito.when(JobDao.getJobRoleByID(id) == null).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> JobDao.getJobRoleByID(id));
    }

    @Test
    void getJobRoleByID_shouldReturnJobRole_whenDaoReturnsJobRole() throws DatabaseConnectionException, SQLException, FailedToGetJobRolesException, JobRoleDoesNotExistException {
        int id = 1;
        Job job = new Job(id, "admin", "1234", "Placement students, programming at academy", "Taking part in projects");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.getJobRoleByID(id)).thenReturn(job);
        Job result = jobService.getJobRoleByID(id);

        assertEquals(result, job);
    }
}
