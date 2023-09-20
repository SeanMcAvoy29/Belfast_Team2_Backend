package org.kainos.ea.api;

import org.kainos.ea.cli.CapabilityLeadRequest;
import org.kainos.ea.client.CapabilityLeadDoesNotExistException;
import org.kainos.ea.client.FailedToGetCapabilityLeadException;
import org.kainos.ea.db.CapabilityLeadDAO;


import java.sql.SQLException;

public class CapabilityLeadService {

    final private CapabilityLeadDAO capabilityleadDAO = new CapabilityLeadDAO();

    public CapabilityLeadRequest getCapabilityLeadByCapabilityId(int id) throws FailedToGetCapabilityLeadException, CapabilityLeadDoesNotExistException {
        try{
            CapabilityLeadRequest capability = capabilityleadDAO.getCapabilityLeadByCapabilityId(id);

            if (capability == null){
                throw new FailedToGetCapabilityLeadException();
            }
            return capability;
        } catch(SQLException e){
            System.err.println(e.getMessage());
            throw new CapabilityLeadDoesNotExistException();
        }
    }
}
