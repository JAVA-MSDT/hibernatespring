

CREATE TABLE user_table (
  user_id BIGSERIAL NOT NULL PRIMARY KEY,
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL,
  email varchar(45) NOT NULL
)