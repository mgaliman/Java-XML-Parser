CREATE DATABASE MyJavaDatabase
GO
USE MyJavaDatabase
GO

CREATE TABLE Movie
(
	IDMovie INT PRIMARY KEY IDENTITY,
	Title NVARCHAR(300),
	PublishedDate NVARCHAR(90),
	Description NVARCHAR(900),
	OriginalTitle NVARCHAR(300),
	PersonID int FOREIGN KEY REFERENCES Person(IDPerson),
	Duration INT,
	Genre NVARCHAR(300),		
	PicturePath NVARCHAR(90),
	Link NVARCHAR(300),
	StartDate NVARCHAR(90)	
)
GO

CREATE TABLE Person
(
	IDPerson INT PRIMARY KEY IDENTITY,
	FirstName NVARCHAR(300),
	LastName NVARCHAR(300)
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
	@Duration INT,
	@Genre NVARCHAR(300),	
	@PicturePath NVARCHAR(90),
	@Link NVARCHAR(300),
	@StartDate NVARCHAR(90),
	@ID INT OUTPUT
AS 
BEGIN 
	INSERT INTO Movie VALUES(@Title, @PublishedDate, @Description, @OriginalTitle, @PersonID, @Duration, @Genre, @PicturePath, @Link, @StartDate)
	SET @ID = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateMovie
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(90),
	@Description NVARCHAR(900),
	@OriginalTitle NVARCHAR(300),
	@PersonID INT,
	@Duration INT,
	@Genre NVARCHAR(300),		
	@PicturePath NVARCHAR(90),
	@Link NVARCHAR(300),
	@StartDate NVARCHAR(90),
	@IDMovie INT
	 
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
		PicturePath = @PicturePath,
		Link = @Link,
		StartDate = @StartDate
				
	WHERE 
		IDMovie = @IDMovie
END
GO

CREATE PROCEDURE deleteMovie
	@IDMovie INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Movie
	WHERE 
		IDMovie = @IDMovie
END
GO

CREATE PROCEDURE selectMovie
	@IDMovie INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Movie
	WHERE 
		IDMovie = @IDMovie
END
GO

CREATE PROCEDURE selectMovies
AS 
BEGIN 
	SELECT * FROM Movie
END
GO

--PERSON PROCEDURES--
---------------------
CREATE PROCEDURE createPerson
	@FirstName NVARCHAR(300),
	@LastName NVARCHAR(300),
	@ID INT OUTPUT
AS 
BEGIN 
	INSERT INTO Person VALUES(@FirstName, @LastName)
	SET @ID = SCOPE_IDENTITY()
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
				
	WHERE 
		IDPerson = @IDPerson
END
GO

CREATE PROCEDURE deletePerson
	@IDPerson INT	 
AS 
BEGIN 
	DELETE  
	FROM 
		Person
	WHERE 
		IDPerson = @IDPerson
END
GO

CREATE PROCEDURE selectPerson
	@IDPerson INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Person
	WHERE 
		IDPerson = @IDPerson
END
GO

CREATE PROCEDURE selectPersons
AS 
BEGIN 
	SELECT * FROM Person
END
GO