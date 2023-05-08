-- My database. Soccer competitions. October 2022 from scratch.
-- Asked tutor for help with my ER model. done.


DROP TABLE IF EXISTS competition;

CREATE TABLE competition (
    compId int(11) NOT NULL PRIMARY KEY,
    name varchar(50),
    gender varchar(50),
    age varchar(50),
    level varchar(50)
);


DROP TABLE IF EXISTS team;

CREATE TABLE team (
    teamId int(11) NOT NULL PRIMARY KEY,
    teamName varchar(50),
    coach varchar(50),
    gender varchar(50),
    age varchar(50),
    home_location varchar(50),

    playerIdCaptain int(11) NOT NULL,



    compId int(11) NOT NULL
    
);


DROP TABLE IF EXISTS matches;

CREATE TABLE matches (
    matchId int(11) NOT NULL PRIMARY KEY,
    date varchar(50),
    time varchar(50),
    location varchar(50), 

    refereeId int(11) NOT NULL,
    

    compId int(11) NOT NULL
    

    );


DROP TABLE IF EXISTS referee;

CREATE TABLE referee (
    refereeId int(11) NOT NULL PRIMARY KEY,
    name varchar(50)
);


DROP TABLE IF EXISTS players;

CREATE TABLE players (
    playerId int(11) NOT NULL PRIMARY KEY,
    name varchar(50),
    address varchar(50),
    gender varchar(50),
    bDate varchar(50),

    teamId int(11) NOT NULL
    
);


DROP TABLE IF EXISTS gaurdians;

CREATE TABLE gaurdians (

    playerId int(11) NOT NULL,
    

    name varchar(50),
    mobile varchar(50),


    CONSTRAINT pk_gaurdians PRIMARY KEY (playerId, name)
);


DROP TABLE IF EXISTS score;

CREATE TABLE score (
    
    matchId int(11) NOT NULL,
    

    teamId int(11) NOT NULL,
    

    points_this_match int(11),


    CONSTRAINT pk_score PRIMARY KEY (matchId, teamId)

);


DROP TABLE IF EXISTS medicalCondition;

CREATE TABLE medicalCondition (

    playerId int(11) NOT NULL,
    medCondition varchar(50),

    
     

    
    CONSTRAINT pk_medicalCondition PRIMARY KEY (playerId, medCondition)
    
);

ALTER TABLE team
ADD CONSTRAINT fk_team_playerIdCaptain FOREIGN KEY (playerIdCaptain) REFERENCES players(playerId),
ADD CONSTRAINT fk_team_compId FOREIGN KEY (compId) REFERENCES competition(compId)
;

ALTER TABLE matches
ADD CONSTRAINT fk_matches_refereeId FOREIGN KEY (refereeId) REFERENCES referee(refereeId),
ADD CONSTRAINT fk_matches_compId FOREIGN KEY (compId) REFERENCES competition(compId)
;

ALTER TABLE players
ADD CONSTRAINT fk_players_teamId FOREIGN KEY (teamId) REFERENCES team(teamId)
;

ALTER TABLE score
ADD CONSTRAINT fk_score_matchId FOREIGN KEY (matchId) REFERENCES matches(matchId),
ADD CONSTRAINT fk_score_teamId FOREIGN KEY (teamId) REFERENCES team(teamId)
;

ALTER TABLE medicalCondition
ADD CONSTRAINT fk_medicalCondition_playerId FOREIGN KEY (playerId) REFERENCES players(playerId)
;