package com.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.JobService;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.FailedToCreateJobException;
import org.kainos.ea.client.InvalidJobException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.jobDao;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
