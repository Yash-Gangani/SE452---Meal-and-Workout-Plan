# SE452 Object-Oriented Enterprise Applications

## Depaul University / Spring 2024 / Group 6
> **Run on local machine.**
>
> **Execute:** java -jar /target/project-[version number]-SNAPSHOT.jar
>
> **Dev endpoint:** https://localhost/8001
>
> **Prod endpoint:** https://localhost/8011
>
> **H2 Console (dev environment only):** /h2-console
>
> **API Documentation:** /swagger-ui-custom.html


### Project Overview: 
#### Meal and Workout Planning App Intended Scope:
- The app is preloaded with meals, workouts, meal plans, and workout plans
- Users can select a predefined meal plan or workout plan
- Users can add/delete meals and workouts to be stored with their profile
- Users can create/edit/delete meal plans and workout plans
- Users can change their current meal plan or workout plan
- Admins have full CRUD access to the predefined data


## Project Members

| Member | Area |
| -- | -- | 
| Yash Gangani | |
| Shireesha Hanmantharaopet | MealPlan package and test packages |
| Saad Mansuri | |
| John Smillie | - Person and Meal packages - full CRUD operations with UI<br/> - Person/Meal test packages<br/> - diagrams<br/> - data.sql for testing<br/> - H2 database setup and connection<br/> - Oracle database setup and connection<br/> - Seeded Oracle production DB predefined objects </br> - GitHub Actions integration and maven.yml <br/> - Logback.xml file integration<br/> - config files and settings: dev and prod<br/> - OpenAPI integration<br/> - Thymeleaf integration<br/> - UI Admin Dashboard: add/edit/delete users and meals<br> - Person and Meal template pages and static index.html utilizing Bootstrap CSS</br> - Javadocs |


<br/>


## Decision Made
| # | Area  | Decision | Reason | 
| -- | -- | -- | -- |
| 1 | IDE | VS Code |Easy to use and install extensions.| 
| 2 | Back-End Design  | Spring Boot Java | Easy to use and offers greater flexibility than it’s counterparts |
| 3 | Dependency Management | Maven | Widespread usage and simple configuration. |
| 4 | In-memory database | H2 | Simple volatile storage for the development environment |
| 5 | Logging | Logback | Quick upstart (built into the Spring Web dependency) |
| 6 | Automated start / stop actions | data.sql and auto create / drop | Create and drop tables with seeded data when running in development environment |
| 7 | Non-volatile persistence | Oracle | CSC453 Oracle DB still available. Compatibility with JPA annoatations |
| 8 | UI | Thymeleaf |  Inclusive front-end engine for Spring Boot |
| 9 | Build and Deploy | Github Actions | CI with build analysis for each push to the repo |
| 10 | Documentation | Spring OpenAPI  </br> Javadocs | Runtime publishing of REST API documentation.</br> For developer knowledge of classes and uses. |


<br>

## Implementation Table
| **Key Features** | **Implemented** |
| --| -- |
| The database is initialized with preset meals | yes (JS) |
| A web endpoint is available as a dashboard for administrators | yes (JS)|
| An admin can add/edit/delete users| yes (JS)|
| An admin can add/edit/delete meals| yes (JS) |
| An admin can add meals to the user's meal list | yes (JS) |

<br/>

## Conflict Resolution
We will be using Discord for communication.

<br/>

## Communication Mechanism
We will meet every Friday 5pm to 8pm on Discord, and in future, planning to meet at Loop Library.

<br/>

<br/>

