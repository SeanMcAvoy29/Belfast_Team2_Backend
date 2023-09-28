package org.kainos.ea.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.cli.JobSpecResponse;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.client.JobRoleDoesNotExistException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDAO;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobSpecServiceTest {
    JobSpecDAO jobSpecDAO = Mockito.mock(JobSpecDAO.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    JobSpecService jobSpecService = new JobSpecService(jobSpecDAO,databaseConnector);
    Connection conn;

    @Test
    void getJobSpec_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobSpecDAO.getJobSpecById(10,conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobSpecService.getJobSpecById(10));
    }

    @Test
    void getJobSpec_shouldReturnJobDoesNotExistException_whenDaoReturnsJobDoesNotExistException () throws SQLException, DatabaseConnectionException {
        int id = 1;
        Mockito.when(jobSpecDAO.getJobSpecById(1,databaseConnector)).thenReturn(null);

        assertThrows(JobRoleDoesNotExistException.class,
                () -> jobSpecService.getJobSpecById(id));
    }

    @Test
    void getJobSpec_shouldReturnJobSpec_whenDaoReturnsJobSpec () throws SQLException, JobRoleDoesNotExistException, DatabaseConnectionException {

        List<String> responsibilities = new ArrayList<>();
        responsibilities.add("Coding");
        responsibilities.add("Testing");
        responsibilities.add("Git");

        JobSpecResponse expectResult = new JobSpecResponse("Test - Job Role","Test - Job Spec",responsibilities,"https://Linktest.com");
        Mockito.when(jobSpecDAO.getJobSpecById(1,databaseConnector)).thenReturn(expectResult);

        JobSpecResponse result = jobSpecService.getJobSpecById(1);
        assertEquals(expectResult.getJobRole(), result.getJobRole());
        assertEquals(expectResult.getSpecifications(), result.getSpecifications());
        assertEquals(expectResult.getSharePointLink(), result.getSharePointLink());
        assertEquals(expectResult.getResponsibilities(), result.getResponsibilities());
    }
}
