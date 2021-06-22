# ClockApplication

## Description
This is an android clock application that shows time for the given list of cities. Data is stored in SQL Database and time is updated after every 2 seconds. Following are some of the main functionalities of application.

  1. Search City
  2. Pin on Main Screen
  3. Unpin from Main Screen
  4. See the Time of Each City
  

## Languages
### Front End
For UI Development XML tags are used for lists, buttons and cities. Following are screenshots of User Interface of application.

### Screen-1
This is the first screen showing the list of listies pinned by the user. It shows Country Flag, Time, and Short Description. By clicking the button on the bottom of the screen user will be directed to the second screen.

![Alt text](1.jpg?raw=true "Optional Title")

### Screen-2
This is the second screen. User can pin any city to the main screen and they can also unpin any city by unchecking the city. 

![Alt text](2.jpg?raw=true "Optional Title")

### Screen-3
User can search any city using the filter option and can check or uncheck it. 
![Alt text](3.jpg?raw=true "Optional Title")

### Back End
Backend of application is developed in java.

### Database
Database used in this application is SQL Lite local database. Application stores data in database, fetches if needed and it also updates data in case of any changes.

## Working
Application uses Recycler View to print the list of cities on both screens. For the Front End, XML is used and integrated with Java. With Recycler View there is view holder associated with each view for efficient processing.

## How to Run
To run this project you need Android Studio version 42. or earlier with gradle version 7.0.2. You can directly clone the project with version control and use it to add new functions or install it into you phone. 

If you face any difficulty understanding any part or running the project, feel free to reach me at naeem.akhtar.cs@gmail.com. Thanks.