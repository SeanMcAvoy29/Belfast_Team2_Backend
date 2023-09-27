package org.kainos.ea.api;

import org.kainos.ea.cli.CapabilityLeadRequest;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.db.CapabilityLeadDAO;


import java.sql.SQLException;

public class CapabilityLeadService {

    final private CapabilityLeadDAO capabilityleadDAO = new CapabilityLeadDAO();

    public CapabilityLeadRequest getCapabilityLeadByCapabilityId(int id) throws DatabaseConnectionException, SQLException {

        try {
            return capabilityleadDAO.getCapabilityLeadByCapabilityId(id);
        }
        catch (SQLException e){
            throw new SQLException();
        }
    }
}
