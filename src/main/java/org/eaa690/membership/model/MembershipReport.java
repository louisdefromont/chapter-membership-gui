package org.eaa690.membership.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MembershipReport {

    private int regularMemberCount = 0;

    private int regularMemberExpiredCount = 0;

    private int regularMemberWillExpire30DaysCount = 0;

    private int regularMemberWillExpire7DaysCount = 0;

    private int familyMemberCount = 0;

    private int familyMemberExpiredCount = 0;

    private int familyMemberWillExpire30DaysCount = 0;

    private int familyMemberWillExpire7DaysCount = 0;

    private int familyMembershipCount = 0;

    private int familyMembershipExpiredCount = 0;

    private int familyMembershipWillExpire30DaysCount = 0;

    private int familyMembershipWillExpire7DaysCount = 0;

    private int lifetimeMemberCount = 0;

    private int honoraryMemberCount = 0;

    private int studentMemberCount = 0;

    private int studentMemberExpiredCount = 0;

    private int studentMemberWillExpire30DaysCount = 0;

    private int studentMemberWillExpire7DaysCount = 0;

    private int prospectMemberCount = 0;

    private int nonMemberCount = 0;
}
