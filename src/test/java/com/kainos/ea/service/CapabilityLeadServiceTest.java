package com.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.CapabilityLeadService;
import org.kainos.ea.cli.CapabilityLead;
import org.kainos.ea.cli.CapabilityLeadRequest;
import org.kainos.ea.client.CapabilityLeadDoesNotExistException;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.db.CapabilityLeadDAO;
import org.kainos.ea.db.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CapabilityLeadServiceTest {

    CapabilityLeadDAO capabilityLeadDao = Mockito.mock(CapabilityLeadDAO.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    CapabilityLeadService capabilityLeadService = new CapabilityLeadService(capabilityLeadDao);


    Connection conn;

    @Test
    void getCapabilityLead_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityLeadDao.getCapabilityLeadByCapabilityId(10000)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> capabilityLeadService.getCapabilityLeadByCapabilityId(10000));
    }

    @Test
    void getCapabilityLead_shouldReturnCapabilityLead_whenDaoReturnsName() throws DatabaseConnectionException, SQLException, CapabilityLeadDoesNotExistException {
        CapabilityLeadRequest expectedCapabilityLead = new CapabilityLeadRequest("Engineering", "Patrick Jones", "Hello, this is a test message!", "https://media.istockphoto.com/id/1314997483/photo/portrait-of-a-confident-mature-businessman-working-in-a-modern-office.jpg?s=1024x1024&w=is&k=20&c=dXDaMTcP9kERe6F6xcISpHM0rDhEceKwHd-Dq08v6VE=");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityLeadDao.getCapabilityLeadByCapabilityId(1)).thenReturn(expectedCapabilityLead);

        CapabilityLeadRequest result = capabilityLeadService.getCapabilityLeadByCapabilityId(1);

        assertEquals(result, expectedCapabilityLead);
    }

    @Test
    void getCapabilityLead_shouldThrowCapabilityLeadDoesNotExistException_whenDaoReturnsNull() throws CapabilityLeadDoesNotExistException, SQLException, DatabaseConnectionException {
        int expectedResult = 0;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityLeadDao.getCapabilityLeadByCapabilityId(1)).thenReturn(null);


        assertThrows(CapabilityLeadDoesNotExistException.class,
                () -> capabilityLeadService.getCapabilityLeadByCapabilityId(1));

    }

}
