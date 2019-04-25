1. Ideally I should have made a Maven project with all the Required dependencies (Jars with the Names and versions) for the Project, but for my Quick Testing I have added all the Required JArs to the Build Path. It's all about to make things work and then could make it more refined.

2. I have added two more records to the CSV file which I wanted to try for my Testing Purpose.(In one of the record I have duplicate Author Name, wanted to see if the set I am making is actually a set and stores only the Author Name only once)

3. I have modified the Total Engagement Time to be String from Duration.

4. Will be pasting the Screen shot of the JSON for all 4 Records in the CSV.

5. JSONLINT Tool was used to test if the JSON's are properly formed.

6. I haven't done any Exception Handling etc in the code, The whole purpose of making this work was to show the overall approach. 

7. One of the Problem which I will look into today/Tomorrow will be the JSON shows the AuthorName and FreeLancer Fields empty which is wrong.  I will try to look into it and make changes. I didn't find the right method to convert them into Array. sorry for that.

8. Currently the Test class is also under same folder(src) which is bad coding practice, it should have its own folder something like "test/scala/" etc. It's done to prove the code works with minimal efforts :)

9. There is a Testoutput File which has the CONSOLE output printed (FYI)


