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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CapabilityLeadServiceTest {

    CapabilityLeadDAO capabilityLeadDao = Mockito.mock(CapabilityLeadDAO.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    CapabilityLeadService capabilityLeadService = new CapabilityLeadService(capabilityLeadDao, databaseConnector);


    Connection conn;

    @Test
    void getCapabilityLead_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityLeadDao.getCapabilityLeadByCapabilityId(10000)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> capabilityLeadService.getCapabilityLeadByCapabilityId(10000));
    }

    /*
    Mocking Exercise 2

    Write a unit test for the getEmployee method

    When the dao returns an employee

    Expect the employee to be returned

    This should pass without code changes
     */

    @Test
    void getEmployee_shouldReturnEmployee_whenDaoReturnsName() throws DatabaseConnectionException, SQLException, CapabilityLeadDoesNotExistException {
        CapabilityLead expectedCapabilityLead = new CapabilityLead("Engineering", "Patrick Jones", "Hello, this is a test message!", "https://www.google.com");
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(capabilityLeadDao.getCapabilityLeadByCapabilityId(1)).thenReturn(expectedCapabilityLead);

        CapabilityLead result = capabilityLeadService.getCapabilityLeadByCapabilityId(1);

        assertEquals(result, expectedCapabilityLead);
    }

    /*
    Mocking Exercise 3

    Write a unit test for the getEmployee method

    When the dao returns null

    Expect UserDoesNotExistException to be thrown

    This should fail, make code changes to make this test pass
     */

    @Test
    void getEmployee_shouldThrowUserDoesNotExistException_whenDaoReturnsNull() throws UserDoesNotExistException, SQLException, DatabaseConnectionException {
        int expectedResult = 0;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployee(1, conn)).thenReturn(null);


        assertThrows(UserDoesNotExistException.class,
                () -> employeeService.getEmployee(1));

    }

    /*
    Mocking Exercise 4

    Write a unit test for the getEmployees method

    When the dao returns a list of employees

    Expect the list of employees to be returned

    This should pass without code changes
     */

    @Test
    void getEmployee_shouldReturnListOfEmployees_whenDaoReturnsListOfEmployees() throws UserDoesNotExistException, SQLException, DatabaseConnectionException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployees(conn)).thenReturn(employeeList);


        assertEquals(employeeList, employeeService.getEmployees());

    }


}