package com.kainos.ea.service;

import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {
    JobDao jobDao = Mockito.mock(JobDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    JobValidator jobValidator = Mockito.mock(JobValidator.class);
    JobService jobService = new JobService(jobDao, databaseConnector, jobValidator);
    JobRequest jobRequest = new JobRequest(
            "EmployeeTest",
            1,
            "Placement Employee",
            "Coding",
            "Test",
            2
    );

    Connection conn;

    @Test
    public void createJob_shouldReturnJobNumber_whenDaoCreatesJob() throws SQLException, DatabaseConnectionException, JobRoleTooLongException, ResponsibilitiesTooLongException, SpecificationsTooLongException, InvalidBandException, FailedToCreateJobException {
        int jobNo1 = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobValidator.isValidJob(jobRequest)).thenReturn(true);
        Mockito.when(jobDao.createJob(jobRequest, conn)).thenReturn(jobNo1);

        int result = jobService.createJob(jobRequest);

        assertEquals(result, jobNo1);
    }

    @Test
    public void createJob_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobValidator.isValidJob(jobRequest)).thenReturn(true);
        Mockito.when(jobDao.createJob(jobRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobService.createJob(jobRequest));
    }

    @Test
    public void createJob_shouldThrowDatabaseConnectionException_whenConnectorThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        Mockito.when(jobValidator.isValidJob(jobRequest)).thenReturn(true);
        assertThrows(DatabaseConnectionException.class,
                () -> jobService.createJob(jobRequest));
    }
    @Test
    public void createJob_shouldThrowFailedToCreateJobException_whenConnectorThrowsFailedToCreateJobException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);
        Mockito.when(jobValidator.isValidJob(jobRequest)).thenReturn(false);
        assertThrows(FailedToCreateJobException.class,
                () -> jobService.createJob(jobRequest));
    }
}
