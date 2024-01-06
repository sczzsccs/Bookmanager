DROP DATABASE IF EXISTS book_manager; -- 만약 market_db가 존재하면 우선 삭제한다.
CREATE DATABASE book_manager;

use book_manager;

CREATE TABLE Members (
    MemberID VARCHAR(255) PRIMARY KEY,
    Name VARCHAR(255),
    PhoneNum VARCHAR(20),
    Email VARCHAR(255),
    Level INT, 

	UNIQUE (MemberID)
);

CREATE TABLE Authors (
    AuthorName VARCHAR(255) UNIQUE PRIMARY KEY
);
CREATE TABLE Publishers (
    PublisherName VARCHAR(255) UNIQUE PRIMARY KEY
);

CREATE TABLE Books (
    BookID INT PRIMARY KEY,
    Title VARCHAR(255),
    AuthorName VARCHAR(255),
    PublisherName VARCHAR(255),
    ISBN VARCHAR(20),
    Available BOOLEAN,
    
    FOREIGN KEY (AuthorName) REFERENCES Authors(AuthorName) ON DELETE CASCADE,
    FOREIGN KEY (PublisherName) REFERENCES Publishers(PublisherName) ON DELETE CASCADE
);
CREATE INDEX idx_BookID ON Books(BookID);
CREATE INDEX idx_Available ON Books(Available);

CREATE TABLE Rentals (
    RentalID INT PRIMARY KEY,
    MemberID VARCHAR(255),
    BookID INT,
    RentalDate DATE,
    ReturnDate DATE,
    BookStatus BOOLEAN,

	FOREIGN KEY (MemberID) REFERENCES Members(MemberID) ON DELETE CASCADE,
    FOREIGN KEY (BookID) REFERENCES Books(BookID) ON DELETE CASCADE,
    FOREIGN KEY (BookStatus) REFERENCES Books(Available) ON DELETE CASCADE
);