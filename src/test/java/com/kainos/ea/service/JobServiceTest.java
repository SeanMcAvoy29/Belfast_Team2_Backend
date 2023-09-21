package com.kainos.ea.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.FailedToGetJobRolesException;
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
    public void getAllJobRoles_shouldReturnJobRolesList_whenDaoReturnsJobRoleList() throws SQLException, DatabaseConnectionException, FailedToGetJobRolesException {
        List<Job> expectedResult = JobDao.getAllJobRoles(conn);
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobService.getAllJobRoles()).thenReturn(expectedResult);

        List<Job> result = JobDao.getAllJobRoles(conn);

        assertEquals(result, expectedResult);
    }

    @Test
    public void getAllJobRoles_shouldThrowSQLException_whenDaoThrowsSQLException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(JobDao.getAllJobRoles(conn) == null).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> JobDao.getAllJobRoles(conn));
    }
}
