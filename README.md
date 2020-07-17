# TestHarnessAPI

REST API Endpoints:

http://35.244.107.6/restapi/api/order?customerNo=12345

curl -X GET \
  'http://35.244.107.6/restapi/api/order?customerNo=12345' \
  -H 'authorization: Basic dXNlcjp1c2Vy' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 7d5622e3-6834-180e-b9d1-95fcccdd2de8'
  
  Step 1 - Clone git : https://github.com/vaneet392003/TestHarnessAPI.git
  Step 2 - install maven
  Step 3 - run mvn clean
  Step 4 - run mvn install
  Step 5 - Deploy the .war file in Web Server (eg. tomcat)
  
