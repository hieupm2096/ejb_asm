CREATE DATABASE DbEJBASM
GO
USE DbEJBASM

-- Tables
CREATE TABLE Reader(
	_id INT IDENTITY PRIMARY KEY,
	_name VARCHAR(50) NOT NULL,
	_email VARCHAR(100) NOT NULL,
	_phone VARCHAR(20) NOT NULL,
	_dob DATE NOT NULL,
	_gender BIT NOT NULL,
	_address VARCHAR(200) NOT NULL,
	_status BIT NOT NULL DEFAULT 1,
	_code VARCHAR(8)
)

CREATE TABLE Librarian(
	_id INT IDENTITY PRIMARY KEY,
	_username VARCHAR(50) UNIQUE NOT NULL,
	_password VARCHAR(50) NOT NULL,
	_name VARCHAR(50) NOT NULL,
	_email VARCHAR(100) NOT NULL,
	_phone VARCHAR(20) NOT NULL,
	_dob DATE NOT NULL,
	_address VARCHAR(200) NOT NULL,
	_gender BIT NOT NULL,
	_status BIT NOT NULL DEFAULT 1
)

CREATE TABLE Category(
	_id INT IDENTITY PRIMARY KEY,
	_name VARCHAR(50) NOT NULL UNIQUE,
	_status BIT NOT NULL DEFAULT 1
)

CREATE TABLE Author(
	_id INT IDENTITY PRIMARY KEY,
	_name VARCHAR(50) NOT NULL UNIQUE,
	_status BIT DEFAULT 1
)

CREATE TABLE Publisher(
	_id INT IDENTITY PRIMARY KEY,
	_name VARCHAR(50) NOT NULL UNIQUE,
	_status BIT DEFAULT 1
)

CREATE TABLE Books(
	_id INT IDENTITY PRIMARY KEY,
	_name VARCHAR(200) NOT NULL,
	_year INT,
	_price FLOAT NOT NULL,
	_publisher INT NOT NULL,
	CONSTRAINT book_publisher_fk FOREIGN KEY (_publisher) REFERENCES Publisher(_id),
	_date_import DATE NOT NULL DEFAULT GETDATE(),
	_librarian_id INT NOT NULL,
	CONSTRAINT book_librarian_fk FOREIGN KEY (_librarian_id) REFERENCES Librarian(_id),
	_status BIT NOT NULL DEFAULT 1
)

CREATE TABLE Book(
	_id INT IDENTITY PRIMARY KEY,
	_code VARCHAR(8) UNIQUE,
	_info INT NOT NULL,
	CONSTRAINT book_books_fk FOREIGN KEY (_info) REFERENCES Books(_id),
	_status BIT DEFAULT 1
)
GO

CREATE TABLE Books_Author(
	_books_id INT NOT NULL,
	CONSTRAINT books_author_books_fk FOREIGN KEY (_books_id) REFERENCES Books(_id),
	_author_id INT NOT NULL,
	CONSTRAINT books_author_author_fk FOREIGN KEY (_author_id) REFERENCES Author(_id),
	CONSTRAINT books_author_pk PRIMARY KEY(_books_id, _author_id)
)

CREATE TABLE Books_Category(
	_books_id INT NOT NULL,
	CONSTRAINT books_cate_books FOREIGN KEY (_books_id) REFERENCES Books(_id),
	_cate_id INT NOT NULL,
	CONSTRAINT books_cate_cate FOREIGN KEY (_cate_id) REFERENCES Category(_id),
	CONSTRAINT books_cate_pk PRIMARY KEY(_books_id, _cate_id)
)
GO
-- Trigger
CREATE OR ALTER TRIGGER generate_book_code
ON Book
AFTER INSERT
AS
BEGIN
	DECLARE @number INT, @code VARCHAR(8)
	SET @number = (SELECT MAX(_id) FROM dbo.Book)
	IF @number IS NULL SET @code = 'B0000001'
	ELSE
		BEGIN
			SET @code = CONCAT('B', RIGHT('000000' + CONVERT(VARCHAR(7), @number), 7))
		END
	UPDATE dbo.Book SET _code = @code WHERE _id = @number
END
GO

CREATE OR ALTER TRIGGER generate_reader_code
ON Reader
AFTER INSERT
AS
BEGIN
	DECLARE @number INT, @code VARCHAR(8)
	SET @number = (SELECT MAX(_id) FROM dbo.Reader)
	IF @number IS NULL SET @code = 'R0000001'
	ELSE
		BEGIN
			SET @code = CONCAT('R', RIGHT('000000' + CONVERT(VARCHAR(7), @number), 7))
		END
	UPDATE dbo.Reader SET _code = @code WHERE _id = @number
END
GO
-- Procedures
CREATE OR ALTER PROC get_librarian
@username VARCHAR(50), @password VARCHAR(50)
AS
	SELECT * FROM dbo.Librarian
	WHERE _username = @username AND _password = @password
GO

CREATE OR ALTER PROC get_reader
@id INT = NULL
AS
	SELECT * FROM dbo.Reader
	WHERE @id IS NULL OR _id = @id
GO

CREATE PROC insert_reader
@name VARCHAR(50),
@email VARCHAR(100),
@phone VARCHAR(20),
@dob DATE,
@gender BIT,
@address VARCHAR(200)
AS
	INSERT INTO dbo.Reader(_name, _email, _phone, _dob, _gender, _address)
	VALUES (@name, @email, @phone, @dob, @gender, @address)
GO

CREATE OR ALTER PROC update_reader
@code VARCHAR(8),
@name VARCHAR(50),
@email VARCHAR(100),
@phone VARCHAR(20),
@dob DATE,
@gender BIT,
@address VARCHAR(200),
@status BIT
AS
	UPDATE dbo.Reader
	SET _name = @name,
		_email = @email,
		_phone = @phone,
		_dob = @dob,
		_gender = @gender,
		_address = @address,
		_status = @status
	WHERE _code = @code
GO

CREATE OR ALTER PROC delete_reader
@id INT
AS
	DELETE FROM dbo.Reader WHERE _id = @id
GO



