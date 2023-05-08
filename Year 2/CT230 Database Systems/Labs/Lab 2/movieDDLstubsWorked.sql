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
  dfName , varchar(50)
  dSurname , varchar(50)
);


-- if table already exists, delete
DROP TABLE IF EXISTS movie;

# TO DO: add Primary Key and missing data types to the movie table

-- movie(mID, title, relYear, category, runTime, studioName, description, rating)
CREATE TABLE movie (
  mID int(11) NOT NULL,
  title varchar(100),
  relYear,
  category,
  runTime,
  studioName varchar(100),
  description,
  rating decimal(4,2),
);

-- if table already exists, delete
DROP TABLE IF EXISTS genres;

# TO DO: add Primary and Foreign Keys to table genres

-- genres(movieID, genre)
CREATE TABLE genres (
  movieID int(11) NOT NULL,
  genre varchar(50) NOT NULL,
);

-- if table already exists, delete
DROP TABLE IF EXISTS directs;

# TO DO: create table directs 

-- directs(movieID, directorID)



-- if table already exists, delete
DROP TABLE IF EXISTS stars;

# TO DO: create table stars
-- stars(movieID, actorID)

