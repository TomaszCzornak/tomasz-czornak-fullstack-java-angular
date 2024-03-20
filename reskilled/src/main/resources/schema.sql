CREATE TABLE Job
(
    id       INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(255) NOT NULL,
    city     VARCHAR(255) NOT NULL,
    salary   BIGINT       NOT NULL CHECK (salary > 0),
    currency VARCHAR(255) NOT NULL,
    primary key (id)
);

CREATE TABLE Skill
(
    id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE job_skill
(
    job_id   INT NOT NULL,
    skill_id INT NOT NULL,
    PRIMARY KEY (job_id, skill_id),
    FOREIGN KEY (job_id) REFERENCES Job (id),
    FOREIGN KEY (skill_id) REFERENCES Skill (id)
);

CREATE TABLE users
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    uuid       VARCHAR,
    created_at VARCHAR(255) NOT NULL,
    updated_at VARCHAR(255),
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    isLock     boolean DEFAULT true,
    isEnabled  boolean DEFAULT false,
    role       varchar      not null,
    PRIMARY KEY (id)
);

create table user_per_candidate
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

create table resetoperations
(
    id         varchar primary key,
    users      varchar REFERENCES users (id),
    createdate timestamp DEFAULT current_timestamp,
    uuid       varchar
);

create table Candidate
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email varchar(255) not null unique,
    user_per_candidate varchar not null,
    foreign key (user_per_candidate) references users (id),
    primary key (id)
);

CREATE TABLE Recruitment
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    job_id       INT,
    candidate_id INT,
    FOREIGN KEY (job_id) REFERENCES Job (id),
    FOREIGN KEY (candidate_id) REFERENCES Candidate (id),
    primary key (id)
);

CREATE TABLE job_recruitment
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    job_id INT NOT NULL,
    recruitment_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (job_id) REFERENCES Job (id),
    FOREIGN KEY (recruitment_id) REFERENCES Recruitment (id)
);
