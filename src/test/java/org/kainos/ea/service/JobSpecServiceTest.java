package org.kainos.ea.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.kainos.ea.api.JobSpecService;
import org.kainos.ea.cli.JobSpecRequest;
import org.kainos.ea.client.JobDoesNotExistException;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobSpecDAO;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JobSpecServiceTest {
    JobSpecDAO jobSpecDAO = Mockito.mock(JobSpecDAO.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
    JobSpecService jobSpecService = new JobSpecService(jobSpecDAO,databaseConnector);
    Connection conn;

    @Test
    void getJobSpec_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobSpecDAO.getJobSpecById(10,conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobSpecService.getJobSpecById(10));
    }

    @Test
    void getJobSpec_shouldReturnJobDoesNotExistException_whenDaoReturnsJobDoesNotExistException () throws SQLException{
        int id = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobSpecDAO.getJobSpecById(1,conn)).thenReturn(null);

        assertThrows(JobDoesNotExistException.class,
                () -> jobSpecService.getJobSpecById(id));
    }

    @Test
    void getJobSpec_shouldReturnJobSpec_whenDaoReturnsJobSpec () throws SQLException, JobDoesNotExistException {

        JobSpecRequest expectResult = new JobSpecRequest("Test - Job Role","Test - Job Spec","Test");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(jobSpecDAO.getJobSpecById(1,conn)).thenReturn(expectResult);

        JobSpecRequest result = jobSpecService.getJobSpecById(1);
        assertEquals(expectResult.getJobRole(), result.getJobRole());
        assertEquals(expectResult.getSpecifications(), result.getSpecifications());
    }
}
