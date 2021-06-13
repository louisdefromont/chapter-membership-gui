#!/bin/sh
git pull
mvn compile exec:java -Dexec.mainClass="org.eaa690.membership.MembershipApp"
