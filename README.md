# match-service
This is a Spring Boot microservice which runs on a docker container 
and performs fully CRUD operations to football/ basketball matches.
It comes with its own Postgres database(matches) consisting of two tables:
1. match(id, description, matchDate, matchTime, teamA, teamB, sport) 
2. match_odds(id, matchId, specifier, odd).
When creating a new match a new entry is saved at both tables accordingly. 
The match table one keeps data regarding the match and the match_odds table regarding the match's odds.

The two data tables are 1-1.

# Steps to run the program:
Download the project or clone the code to your favorite IDE (https://github.com/gdimitriou/match-service.git). 

Run: docker-compose up.

# Dependencies
1. Java 8
2. Docker
3. Maven
4. Postgres

# Endpoints:
The service comes with five endpoints:
1. GET api/v1/match/{id}
2. GET api/v1/match/getAll
3. POST api/v1/match
4. PUT api/v1/match/{id}
5. DELETE api/v1/match/{id}

SwaggerUI can be found at: http://localhost:8081/swagger-ui/

