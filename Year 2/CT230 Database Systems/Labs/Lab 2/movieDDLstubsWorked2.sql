# CT230 Movie Database 
# September 2022
# DDL Code - partially complete 


-- if table already exists, delete
DROP TABLE IF EXISTS actor;

# TO DO: Nothing - table actor does not need to be modified/updated

-- actor(aID, fName, surname, nationality, gender)
CREATE TABLE actor (
  aID int(11) NOT NULL PRIMARY KEY,
  fName varchar(50),
  surname varchar(50),
  nationality varchar(50),
  gender varchar(50)
);

-- if table already exists, delete
DROP TABLE IF EXISTS director;

# TO DO: add Primary Key and the missing data types to table director

-- director(dID, dfName, dSurname)
CREATE TABLE director (
  dID int(11) NOT NULL PRIMARY KEY,
  dfName varchar(50) ,
  dSurname varchar(50)
);


-- if table already exists, delete
DROP TABLE IF EXISTS movie;

# TO DO: add Primary Key and missing data types to the movie table

-- movie(mID, title, relYear, category, runTime, studioName, description, rating)
CREATE TABLE movie (
  mID int(11) NOT NULL PRIMARY KEY,
  title varchar(100),
  relYear int(11),
  category varchar(50) , 
  runTime int(11) ,
  studioName varchar(100),
  description varchar(300),
  rating decimal(4,2)
);

-- if table already exists, delete
DROP TABLE IF EXISTS genres;

# TO DO: add Primary and Foreign Keys to table genres

-- genres(movieID, genre)
CREATE TABLE genres (
  movieID int(11) NOT NULL,
  genre varchar(50) NOT NULL,
  PRIMARY KEY (movieID, genre)
);

-- if table already exists, delete
DROP TABLE IF EXISTS directs;

# TO DO: create table directs 

-- directs(movieID, directorID)
CREATE TABLE directs (
  movieID int(11) NOT NULL,
  directorID int(11) NOT NULL,
  PRIMARY KEY (movieID, directorID)
  );


-- if table already exists, delete
DROP TABLE IF EXISTS stars;

# TO DO: create table stars
-- stars(movieID, actorID)

CREATE TABLE stars (
  movieID int(11) NOT NULL,
  actorID int(11) NOT NULL,
  --PRIMARY KEY (movieID, actorID)
);


-- Alter genres table. TO add foreign key constraint.
ALTER TABLE genres
ADD CONSTRAINT genres_fk FOREIGN KEY (movieID) REFERENCES movie (mID)
;


-- Alter directs table. Add foreign key constraint, movieID and directorID.
ALTER TABLE directs
ADD CONSTRAINT directs_fk_movieID FOREIGN KEY (movieID) REFERENCES movie (mID) , 
ADD CONSTRAINT directs_fk_directorID FOREIGN KEY (directorID) REFERENCES director (dID)
;

-- Alter stars table. Add fk constraint, movieID and actorID.
ALTER TABLE stars
ADD CONSTRAINT stars_fk_movieID FOREIGN KEY (movieID) REFERENCES movie (mID), 
ADD CONSTRAINT stars_fk_actorID FOREIGN KEY (actorID) REFERENCES actor (aID)
;