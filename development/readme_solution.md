#### Candidate: Andres Urrego
##### Important thoughts DE challenge

Here below developers and colleagues will be able to find more granular information about how the API code breaks down and additionally a basic test plan to validate the outcomes from the whole methods implemented ont he core class.
From the DevOps perspective and guaranting the CI/CD , it's also included the docker file that compiles the entire API in a docker image ready to go for consuption.

##### How to run the code

A quick intro about how to execute each of the expected deliverables is listed below:

* Execute image:
The file `Dockerfile` contains the info required to deploy a docker image based in Ubuntu 18:04 , afterwards install python version 3.7 as required along with the `requirements.txt` file which contains a list of packages and versions oimplemented during the development phase.
In order to compile the Docker file it's required to copy the content of the folder development into the executable environment. Ideally before launch the command `sudo docker build -t sophi:de .` verify you are set on the path:

```
sophi-onboarding-de/
|  +--development/  <----- copy this folder on the executable environment to build the image
|  -----Dockerfile
|  +----content
|
```

Ideally you should have just the folder `content` and the `Dockerfile`.

* Consume the API:
Once the image has been built, it will be possible to mount the image in a container from where automoatically the API will be up and running waiting for consumption. please perform the command `sudo docker run -d -p 5000:5000 sophi:de`. You will received as return message the docker id that will mean the provisioning of a container has been completed.
Now it's will be simple just one previous validation step to confirm the container is running with the command `sudo docker ps`. Finally, open you local browser and hit: **localhost:5000**. If everything is correct you will see the outcome as **[{'autor': 'Gary Smith', 'total_score': 47488.0, 'date_submitted': '2018-02-28T03:10:02Z'}, {'autor': 'Weiwei Li', 'total_score': 47488.0, 'date_submitted': '2018-02-28T03:10:02Z'}, {'autor': 'DANIEL LEBLANC', 'total_score': 4248930.0, 'date_submitted': '2018-02-29T06:14:05Z'}]**

##### Code distribution

The API has been built in a main class that contains the whole operationality of the pipeline to later return the expected outcome. Additoinally we have some other classes to support the publication, errors, and unit test. more details about these objects in the follow bullets:

* Publisher - **api_endpoint.py** : This is the main [Flask](https://flask.palletsprojects.com/en/1.1.x/) file that contains a simple/functional class to send back the outcome via REST request and interact with the backend of the solution.
* Error handler - **errors.py** : As part of the development schema, I have included a separate `Error class` to handle better the exception and messages to be reported via the front end.
* Unit test - **coreunittest.py** : As part of the development best practices, it's also included a set of unit test for the methods used oin the core logic class. Here we validate that all the outcomes are set as expected and not null values come as result.

* Core pipeline - **dataprocessor.py** : This file refereces the main class of the process and the methods implemented to parse, clean and prepare the result expected from the front-end. This class has the capacity of reading the C SV file, filter the columns required in the expected shape and return back the string result towards the publisher file.

* Dockerfile : This is the image definition for running the entire solution, OS , Python version, packages and required files in the execution environment.


##### Testing - unittest

In order to perform some insightful test around the API , I have included three main test pointing the methods from the core class. Every test validates the outcome along with the expected type object to feed the front-end.

* test_engagement_time : validates the calculation of the engagement time following the porposed formula
* test__shapereturnmessage : verifies the return of a list for the autors and the scores sorted in desc.
* test_readingcontent : Validates the entire pipeline due to this is the initial and startup method.
