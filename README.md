# Description
Simple API Rest to consult and fetch specific data from [an API of ice and fire](https://anapioficeandfire.com).
Since multiple calls to different external services need to be done, simultaneous calling were implemented in the service to
improve the response time, in addition to Web Server Caching.

## Data
The service will provide you data of all houses limited to their url, name, region and current lord in json format.

```json
[{"url":"https://www.anapioficeandfire.com/api/houses/1",
  "name":"House Algood",
  "region":"The Westerlands",
  "currentLord":null},
  {"url":"https://www.anapioficeandfire.com/api/houses/2",
    "name":"House Allyrion of Godsgrace",
    "region":"Dorne",
    "currentLord": {"url":"https://www.anapioficeandfire.com/api/characters/298",
      "name":"Delonne Allyrion",
      "gender":"Female"}},
  "..."
]
```

# Quick start

## Prerequisites
- Java JDK 17 installed in your machine.
  
An executable JAR were build to run the application as a single command.

1. Open a terminal and move to the folder where the JAR where downloaded and run the following command:
```bash
java -jar GOTproject-0.0.1-SNAPSHOT.jar
```
Now the server will be running in http://localhost:8081.

2. To get the data just go to the endpoint http://localhost:8081/houses.

And that's it... For now.