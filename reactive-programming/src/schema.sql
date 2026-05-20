CREATE TABLE book_details
(
    book_id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_name        VARCHAR(255),
    book_description TEXT,
    book_publisher   VARCHAR(255),
    book_author      VARCHAR(255)
);