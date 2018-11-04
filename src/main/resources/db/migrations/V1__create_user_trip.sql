CREATE TABLE user (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    username VARCHAR(255),
                    password VARCHAR(255),
                    authorities VARCHAR(255),
                    PRIMARY KEY (id)
);

CREATE TABLE trip (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    destination VARCHAR(255),
                    start_date DATE,
                    end_date DATE,
                    comment VARCHAR(1000),
                    user_id BIGINT NOT NULL,
                    PRIMARY KEY (id),
                    FOREIGN KEY (user_id) REFERENCES user(id)
);