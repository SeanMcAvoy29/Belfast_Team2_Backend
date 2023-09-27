package org.kainos.ea.api;

import org.kainos.ea.cli.BandResponse;
import org.kainos.ea.client.FailedToGetBandsException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class BandService {

    private BandDao bandDao;

    public DatabaseConnector databaseConnector;

    public BandService(BandDao bandDao, DatabaseConnector databaseConnector) {
        this.bandDao = bandDao;
        this.databaseConnector = databaseConnector;
    }

    public List<BandResponse> getBands() throws SQLException, FailedToGetBandsException {
        try {
            return bandDao.getBands();
        } catch (SQLException e) {
            throw new FailedToGetBandsException();
    }
}}
