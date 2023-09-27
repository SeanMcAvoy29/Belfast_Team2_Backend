package org.kainos.ea.db;

import org.kainos.ea.cli.BandResponse;
import org.kainos.ea.client.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BandDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<BandResponse> getBands() throws SQLException {
        try{
            Connection c = databaseConnector.getConnection();
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT BandID, BandName FROM Bands;");

            List<BandResponse> list = new ArrayList<>();

            while (rs.next()) {
                BandResponse bandResponse = new BandResponse(
                        rs.getInt("BandID"),
                        rs.getString("BandName")

                );
                list.add(bandResponse);
            }

            return list;
        }
        catch (Exception | DatabaseConnectionException e)
        {
            throw new SQLException(e);
        }
    }
}