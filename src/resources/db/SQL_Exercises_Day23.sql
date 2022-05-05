
-- SELECT ALL non USA customers
SELECT * FROM customers c
WHERE Country != 'USA' ;

-- SELECT ONLY German customers
SELECT * FROM customers c 
WHERE Country = 'Germany' ; 

-- SELECT employees (different table) who are sales agents

SELECT * FROM employees e 
WHERE Title LIKE "%SALES%AGENT%";

-- SELECT unique billing countries from invoices table

SELECT DISTINCT BillingCountry 
FROM invoices i
ORDER BY BillingCountry ASC; 

SELECT * FROM customers;


SELECT * FROM tracks t 
ORDER BY Name DESC
LIMIT 25
OFFSET 10;



SELECT a.Title , a2.Name FROM albums a 
JOIN artists a2 
ON a.ArtistId = a2.ArtistId ; -- this will be wild

SELECT * FROM tracks t ;

SELECT * FROM tracks t 
ORDER BY UnitPrice DESC;

--before creating or refreshing a view you
-- need to drop the old one
DROP VIEW IF EXISTS track_view;
CREATE VIEW track_view
AS
SELECT t.TrackId,
a.Title Album, 
a2.Name Artist, 
t.Name SongName,
g.Name Genre,
t.UnitPrice,
t.Milliseconds,
mt.Name Media
FROM tracks t
JOIN albums a 
ON t.AlbumId = a.AlbumId
JOIN artists a2 
ON a.ArtistId = a2.ArtistId
JOIN genres g 
ON t.GenreId = g.GenreId
JOIN media_types mt
ON t.MediaTypeId = mt.MediaTypeId ;

-- ONCE I have created a view I can query it just like a table
-- think of it as a virtual table

SELECT tv.Album,
tv.Artist,
tv.SongName,
tv.Genre,
tv.UnitPrice,
tv.Milliseconds,
tv.Milliseconds / 1000 as Seconds,
tv.Milliseconds / (1000*60) as Minutes,
tv.Media
FROM track_view tv 
WHERE tv.Media NOT LIKE '%video%'
ORDER BY tv.Milliseconds DESC
LIMIT 10;




