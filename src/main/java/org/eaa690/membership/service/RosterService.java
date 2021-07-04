package org.eaa690.membership.service;

import org.apache.commons.lang3.StringUtils;
import org.eaa690.membership.model.Member;

import java.util.Properties;

/**
 * RosterService
 */
public class RosterService {

    /**
     * Application properties.
     */
    private Properties props;

    /**
     * Default Constructor.
     *
     * @param props Application properties
     */
    public RosterService(final Properties props) {
        this.props = props;
    }

    /**
     * Check if provided RFID belongs to an admin user.
     *
     * @param rfid RFID
     * @return admin?
     */
    public boolean isAdmin(final String rfid) {
        if (StringUtils.isNotEmpty(rfid)) {
            if ("3353207838".equalsIgnoreCase(rfid)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Finds member information from provided RFID.
     *
     * @param rfid RFID
     * @return Member
     */
    public Member getMember(final String rfid) {
        // TODO: do something
        return null;
    }

    /**
     * Updates the RFID for a member.
     *
     * @param memberId Member ID
     * @param rfid RFID
     */
    public void updateRFID(final String memberId, final String rfid) {
        // TODO: do something
    }
}
