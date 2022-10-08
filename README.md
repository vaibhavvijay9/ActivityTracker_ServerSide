# ActivityTracker_ServerSide
A Maven project with APIs developed using Jersey for RESTful Web Services for an Activity Tracker web application. It uses MySQL as database. It is deployed at Heroku and MySQL is hosted at RemoteMysql.com

Steps to make changes and deploy -

. Make changes (Eclipse Neon or any IDE)
. Right Click project in Eclipse and select 'Run As' --> 'Maven Build'(2nd one)
. In goal write - clean install
. see console and it should display 'BUILD SUCCESS'
. git add .
. git commit -m "msg"
. git push origin master

==> Your updated code will be deployed on heroku.

Test by accessing URL - https://activitytrackerapp.herokuapp.com/api/hello
