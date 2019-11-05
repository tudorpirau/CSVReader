# CSVReader
1. Presentation.
This is a simple application which is reading data from a CSV file. Data is filtered, in order to get ride of bad records and then imported to SQL Lite in memory database. Bad records are exported in a new file.
<br>
2.Used technologies.
<br>
Spring Boot, Hibernate, Maven. For importing CSV data is used a library from Apache. Data is inserted in a database using a Hibernate Entity. It is used a batch insert to improve the performance as there are very many records in CSV file.
<br>
3. Steps to set up and run the project.
<br>
To set up and run this project it is required to install Spring Tool Suite or Eclipse. To run one of these you need Java development Kit installed on your machine. Next, it is neccessary to clone this repository in your IDE and then to import this project. Make sure all maven dependecies are updated in your project. The database details are located in application.properties file. You can change username and password for your database there. Now, you can run it. Open your browser and go to index, will be opened the web page of the application. There you have to input directory where you CSV file is located and after clicking submit, you will see a brief status of imported data. In the case you CSV file is corrupt or is located in a different location, you will see an error message.
<br>
4. Screenshots:
<br>
https://imgur.com/Quhmu6H
https://imgur.com/pkhUvv2
<br>
5. Maven dependencies:
<br>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<br>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
<br>
<!-- Dependencies for Hibernate and ORM  -->  
<dependency>
<groupId>org.xerial</groupId>
<artifactId>sqlite-jdbc</artifactId>
<version>3.25.2</version>
</dependency>
<br>
<dependency>
<groupId>com.h2database</groupId>
<artifactId>h2</artifactId>
<version>1.3.156</version>
</dependency>
<br>
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-orm</artifactId>
</dependency>
<br>
<dependency>
<groupId>org.hibernate</groupId>
<artifactId>hibernate-entitymanager</artifactId>
<version>3.6.0.Final</version>
</dependency>
<br>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<br>
<!-- Dependencies for SQLite -->
<dependency>
<groupId>org.xerial</groupId>
<artifactId>sqlite-jdbc</artifactId>
<version>3.8.11.2</version>
</dependency>
<br>
<dependency>
<groupId>net.kemitix</groupId>
<artifactId>sqlite-dialect</artifactId>
<version>0.1.0</version>
</dependency>  
<br>	  
<dependency>
<groupId>log4j</groupId>
<artifactId>log4j</artifactId>
<version>1.2.16</version>
</dependency>
<br>
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-csv</artifactId>
<version>1.5</version>
</dependency>
<br>		
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-test</artifactId>
<scope>test</scope>
</dependency>
<br>
Good luck!
