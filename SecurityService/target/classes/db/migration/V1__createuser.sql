

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT NOT NULL,
   user_name VARCHAR(255) NULL,
   password VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   phone_number VARCHAR(255) NULL,
   `role` VARCHAR(255) NULL,
   active BIT(1) NOT NULL,
   expiry BIT(1) NOT NULL,
   block BIT(1) NOT NULL,
   CONSTRAINT pk_user PRIMARY KEY (id)
);