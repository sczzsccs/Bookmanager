-- 만약 market_db가 존재하면 우선 삭제한다.
DROP DATABASE IF EXISTS book_manager;
CREATE DATABASE book_manager;

use book_manager;

select *from authors;

select *from publishers;

select *from books;

select *from members;

select *from rentals;

select title, available, authors_entity_authors_name, publishers_entity_publishers_name from books
	where available = 0;