


[![Contributors][contributors-shield]][contributors-url]




<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="https://i.ibb.co/PFZ3hRm/LOGO-PPM.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">PPM project - 2019/2020</h3>

  <p align="center">
    Project made by IG4 Polytech Montpellier students 
    <br />

  </p>
</p>

<!-- ABOUT THE PROJECT -->
## About The Project

This project is an application for businesses, it aims to ensure the smooth running of all projects.
The application offers various features that will help managers to succeed in a project on time and efficiently.
The application offers the following features:
* a suggestion box
* a project planning: management of tasks, members, files, a chat system ...
* an announcement system
* a ticket system to inform the administrators of a possible problem

Each member of the group contributed to the realization of these functionalities, which were equally distributed.


### Built With
Pour faire ce projet nous avons utilisé les technologies suivantes :

* [SceneBuilder](https://gluonhq.com/products/scene-builder/)
* [Jdbc](https://dev.mysql.com/downloads/connector/j/)
* [Dropbox-API](https://www.dropbox.com/developers/documentation/http/overview)



<!-- GETTING STARTED -->
## Getting Started

To run the project on your computer you must do certain things

### Prerequisites

* JavaFX to show the interface
* Java JDK to run the project

### Use

1. You can just run the App.JAR to use the application

OR

1. Clone the repo

2. Put the project on Eclipse/Intellij Idea

3. Put the dropbox api KEY on the <strong>src/controller/Resource/DropBoxConnexion.java</strong>


4. Put the BD password on the <strong>src/DAO/MySqlConnector.java</strong>

4. Run the app from the IDE


## Contributors

* Thomas Faure
* Remi Salmi
* Guillaume Tessier
* Lauren Unquera

<!-- CONTACT -->
## Contact

thomas.faure05@gmail.com

Project Link: [https://github.com/Thomas-Faure/ProjetOOSE](https://github.com/Thomas-Faure/ProjetOOSE)


## The application choices
### Our choices
We chose to make one package per specialty, one for tests, one for dao, one for business logic, one for facades, one for controllers and one for views (UI), this allows us to structure our code well and to get less lost when we try to navigate.
In each of these packages, there is a package corresponding to each UseCase (for example a Task, an Announcement) which allows us to structure our code even more.
That way, when distributing the tasks, it is easier to target the area where we are going to work.

##### Buisness Logic Package :
Each Class inherits an abstract class, useful for polymorphism and allows to respect the D of the SOLID principle (Dependency inversion principle) which consists in depending on abstract classes rather than concrete classes.
Each concrete class therefore implements the methods of their abstract class.

##### DAO :
We also have abstract classes as for the buisnesslogic, it is here that the factory and the class to connect to the database are placed (all the classes which are in connection with the database will be in this package).

##### Test :
There are two packages:
- one for the buisness logic where we will perform tests on some classes of the buisness logic package.
- one for the DAO where we are going to carry out the tests on some classes from the DAO package.

##### UI :
It is here that are defined the classes corresponding to each view (one class per view) which will call an FXML file (similar to XML to represent a view in JavaFX) which corresponds to the view.
Controller :
each FXML file of the UI package contains a reference to a controller class, so we have one controller per view on our application (which are stored in a folder corresponding to the name of the UseCases ex: "Controller/Task/NomVueController.java").

##### Facade :
Each type of facade is arranged in a package corresponding to the name of the UseCase and have each an abstract class. These classes take the principle of the Design Pattern Facade which will be applied to our project.
These facades will process the calls made via the controls and will call certain methods from the different CADs if necessary.

### Problems
Some classes were difficult to implement and organize, we had at one point bad code implementations that were not optimized, we had to change that.
As JavaFX was a new technology for us, there were some functions for the controllers that we didn't plan to do at the beginning that were added (because of the lack of JavaFX skills at the beginning).
There were problems with page return and refresh. Indeed we had all planned a return on a specific page (in case of return or validation of a modification for example) but for the Task UseCase for example, it is possible that the return is done either on a sprint view or directly on the task lists page. When a task is modified, the modification must appear on the return page. For this, we have defined a new abstract class that inherits from GlobalUI (abstract class for our views) that will retain the controller associated to the view, the controller will also implement an interface named "IController" that contains an "update" function, so that for each return when we modify a task, we can go back to the previous view by updating its content. We didn't implement these interfaces on all views/controllers, because they don't necessarily require an update.


[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=flat-square
[contributors-url]: https://github.com/Thomas-Faure/ProjetOOSE/graphs/contributors

