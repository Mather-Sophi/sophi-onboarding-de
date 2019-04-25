# Hello!
We cannot wait to have you on-board in our team, but before that, we have designed a ticket, with exact similar structure of what we have in our Jira board for the team. This will help you to know more about us, too. You can imagine the future-you to take one or more of these tickets in every sprint.  
 
 
## Ticket name: SOPHI-777: Competition Scoreboard
 
### Background: 
There will be a writing competition held by The Globe and Mail before the new year for young journalism interns. We need to create a scoreboard to show the competition results. You will implement the API part that reads a file from our data-science scoring components. A frontend developer will pickup your work and implement the UI for it.
 
### Goal: 
Your project should read the input file and present the data in one REST endpoint.
 
### Expected Deliverables:
* One Rest Endpoint that represents total score of every single author in json format sorted descending by "Competition Score" and “Date Submitted”
* Set of unit-tests that proves validity of your code.
* A multi-stage Dockerfile that builds and runs your project
* Markdown documentation, describing the necessary things, i.e. How to run the code.
 
 
### Technical Notes:
* Your choice of Scala libraries would impress us. If you'd explain your reasoning the Markdown file, it would help us understand more. Please add libraries when only needed.
* The goal of your code is not be short, but is the coding style to be expressive and comprehensive enough in a functional way.
* Software design patterns, always and forever.
* Scaladocs are mandatory. Please have them in your code. You don’t need to generate the actual docs.
* You’re given a CSV (git splitted by comma) fie that might have unexpected values in _any_ of the fields These are the columns:
  * Article Name (String)
  * Date Submitted (Timestamp)
  * Article ID (uuid4)
  * Author(s) name (Set of Strings splitted by semi-column)
  * Freelancer/in-house/wire (List of Strings  splitted by semi-column)
  * Popularity index (Double)
  * Readership index (Double)
  * Total Engagement Time (Duration)

* Competition Score: Total Engagement Time in seconds * (Popularity index + Readership index)
* The person that runs your code (i.e. a platform engineer), only knows Docker. He attaches a Docker volume to your container to provide the input. He reads your Markdown documents to build and run your image.
* Please use Scala 2.12.6 and sbt version of your choice.
 
 
### Solution Submission:
* Please fork this repository as a _private_ repository. It is important to keep your solution in a _private_ repository to avoid other potential candidates accessing your solutions.
* Whenever feeling comfortable, you can create a pull request in _your_ repository and add *szareiangm* as the reviewer.
* When you submit your pull request, *szareiangm* will add some other team members. The team will review your code. We try to keep our comments limited.
* You have the choice of addressing the comments and squashing your commits into one. The way you handle the comments is important to us.
 
### Good luck!