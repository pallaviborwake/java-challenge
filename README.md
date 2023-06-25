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
    >>Added Junit testcase for Controller and Service class. Also check code covergae using 
     EcLEmma Java Code Coverage Tool.Used Juint5 and Mockito.Current code coverage is 60%
  - Change syntax
  - Protect controller end points
    >>Implemented with spring security with JWT (Also added roles ADMIN and USER)
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
- Addition to implemented role base access would have implement more in detail. For Example:only logged in user able to update own data etc
- Would have Implement logging using spring AOP
- Would have implement spring actuator for checking health and monitoring
- Would have modify the code to pass code quality as per sonar standred
- Would have implement testcase for other class apart from Controller and Service class
  
  ### How to access Apis
  #### Step 1:Login
  - For Login has two role
    >>1)Role    :ADMIN                   
    >>  Username:axaUser                                
    >>  Password:password                              

    >>2)Role    :USER<br>
    >>  Username:otherAxa<br>
    >>  Password:pass123<br>

      #### Description
         1)Admin: Has all permission to do all employee operation.
         2)User:  Has only create permission(only SaveAPI and this is for showcasing user role only.)
   -  Login with any above username and password and get the token
   - URL: http://localhost:8080/api/v1/login
   - Request body 
    >> {<br>
        "password": "password",<br>
       "username": "axaUser"<br>
       }<br>
    - Response Body <br>
    >> {<br>
         "token": <br> 
          "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheGFVc2VyIiwiUm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJleHAiOjE2ODc2MTkzNzgsIml<br>
           hdCI6MTY4NzYwMTM3OH0.kPWMIPW_ifc-p2CnNqOfSiLnDTrn2L7MJcVFll05xd-ZuWwii9yI2S7JXArV3Tytg-kMN-2c0bJqDBIg2vh4AA",<br>
       "username": "axaUser",<br>
       "role": "ROLE_ADMIN"<br>
     }<br>
     #### Step 2:Access other api
     - Add token in the Authorization header for accessing all APIs as following(Example)<br>
     >>Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJheGFVc2VyIiwiUm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MR<br>
          V9BRE1JTiJdLCJleHAiOjE2ODc2MDI3NzIsImlhdCI6MTY4NzU4NDc3Mn0.XHDf95OpoR_i_wWpdS0CgG-<br> 
         QfabMuhd7nOT83ZQ67BYSHn0ERo1oJ8tVtXuZNtOqaUJAuaiKfi9YVT7vyVlSmw<br>
         
     >>Note   
          1)Do not forget to add "Bearer " before token to access api. Add space after `Bearer`<br>
          2)In employee endpoints ignore the value of id while creating,updating<br>
          
    ### How to run Testcase 
     >> To Run TestCasein 2 way<br>
      1) Run with  `mvn test`  for starting the testcase <br>
      2) OR Run with  `mvn install` <br>
         *** Note:If got build error wile running testcase please build path.Maven Dependencies checkbox is checked or not.,br.
  
     #### Your experience in Java
    - I have 3 years experience in Java and I started to use Spring Boot from last year
    - Using Spring Boot I have devloped APIs and have written JUnit for them.
    - Used sonarQube to maintain code quality and covergae.
  
