package org.kainos.ea.api;

import org.kainos.ea.cli.CapabilityLead;
import org.kainos.ea.client.CapabilityLeadDoesNotExistException;
import org.kainos.ea.client.DatabaseConnectionException;
import org.kainos.ea.db.CapabilityLeadDAO;


import java.sql.SQLException;

public class CapabilityLeadService {

    private CapabilityLeadDAO capabilityleadDAO;

    public CapabilityLeadService(CapabilityLeadDAO capabilityleadDAO) {
        this.capabilityleadDAO = capabilityleadDAO;
    }


    public CapabilityLead getCapabilityLeadByCapabilityId(int id) throws DatabaseConnectionException, SQLException, CapabilityLeadDoesNotExistException {

        try {
            CapabilityLead capabilityLeadRequest = capabilityleadDAO.getCapabilityLeadByCapabilityId(id);

            if (capabilityLeadRequest == null) {
                throw new CapabilityLeadDoesNotExistException();
            }

            return capabilityLeadRequest;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
