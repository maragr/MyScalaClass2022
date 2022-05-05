SELECT * FROM artists a 
--WHERE Name LIKE '%V%"
WHERE Name GLOB '*Sau*'
LIMIT 20;

--CREATE (in CRUD terminology)
INSERT INTO artists (Name)
VALUES ('Valdis Saulespurēns');

UPDATE artists
SET name = 'Rūta Saulespurēna'
WHERE ArtistId = 277;

UPDATE artists 
SET name = 'Valdis Saulespurēns'
WHERE Name GLOB '*Sau*';
 -- so I skip my own name
 -- so limit and order options might not be enabled in your SQLite
 -- https://www.sqlite.org/lang_update.html

-- DELETE - Delete in CRUD
-- so check first with SELECT to see what you want to delete
-- Syntax is very similar
SELECT * FROM artists a 
WHERE Name GLOB '*Sau*';




DELETE FROM artists 
WHERE ArtistId = 277;


SELECT * FROM albums a2 
ORDER BY AlbumId DESC 
LIMIT 5;
--Insert INTO albums
INSERT INTO albums 
(Title, ArtistId)
VALUES ('My amazing Xmas album', 276);

SELECT * FROM tracks t 
ORDER BY TrackId DESC 
LIMIT 10;

INSERT INTO tracks 
(Name, AlbumId, MediaTypeId, 
GenreId, Composer, Milliseconds,
Bytes, UnitPrice)
VALUES ('Sitting under a tree',348,1,
22, 'Valdis S',181000,1359000, 2.99);

INSERT INTO tracks 
(Name, AlbumId, MediaTypeId, 
GenreId, Composer, Milliseconds,
Bytes, UnitPrice)
VALUES ('Sitting on a tree',358,1,
22, 'Valdis S',181300,1359000, 4.99);

UPDATE tracks 
SET AlbumId = 348
WHERE TrackId = 3505;

--TODO
--CREATE YOUR OWN genre of music

INSERT INTO genres (Name) 
VALUES ('Rap Folk');

--CREATE artist Yourself or someone real or imagined
INSERT INTO artists (Name)
VALUES ('Folk Vibes');
--CREATE album

INSERT INTO albums (Title, ArtistId)
VALUES ('Out there behind the forest',
(SELECT ArtistId 
FROM artists a2 
WHERE a2.Name = 'Folk Vibes'));

SELECT ArtistId 
FROM artists a2 
WHERE a2.Name = 'Folk Vibes';
--CREATE 2 tracks from that album that use your own genre of music

INSERT INTO tracks (Name, AlbumId, MediaTypeId, Milliseconds, UnitPrice, GenreId)
VALUES ('Red Poppies', (SELECT AlbumId FROM albums WHERE Title = 'Out there behind the forest'), 1, 120895, 0.86, 26),
('Blue-eyed', (SELECT AlbumId FROM albums WHERE Title = 'Out there behind the forest'), 1, 129330, 0.86, 26);

SELECT * FROM tracks t 
ORDER BY TrackId  DESC 
LIMIT 10;


SELECT GenreId FROM genres WHERE Name = 'Opera';

UPDATE tracks 
SET GenreId = (SELECT GenreId FROM genres WHERE Name = 'Opera')
WHERE Name = 'Red Poppies';

--UPDATE one of the tracks to be opera genre
--DELETE the opera track 
DELETE FROM tracks 
WHERE Name = 'Red Poppies';
-- as long as there are no other 'Red Poppies' songs..

--SELECT show your track joining it together with genre, album and artist
--like we did in a previous lecture

SELECT t.Name Song, 
a.Title Album, 
a2.Name Artist, 
g.Name Genre,
t.Milliseconds ,
mt.Name Media,
t.UnitPrice 
FROM tracks t
JOIN genres g 
ON t.GenreId = g.GenreId 
JOIN albums a 
ON t.AlbumId = a.AlbumId 
JOIN artists a2 
ON a.ArtistId = a2.ArtistId 
JOIN media_types mt 
ON t.MediaTypeId = mt.MediaTypeId 
WHERE t.Name = 'Blue-eyed';


-- SO LEFT JOIN will fill NULL values on the right side table
-- if no match is found
SELECT
   artists.ArtistId, 
   albums.AlbumId
FROM
   artists
LEFT JOIN albums ON
   albums.ArtistId = artists.ArtistId
ORDER BY
   artists.ArtistId;
   
  
  --https://www.sqlitetutorial.net/sqlite-group-by/
  
SELECT
	tracks.albumid,
	COUNT(trackid) trackCount,
	SUM(UnitPrice) albumPrice,
	a.Title Album,
	a2.Name Artist
FROM
	tracks
JOIN albums a 
ON a.AlbumId = tracks.albumid 
JOIN artists a2 
ON a2.ArtistId = a.ArtistId 
GROUP BY
	tracks.albumid
HAVING albumPrice > 25
ORDER BY trackCount DESC ; 

SELECT country, COUNT(CustomerId) Customers
FROM customers
GROUP BY country
ORDER BY customers DESC;

SELECT t.GenreId, 
COUNT(t.TrackId) TracksPerGenre, 
SUM(t.UnitPrice ) SalesByGenre,
g2.Name Genre
FROM tracks t 
JOIN genres g2 
ON t.GenreId = g2.GenreId 
GROUP BY t.GenreId 
ORDER BY SalesByGenre DESC;