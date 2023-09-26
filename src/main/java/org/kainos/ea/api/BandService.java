package org.kainos.ea.api;

import org.kainos.ea.cli.BandResponse;
import org.kainos.ea.client.FailedToGetBandsException;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

    public class BandService {

        private BandDao bandDao = new BandDao();

        public DatabaseConnector databaseConnector = new DatabaseConnector();

        public BandService(BandDao bandDao, DatabaseConnector databaseConnector) {
            this.bandDao = bandDao;
            this.databaseConnector = databaseConnector;
        }

        public List<BandResponse> getBands() throws SQLException, FailedToGetBandsException {
            List<BandResponse> bandResponseList = null;
            try {
                bandResponseList = bandDao.getBands();
            } catch (SQLException e) {
                throw new FailedToGetBandsException();
            }
            return bandResponseList;
        }
    }

