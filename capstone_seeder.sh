#!/bin/bash
echo "Seeding Capstone Insurance App..."

API_URL="http://localhost:8080"
declare -A TOKENS

USERS=(
  "Rep Man|repman@example.com|adminpass|REPRESENTATIVE|1980-01-01"
  "Susan Repster|susan.rep@example.com|reppass|REPRESENTATIVE|1982-07-15"
  "John Smithless|jsmithless@example.com|pass123|CUSTOMER|1990-05-10"
  "Emily Buyer|emilyb@example.com|pass456|CUSTOMER|1988-09-22"
  "Mike Tenant|mikete@example.com|pass789|CUSTOMER|1975-03-03"
  "Hayden Stone|hayden.stone@testmail.com|defaultpass|CUSTOMER|1976-09-04"
  "Hayden Blake|hayden.blake@testmail.com|defaultpass|CUSTOMER|1991-04-25"
  "Jordan Stone|jordan.stone@mailinator.com|defaultpass|CUSTOMER|1983-03-16"
  "Taylor Dale|taylor.dale@mailinator.com|defaultpass|CUSTOMER|1995-05-18"
  "Skylar Dale|skylar.dale@fakemail.net|defaultpass|CUSTOMER|1970-12-22"
  "Casey Harper|casey.harper@fakemail.net|defaultpass|REPRESENTATIVE|1992-01-04"
  "Hayden Fox|hayden.fox@testmail.com|defaultpass|CUSTOMER|1997-04-26"
  "Riley Gray|riley.gray@fakemail.net|defaultpass|REPRESENTATIVE|1971-01-07"
  "Morgan Sage|morgan.sage@mailinator.com|defaultpass|CUSTOMER|1994-12-01"
  "Reese Harper|reese.harper@fakemail.net|defaultpass|REPRESENTATIVE|1971-04-10"
  "Hayden Ray|hayden.ray@fakemail.net|defaultpass|REPRESENTATIVE|1987-12-23"
  "Quinn Gray|quinn.gray@fakemail.net|defaultpass|REPRESENTATIVE|1991-07-08"
  "Casey Dale|casey.dale@fakemail.net|defaultpass|CUSTOMER|1995-12-04"
  "Alex Sage|alex.sage@testmail.com|defaultpass|REPRESENTATIVE|1971-11-06"
  "Reese Lane|reese.lane@testmail.com|defaultpass|REPRESENTATIVE|1970-08-12"
  "Reese Gray|reese.gray@mailinator.com|defaultpass|REPRESENTATIVE|1984-01-19"
  "Taylor Blake|taylor.blake@testmail.com|defaultpass|CUSTOMER|1980-02-12"
  "Alex Harper|alex.harper@fakemail.net|defaultpass|REPRESENTATIVE|1969-05-28"
  "Quinn Ray|quinn.ray@mailinator.com|defaultpass|REPRESENTATIVE|1984-08-10"
  "Morgan Blake|morgan.blake@fakemail.net|defaultpass|CUSTOMER|1972-12-08"
)

for u in "${USERS[@]}"; do
  IFS='|' read -r name email pass role dob <<< "$u"
  roleEndpoint="register"
  [[ "$role" == "REPRESENTATIVE" ]] && roleEndpoint="registerrepresenative"
  curl -s -X POST "$API_URL/$roleEndpoint" \
    -d "username=$name" -d "email=$email" -d "password=$pass" -d "dateOfBirth=$dob" > /dev/null
done
echo "Registered users & reps."

for u in "${USERS[@]}"; do
  IFS='|' read -r name email pass role dob <<< "$u"
  token=$(curl -s -X POST "$API_URL/login?email=$email&password=$pass" | grep -oE '[0-9a-f\-]{36}')
  TOKENS["$name"]=$token
done
echo "Tokens collected."


curl -s -X POST "$API_URL/token/${TOKENS["Emily Buyer"]}/homequote" \
  -F "insuredHome.dateBuilt=1990-01-01" -F "insuredHome.homeValue=350000" -F "insuredHome.liabilityLimit=2500000" \
  -F "insuredHome.dwellingType=BUNGALOW" -F "insuredHome.heatingType=OIL" -F "insuredHome.locationType=RURAL" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1350" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Emily Buyer"]}/autoquote" \
  -F "driverAge=22" -F "insuredAutomobile.vehicleMake=Ford" -F "insuredAutomobile.vehicleModel=Focus" \
  -F "insuredAutomobile.vehicleManufactureDate=2015-01-01" -F "insuredAutomobile.numberofAccidents=2" \
  -F "basePremium=750" -F "taxRate=0.15" -F "totalPremium=1720" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Susan Repster"]}/homepolicy" \
  -F "insuredHome.dateBuilt=1980-01-01" -F "insuredHome.homeValue=400000" -F "insuredHome.liabilityLimit=2500000" \
  -F "insuredHome.dwellingType=STANDALONE" -F "insuredHome.heatingType=GAS" -F "insuredHome.locationType=URBAN" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1400" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["John Smithless"]}/autoquote" \
  -F "driverAge=25" -F "insuredAutomobile.vehicleMake=Honda" -F "insuredAutomobile.vehicleModel=Civic" \
  -F "insuredAutomobile.vehicleManufactureDate=2018-01-01" -F "insuredAutomobile.numberofAccidents=1" \
  -F "basePremium=750" -F "taxRate=0.15" -F "totalPremium=1600" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Mike Tenant"]}/autopolicy" \
  -F "driverAge=35" -F "insuredAutomobile.vehicleMake=Subaru" -F "insuredAutomobile.vehicleModel=Impreza" \
  -F "insuredAutomobile.vehicleManufactureDate=2017-01-01" -F "insuredAutomobile.numberofAccidents=1" \
  -F "basePremium=750" -F "taxRate=0.15" -F "totalPremium=1580" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Mike Tenant"]}/homequote" \
  -F "insuredHome.dateBuilt=2005-01-01" -F "insuredHome.homeValue=310000" -F "insuredHome.liabilityLimit=1800000" \
  -F "insuredHome.dwellingType=BUNGALOW" -F "insuredHome.heatingType=GAS" -F "insuredHome.locationType=URBAN" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1270" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["John Smithless"]}/homepolicy" \
  -F "insuredHome.dateBuilt=1995-01-01" -F "insuredHome.homeValue=275000" -F "insuredHome.liabilityLimit=1500000" \
  -F "insuredHome.dwellingType=BUNGALOW" -F "insuredHome.heatingType=ELECTRIC" -F "insuredHome.locationType=RURAL" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1200" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Rep Man"]}/autopolicy" \
  -F "driverAge=45" -F "insuredAutomobile.vehicleMake=Chevy" -F "insuredAutomobile.vehicleModel=Malibu" \
  -F "insuredAutomobile.vehicleManufactureDate=2010-01-01" -F "insuredAutomobile.numberofAccidents=3" \
  -F "basePremium=750" -F "taxRate=0.15" -F "totalPremium=2200" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Hayden Stone"]}/homepolicy" \
  -F "insuredHome.dateBuilt=1996-01-01" -F "insuredHome.homeValue=318443" \
  -F "insuredHome.liabilityLimit=1500000" \
  -F "insuredHome.dwellingType=BUNGALOW" \
  -F "insuredHome.heatingType=ELECTRIC" \
  -F "insuredHome.locationType=URBAN" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1345.09" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Hayden Blake"]}/homepolicy" \
  -F "insuredHome.dateBuilt=1994-01-01" -F "insuredHome.homeValue=277121" \
  -F "insuredHome.liabilityLimit=2000000" \
  -F "insuredHome.dwellingType=STANDALONE" \
  -F "insuredHome.heatingType=OIL" \
  -F "insuredHome.locationType=RURAL" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1376.08" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Jordan Stone"]}/autopolicy" \
  -F "driverAge=30" -F "insuredAutomobile.vehicleMake=Chevy" \
  -F "insuredAutomobile.vehicleModel=Focus" \
  -F "insuredAutomobile.vehicleManufactureDate=2012-01-01" \
  -F "insuredAutomobile.numberofAccidents=0" \
  -F "basePremium=750" -F "taxRate=0.15" -F "totalPremium=1443.76" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Taylor Dale"]}/homequote" \
  -F "insuredHome.dateBuilt=1992-01-01" -F "insuredHome.homeValue=377583" \
  -F "insuredHome.liabilityLimit=1500000" \
  -F "insuredHome.dwellingType=BUNGALOW" \
  -F "insuredHome.heatingType=OIL" \
  -F "insuredHome.locationType=URBAN" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1256.04" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Skylar Dale"]}/homequote" \
  -F "insuredHome.dateBuilt=1996-01-01" -F "insuredHome.homeValue=291499" \
  -F "insuredHome.liabilityLimit=1500000" \
  -F "insuredHome.dwellingType=BUNGALOW" \
  -F "insuredHome.heatingType=GAS" \
  -F "insuredHome.locationType=URBAN" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1445.57" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Casey Harper"]}/homequote" \
  -F "insuredHome.dateBuilt=1998-01-01" -F "insuredHome.homeValue=268660" \
  -F "insuredHome.liabilityLimit=1500000" \
  -F "insuredHome.dwellingType=BUNGALOW" \
  -F "insuredHome.heatingType=ELECTRIC" \
  -F "insuredHome.locationType=RURAL" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1489.15" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Hayden Fox"]}/autopolicy" \
  -F "driverAge=28" -F "insuredAutomobile.vehicleMake=Ford" \
  -F "insuredAutomobile.vehicleModel=Civic" \
  -F "insuredAutomobile.vehicleManufactureDate=2015-01-01" \
  -F "insuredAutomobile.numberofAccidents=0" \
  -F "basePremium=750" -F "taxRate=0.15" -F "totalPremium=1373.46" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Riley Gray"]}/homequote" \
  -F "insuredHome.dateBuilt=1990-01-01" -F "insuredHome.homeValue=369079" \
  -F "insuredHome.liabilityLimit=2500000" \
  -F "insuredHome.dwellingType=STANDALONE" \
  -F "insuredHome.heatingType=ELECTRIC" \
  -F "insuredHome.locationType=URBAN" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1302.65" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Morgan Sage"]}/homequote" \
  -F "insuredHome.dateBuilt=1999-01-01" -F "insuredHome.homeValue=279344" \
  -F "insuredHome.liabilityLimit=2000000" \
  -F "insuredHome.dwellingType=BUNGALOW" \
  -F "insuredHome.heatingType=ELECTRIC" \
  -F "insuredHome.locationType=RURAL" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1200.09" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null

curl -s -X POST "$API_URL/token/${TOKENS["Reese Harper"]}/homepolicy" \
  -F "insuredHome.dateBuilt=1991-01-01" -F "insuredHome.homeValue=369876" \
  -F "insuredHome.liabilityLimit=2000000" \
  -F "insuredHome.dwellingType=BUNGALOW" \
  -F "insuredHome.heatingType=OIL" \
  -F "insuredHome.locationType=RURAL" \
  -F "basePremium=500" -F "taxRate=0.15" -F "totalPremium=1674.37" \
  -F "startDate=2025-04-16" -F "endDate=2026-04-16" -F "activeStatus=1" > /dev/null


echo "Quotes and policies submitted."
echo "Done seeding!"
