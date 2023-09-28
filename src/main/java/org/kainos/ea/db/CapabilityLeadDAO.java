package org.kainos.ea.db;

import org.kainos.ea.cli.CapabilityLead;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CapabilityLeadDAO {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public CapabilityLead getCapabilityLeadByCapabilityId(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String selectStatement = "SELECT CapabilityName, CapabilityLeadName, CapabilityLeadMessage, CapabilityLeadPhotoURL FROM CapabilityLead INNER JOIN Capability ON CapabilityLead.CapabilityLeadID = Capability.CapabilityLead WHERE CapabilityLeadID = ?";

        PreparedStatement st = c.prepareStatement(selectStatement);
        st.setInt(1, id);

        ResultSet rs = st.executeQuery();

        while(rs.next()){
            return new CapabilityLead(
                    rs.getString("CapabilityName"),
                    rs.getString("CapabilityLeadName"),
                    rs.getString("CapabilityLeadMessage"),
                    rs.getString("CapabilityLeadPhotoURL")
            );
        }

        return null;
    }
}
