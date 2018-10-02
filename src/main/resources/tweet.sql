
CREATE TABLE tweet (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  message VARCHAR(140),
  author  VARCHAR(100)
);

INSERT INTO tweet (message, author) VALUES ('hello world!', 'goobar');
INSERT INTO tweet (message, author) VALUES ('hello myWorld!', 'foobar');
INSERT INTO tweet (message, author) VALUES ('hello', 'hoobar');
