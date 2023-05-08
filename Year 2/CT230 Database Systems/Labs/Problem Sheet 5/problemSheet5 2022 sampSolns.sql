-- CT230 Problem Sheet 5 Sample Solutions
-- Josephine Griffith, 2022


-- 1. Using a join approach, list the genres of the movie with the title ‘Belfast’

SELECT genre
FROM   genres INNER JOIN movie ON mID = movieID
WHERE  title = 'Belfast';

-- 2. Using an explicit inner join, list the names and release years, of all movies that star the actor ‘Saoirse Ronan’

SELECT title, relYear
FROM   movie INNER JOIN stars ON mID = movieID 
             INNER JOIN actor ON actorID = aID
WHERE  fName = 'Saoirse' AND surname = 'Ronan';

-- 3. Using an implicit inner join, list the names of all actors in the film with the title ‘Spiderman No way home’.

SELECT fName, surname
FROM   movie,  stars, actor
WHERE  title = 'Spiderman No way home' AND
       mID = movieID AND 
       actorID = aID;

-- 4.	Using a join, list the titles of all films, release year and description of all films which are directed by the director ‘Anthony Russo’.

SELECT  title, relYear, description
FROM    movie INNER JOIN directs ON mID = movieID 
              INNER JOIN director on dID = directorID
WHERE   dfName = 'Anthony' AND 
        dSurname = 'Russo';

-- 5.	List the title of all films which were directed by Quentin Tarantino or stared Quentin Tarantino as an actor.

(SELECT title
 FROM   movie INNER JOIN stars ON mID = movieID 
              INNER JOIN actor ON actorID = aID
 WHERE  fName = 'Quentin' AND 
        surname = 'Tarantino')
UNION 
(SELECT title
 FROM   movie INNER JOIN directs ON mID = movieID 
              INNER JOIN director on dID = directorID
 WHERE dfName = 'Quentin' AND 
       dSurname = 'Tarantino');


-- 6.	List the name of actors who starred in the same movies as the actor Brie Larson.

SELECT DISTINCT fName, surname
FROM   stars, actor
WHERE  actorID = aID AND fName != 'Brie' AND surname != 'Larson' AND 
       movieID  IN
	(SELECT movieID
	 FROM   stars, actor
	 WHERE  actorID = aID AND fName = 'Brie' AND surname = 'Larson');


-- 7.	For each director, who has directed more than one movie, list the name of the director and the number of movies they have directed.

SELECT    dfName, dSurname, COUNT(*) AS 'num Movies Directed'
FROM      director INNER JOIN directs 
          ON  dID = directorID
GROUP BY  dfName, dSurname
HAVING    COUNT(*) > 1;

-- 8 Find the number of movies starred in by each actor, listing the actor name and the number of movies they starred in, for all actors who stared in more than two movies in the database.

SELECT   fName, surname, COUNT(*) AS 'num Movies Starred'
FROM     actor INNER JOIN stars ON actorID = aID
GROUP BY fName, surname
HAVING   COUNT(*) > 2;


-- 9 For each movie genre, list the number of movies with that genre, and the average rating received by movies of that genre, for genres that have more than 5 movies. 

SELECT   genre, COUNT(*) AS 'Num Movies', ROUND(AVG(rating),2) AS 'Avg Rating'
FROM     genres INNER JOIN movie 
         ON mID = movieID
GROUP BY genre
HAVING   COUNT(*) > 5;


-- 10 -- For the movie with title 'Don't Look Up' list the directors and actors of the movie

-- implicit join solution
SELECT CONCAT(fName, ' ', surname) AS Actor, 
       CONCAT(dfName, ' ', dSurname) AS Director
FROM   stars, actor, movie, directs, director
WHERE  actorID = aID AND 
       stars.movieID = mID AND
       movie.mID = directs.movieID AND
       dID = directorID AND
       title = 'Don''t Look Up';
	   
-- explicit join solution

SELECT CONCAT(fName, ' ', surname) AS Actor, 
       CONCAT(dfName, ' ', dSurname) AS Director

FROM   stars INNER JOIN actor
                   ON actorID = aID
             INNER JOIN movie
                   ON  stars.movieID = mID
             INNER JOIN directs
                   ON movie.mID = directs.movieID
             INNER JOIN director
                   ON dID = directorID
WHERE  
       title = 'Don''t Look Up';
	   