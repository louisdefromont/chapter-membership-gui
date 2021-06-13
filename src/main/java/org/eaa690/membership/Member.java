package org.eaa690.membership;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Member {

    private Long rosterId;
    private String name;
    private Date expirationDate;
    private String rfid;
    private String status;
    private String memberType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String family;
    private Long numOfFamily;
    private String homePhone;
    private String cellPhone;
    private String eaaExpiration;
    private String youthProtectionExpiration;
    private String backgroundCheckExpiration;
    private String eaaNumber;
    private String email;
    private String slackUsername;

}
