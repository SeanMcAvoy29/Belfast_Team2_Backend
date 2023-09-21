package com.kainos.ea.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.Job;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.db.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobServiceTest {

    org.kainos.ea.db.JobDao JobDao = Mockito.mock(org.kainos.ea.db.JobDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    JobService jobService = new JobService(JobDao, databaseConnector);

    Job job = new Job(
            5,
            "EmployeeTest",
            "FullTime",
            "Please Work",
            "PLEASE WORK"
    );

    Connection conn;

    @Test
    public void getAllJobRoles_shouldReturnJobRolesList_whenDaoReturnsJobRoleList() throws SQLException, DatabaseConnectionException {
        List<Job> expectedResult = new ArrayList<>();
        expectedResult.add(job);
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.getAllJobRoles(conn)).thenReturn(expectedResult);

        List<Job> result = jobService.getAllJobRoles();

        assertEquals(result, expectedResult);
    }

    @Test
    public void getAllJobRoles_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.getAllJobRoles(conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobService.getAllJobRoles());
    }

    @Test
    public void getAllJobRoles_shouldThrowDatabaseConnectionException_whenConnectorThrowsDatabaseConnectionException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenThrow(DatabaseConnectionException.class);

        assertThrows(DatabaseConnectionException.class,
                () -> jobService.getAllJobRoles());
    }
}
