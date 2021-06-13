#!/bin/sh
cd ~/chapter-membership-gui
git pull
mvn compile exec:java -Dexec.mainClass="org.eaa690.membership.MembershipApp"
