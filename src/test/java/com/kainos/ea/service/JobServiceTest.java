package com.kainos.ea.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.db.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {
    org.kainos.ea.db.JobDao JobDao = Mockito.mock(org.kainos.ea.db.JobDao.class);
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
    public void createJob_shouldReturnJobNumber_whenDaoCreatesJob() throws SQLException, DatabaseConnectionException {
        int jobNo1 = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.createJob(jobRequest, conn)).thenReturn(jobNo1);

        int result = jobService.createJob(jobRequest);

        assertEquals(result, jobNo1);
    }

    @Test
    public void createJob_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.createJob(jobRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobService.createJob(jobRequest));
    }

    @Test
    public void createJob_shouldThrowDatabaseConnectionException_whenConnectorThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobService.createJob(jobRequest));
    }
}
