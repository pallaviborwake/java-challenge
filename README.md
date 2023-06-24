### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Whta I did
  - Add tests
    >>Add Junit testcase for Controller and Service class. Also check code covergae using 
     EcLEmma Java Code Coverage Tool.Used Juint5 and Mockito.
  - Change syntax
  - Protect controller end points
    >>Implemented Role base authorization using spring security with JWT
- Add caching logic for database calls
    >> Implemented it using EH cache.
- Improve doc and comments
    >> Added swagger properly with comments.
- Fix any bug you might find
    >> Fixed issues in employee controller and service get, delete, update api and handled the 
      exceptions  
- Added Spring Global Exception handler and Api Exception,EmployeeNotFoundException,
  EmployeeService application exception (exception/*). 
- Used constants and ErrorCode Enum (constants/*)
- Created standard api response  (helper/ResponseProvider)
- Separated models for controller and entity for DAO (model/* , entity/*)
- Implemented swagger properly
- Implemented Login contoller end points for Role base athorizantion. 
  (Controller/LoginController,Service/UserDetailsServiceImpl,repositories/UserDataRespository,
  entities/UserData)

  #### What I would have done if I had more time
- Would have configured role based spring security with database and created one more Endpoint 
  for user registration 
- Would have Implemented logging using spring AOP
- Would have implemented spring actuator for checking health and monitoring
- Would have modified the code to pass code quality as per sonar standred
  #### How to access Apis
  #### Step 1:Login
  - For Login has two role
    >>1)Role    :ADMIN / USER                       
    >>  Username:axaUser                                
    >>  Password:password                              
    >>  Authority:ADMIN /USER                            

    >>2)Role    :USER<br>
    >>  Username:otherAxa<br>
    >>  Password:pass123<br>
    >>  Authority:USER<br>

    >>Description:<br>
    >>1)Admin: Has all permission to do all employee operation.<br>
    >>2)User:  Has only create and update permission<br>
  -  Login with any above credentials and get the token
   - URL: http://localhost:8080/api/v1/login
   - Request body 
    >> {<br>
        "authority": "USER",<br>
        "password": "password",<br>
        "role": "USER",<br>
       "username": "axaUser"<br>
       }<br>
    - Response Body <br>
    >> {<br>
         "token": <br>
         "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheGFVc2VyIiwiUm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MR<br>
          V9BRE1JTiJdLCJleHAiOjE2ODc2MDI3NzIsImlhdCI6MTY4NzU4NDc3Mn0.XHDf95OpoR_i_wWpdS0CgG- <br>
         QfabMuhd7nOT83ZQ67BYSHn0ERo1oJ8tVtXuZNtOqaUJAuaiKfi9YVT7vyVlSmw"<br>
        }<br>
- Add token in the Authorization header for accessing all APIs as following
    >>Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheGFVc2VyIiwiUm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MR<br>
          V9BRE1JTiJdLCJleHAiOjE2ODc2MDI3NzIsImlhdCI6MTY4NzU4NDc3Mn0.XHDf95OpoR_i_wWpdS0CgG-<br> 
         QfabMuhd7nOT83ZQ67BYSHn0ERo1oJ8tVtXuZNtOqaUJAuaiKfi9YVT7vyVlSmw<br>
         
   **Note :Do not forget to add "Bearer" before token to access api


#### Your experience in Java

- I have 3 years experience in Java and I started to use Spring Boot from last year
- Using Spring Boot i have devlope API also write JUnit for the devlope api.
- Use sonarQube to maintain code quality and covergae.
  
