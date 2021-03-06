USE master
GO

DROP DATABASE MyJavaDatabase
GO

CREATE DATABASE MyJavaDatabase
GO

USE MyJavaDatabase
GO

CREATE TABLE Person
(
	IDPerson INT PRIMARY KEY IDENTITY,
	FirstName NVARCHAR(300),
	LastName NVARCHAR(300)
)
GO

CREATE TABLE Movie
(
	IDMovie INT PRIMARY KEY IDENTITY,
	Title NVARCHAR(300),
	PublishedDate NVARCHAR(90),
	Description NVARCHAR(900),
	OriginalTitle NVARCHAR(300),
	PersonID int FOREIGN KEY REFERENCES Person(IDPerson),
	Duration NVARCHAR(50),
	Genre NVARCHAR(300),
	PicturePath NVARCHAR(300)
)
GO

CREATE TABLE MovieActor
(
	IDMovieActor INT PRIMARY KEY IDENTITY,
	MovieID int FOREIGN KEY REFERENCES Movie(IDMovie),
	PersonID int FOREIGN KEY REFERENCES Person(IDPerson)
)
go

CREATE TABLE FavoritePerson
(
	IDFavoritePerson int CONSTRAINT PK_FavoritePerson PRIMARY KEY IDENTITY,
	PersonID int CONSTRAINT FK_FavoriteActor_Actor FOREIGN KEY REFERENCES Person(IDPerson) NOT NULL
)
GO

CREATE TABLE Account
(
	IDAccount INT PRIMARY KEY IDENTITY,
	IsAdmin bit not null,
	Username NVARCHAR(300),
	Pass NVARCHAR(300)
)
GO

--MOVIE PROCEDURES--
---------------------
CREATE PROCEDURE createMovie
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(90),
	@Description NVARCHAR(900),
	@OriginalTitle NVARCHAR(300),
	@PersonID INT,
	@Duration NVARCHAR(50),
	@Genre NVARCHAR(300),
	@PicturePath NVARCHAR(300),
	@ID INT OUTPUT
AS 
	BEGIN 
		INSERT INTO Movie VALUES(@Title, @PublishedDate, @Description, @OriginalTitle, @PersonID, @Duration, @Genre, @PicturePath)
		SET @ID = SCOPE_IDENTITY()
	END
GO

CREATE PROCEDURE updateMovie
	@IDMovie INT,
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(90),
	@Description NVARCHAR(900),
	@OriginalTitle NVARCHAR(300),
	@PersonID INT,
	@Duration NVARCHAR(50),
	@Genre NVARCHAR(300),
	@PicturePath NVARCHAR(300)
	 
AS 
	BEGIN 
		UPDATE Movie SET 
			Title = @Title,
			PublishedDate = @PublishedDate,		
			Description = @Description,
			OriginalTitle = @OriginalTitle,
			PersonID = @PersonID,
			Duration = @Duration,
			Genre = @Genre,
			PicturePath = @PicturePath
				
		WHERE IDMovie = @IDMovie
	END
GO

CREATE PROCEDURE deleteMovie
	@IDMovie INT	 
AS 
	BEGIN 
		DELETE FROM MovieActor WHERE MovieID = @IDMovie
		DELETE FROM Movie WHERE IDMovie = @IDMovie
	END
GO

CREATE PROCEDURE selectMovie
	@IDMovie INT
AS 
	BEGIN 
		SELECT m.IDMovie, m.Title, m.PublishedDate, m.Description, m.OriginalTitle, m.PersonID, m.Duration, m.Genre, m.PicturePath, p.IDPerson, p.LastName, p.FirstName FROM Movie as m	
		INNER JOIN Person as p on m.PersonID = p.IDPerson
		WHERE IDMovie = @IDMovie
	END
GO

CREATE PROCEDURE selectMovies
AS
BEGIN
	SELECT m.IDMovie, m.Title, m.PublishedDate, m.Description, m.OriginalTitle, m.Duration, m.Genre, m.PicturePath, m.PersonID, p.FirstName, p.LastName
	
	FROM Movie AS m
	FULL JOIN Person AS p
		ON m.PersonID = p.IDPerson	
	WHERE m.IDMovie IS NOT NULL and p.IDPerson IS NOT NULL
END
GO

CREATE PROCEDURE deleteMovies
AS 
	BEGIN
		DELETE FROM MovieActor
		DELETE FROM Movie
		DELETE FROM Person		
	END
go

--PERSON PROCEDURES--
---------------------
CREATE PROCEDURE createPerson
	@FirstName NVARCHAR(300),
	@LastName NVARCHAR(300),
	@ID INT OUTPUT
AS 
	BEGIN 
		IF exists (select * from Person as p where p.FirstName = @FirstName and p.LastName = @LastName)
			BEGIN
				select @ID = p.IDPerson from Person as p where p.FirstName = @FirstName and p.LastName = @LastName
			END
		ELSE
			BEGIN
				INSERT INTO Person VALUES(@FirstName, @LastName)
				SET @ID = SCOPE_IDENTITY()
			END		
	END
GO

CREATE PROCEDURE updatePerson
	@FirstName NVARCHAR(300),
	@LastName NVARCHAR(300),
	@IDPerson INT
	 
AS 
	BEGIN 
		UPDATE Person SET 
			FirstName = @FirstName,
			LastName = @LastName
				
		WHERE IDPerson = @IDPerson
	END
GO

CREATE PROCEDURE deletePerson
	@IDPerson INT	 
AS 
	BEGIN 
		DELETE FROM FavoritePerson WHERE PersonID = @IDPerson
		DELETE  FROM Person	WHERE IDPerson = @IDPerson
	END
GO

CREATE PROCEDURE selectPerson
	@IDPerson INT
AS 
	BEGIN 
		SELECT * FROM Person WHERE IDPerson = @IDPerson
	END
GO

CREATE PROCEDURE selectPersons
AS 
	BEGIN 
		SELECT * FROM Person
	END
GO

CREATE PROCEDURE AddFavoritePerson
	@ActorID int
AS
BEGIN
	INSERT INTO FavoritePerson(PersonID)
	VALUES(@ActorID)
END
GO

CREATE PROCEDURE SelectFavoritePersons
AS
	BEGIN
		SELECT p.IDPerson, p.FirstName, p.LastName
		FROM FavoritePerson AS fp
		INNER JOIN Person AS p
		ON p.IDPerson = fp.PersonID
	END
GO

CREATE PROCEDURE RemoveFavoritePersons
	@ActorID int
AS
	BEGIN
		DELETE FROM FavoritePerson
		WHERE FavoritePerson.PersonID = @ActorID
	END 
GO
--ACCOUNT PROCEDURES--
----------------------
CREATE PROCEDURE createAccount
	@Username NVARCHAR(300),
	@Pass NVARCHAR(300),
	@ID INT OUTPUT
AS 
	BEGIN 
		INSERT INTO Account VALUES(0,@Username, @Pass)
		SET @ID = SCOPE_IDENTITY()
	END
GO

CREATE PROCEDURE selectAccount
	@Username NVARCHAR(300)
AS
	BEGIN
		SELECT * FROM Account WHERE Username = @Username
	END
GO

--MOVIE ACTOR PROCEDURES--
--------------------------
CREATE PROCEDURE createMovieActor
	@MovieID int,
	@PersonID int,
	@ID INT OUTPUT
AS 
	BEGIN
		INSERT INTO MovieActor values (@MovieID, @PersonID) 
		SET @ID = SCOPE_IDENTITY()
	END		
GO

CREATE PROCEDURE selectMovieActors
	@MovieID int
AS
	BEGIN
		SELECT Person.IDPerson, Person.FirstName, Person.LastName FROM MovieActor INNER JOIN Person on Person.IDPerson = MovieActor.PersonID
		WHERE MovieActor.MovieID = @MovieID
	END
GO

insert into Account values (1,'admin','admin')