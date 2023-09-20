package org.kainos.ea.db;

import org.kainos.ea.cli.CapabilityLeadRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CapabilityLeadDAO {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public CapabilityLeadRequest getCapabilityLeadByCapabilityId(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT CapabilityName, CapabilityLeadName, CapabilityLeadMessage, CapabilityLeadPhotoURL FROM CapabilityLead INNER JOIN Capability ON CapabilityLead.CapabilityLeadID = Capability.CapabilityLead WHERE CapabilityLeadID = ?";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        while(rs.next()){
            return new CapabilityLeadRequest(
                    rs.getString("CapabilityName"),
                    rs.getString("CapabilityLeadName"),
                    rs.getString("CapabilityLeadMessage"),
                    rs.getString("CapabilityLeadPhotoURL")
            );
        }

        return null;
    }
}
