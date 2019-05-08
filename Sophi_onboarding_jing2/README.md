# Sophie-onboarding-Jing
Goal:  read the input file and present the data in one REST endpoint

## Operations

GET /api/articles/getarticles

## Prerequisites

- Git

- Docker **(optional)**

- Java 8 SDK
  
- Scala 2.12.x SDK

- SBT (Scala Build Tool)
 
- Intellij IDEA Community Edition

- REST API client


## Structure

- `app/controllers/Articles`: control module --  action function getArticles call models ArticleReader.getArticlesJson function

- `app/models/ArticleReader`: utility classe to build a list of Articles
 
- `app/models/ArticleEntry`: case classes and objects used for represent an ArticleEntry in the list

- `app/conf/routes`: routes file to route different REST api call from different endpoint

- `test/controllers/ArticlesSpec`: specs2 test case to Fake HTTP request /api/articles/getarticles and verify the response status and content

- `test/models/ArticleEntrySpec`: specs2 test case to unitTest the functions in ArticleEntry model

- `test/models/ArticleReaderSpec`: specs2 test case to unitTest the functions in ArticleReaderSpec model

- `public/images/RESTAPIOutput.png`: screenshot of RESTAPI response

- `public/images/sample-input.csv.pnd`: screenshot of sample-input.csv file

- `public/images/unittestResult.txt`: the unit test result report

- `public/resources/sample-input.csv`: sample input csv file



## Behaviour

- uses Play Frame work to build RESTfull api

- when client issue HTTP request "GET /api/articles/getarticles" on REST API client 

- server side will open the csv file <Project Root>/public/resources/sample-input.csv

- parse it, add computer column: Competition Score: "Total Engagement Time in seconds" * ("Popularity index" + "Readership index")

- sorted descending by "Competition Score" and "Date Submitted"

- and parse it back to Json format and send back to client


## Installation & Running

#### use activate disc to deploy the project


#### Run configuration


#### Application configuration


#### Docker packaging


#### Run docker container