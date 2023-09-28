package com.kainos.ea.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.api.BandService;
import org.kainos.ea.cli.Band;
import org.kainos.ea.cli.BandResponse;
import org.kainos.ea.client.FailedToGetBandsException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BandServiceTest {

    BandDao bandDao = Mockito.mock(BandDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    BandService bandService = new BandService(bandDao, databaseConnector);

    @Test
    public void getBands_shouldReturnBands() throws SQLException, FailedToGetBandsException {
        List<BandResponse> response = new ArrayList<>();
        response.add(new BandResponse(9, "Band"));

        Mockito.when(bandDao.getBands()).thenReturn(response);

        assertEquals(response, bandService.getBands());
    }

    @Test
    public void getBands_shouldThrowFailedToGetBandsException_whenSQLException() throws SQLException {
        Mockito.when(bandDao.getBands()).thenThrow(SQLException.class);

        assertThrows(FailedToGetBandsException.class, () -> bandService.getBands());
    }
}