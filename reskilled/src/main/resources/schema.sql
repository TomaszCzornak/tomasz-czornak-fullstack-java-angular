CREATE TABLE Job
(
    id     INT   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(255) NOT NULL,
    city     VARCHAR(255) NOT NULL,
    salary   BIGINT       NOT NULL CHECK (salary > 0),
    currency VARCHAR(255) NOT NULL
);

CREATE TABLE Skill
(
    id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE job_skill
(
    job_id   INT NOT NULL,
    skill_id INT      NOT NULL,
    PRIMARY KEY (job_id, skill_id),
    FOREIGN KEY (job_id) REFERENCES Job (id),
    FOREIGN KEY (skill_id) REFERENCES Skill (id)
);

CREATE TABLE users (
                       id VARCHAR(255) NOT NULL,
                       uuid VARCHAR,
                       created_at VARCHAR(255) NOT NULL,
                       updated_at VARCHAR(255),
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       isLock    boolean DEFAULT true,
                       isEnabled boolean DEFAULT false,
                       PRIMARY KEY (id)
);

create table resetoperations
(
    id         varchar primary key,
    users      varchar REFERENCES users (id),
    createdate timestamp DEFAULT current_timestamp,
    uuid        varchar
);
