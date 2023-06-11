# School Management System

1. Install mysql Database https://www.dataquest.io/blog/install-mysql-windows/
2. Install MySQL Workbench
3. Go to command prompt and run command to start mysql Db: services start mysql
4. Open MySQL Workbench and connect to mysql local DB
5. After connecting to DB run following 

    create database school;
    use school;

    create table student(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    fname varchar(50), 
    mname varchar(50), 
    lname varchar(50), 
    cnic int, 
    semester varchar(10),  
    contactNum int
    );


    create table employee(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    fname varchar(50), mname varchar(50), lname varchar(50), 
    dateOfJoining varchar(50), 
    semester varchar(10),  
    contactNum int
    );

6. Run MainMenu.java file
7. Once testing is completed then go to command prompt and run command to stop mysql Db: services stop mysql
