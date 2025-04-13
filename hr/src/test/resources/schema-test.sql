
CREATE TABLE IF NOT EXISTS user_per_candidate (
                                    id           bigint       NOT NULL AUTO_INCREMENT,
                                    email        varchar(255),
                                    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS candidate (
                           id BIGINT AUTO_INCREMENT,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           user_per_candidate BIGINT NOT NULL,
                           PRIMARY KEY (id),
                           CONSTRAINT fk_user_per_candidate FOREIGN KEY (user_per_candidate) REFERENCES user_per_candidate(id)
);


CREATE TABLE IF NOT EXISTS job (
                     id             bigint       NOT NULL AUTO_INCREMENT,
                     salary         bigint,
                     city           varchar(255),
                     currency       varchar(255) NOT NULL CHECK (currency IN ('EUR', 'PLN', 'USD', 'PLN')),
                     title          varchar(255),
                     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS candidates_jobs (
                                 candidate_id BIGINT,
                                 job_id BIGINT,
                                 PRIMARY KEY (candidate_id, job_id),
                                 CONSTRAINT fk_candidate FOREIGN KEY (candidate_id) REFERENCES candidate(id),
                                 CONSTRAINT fk_job FOREIGN KEY (job_id) REFERENCES job(id)
);

CREATE TABLE IF NOT EXISTS  job_skill (
                           job_id     bigint NOT NULL,
                           skill_id   bigint NOT NULL
);



CREATE TABLE IF NOT EXISTS  resetoperations (
                                 users        bigint,
                                 id           uuid         NOT NULL,
                                 createdate   timestamp DEFAULT current_timestamp,
                                 uuid         varchar(255),
                                 PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  skill (
                       id        bigint       NOT NULL AUTO_INCREMENT,
                       name      varchar(255),
                       PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS  users (
                       isenabled      boolean,
                       islock         boolean,
                       id             bigint       NOT NULL AUTO_INCREMENT,
                       created_at     varchar(255) NOT NULL,
                       email          varchar(255) NOT NULL UNIQUE,
                       first_name     varchar(255) NOT NULL,
                       last_name      varchar(255) NOT NULL,
                       password       varchar(255) NOT NULL,
                       role           varchar(255) CHECK (role IN ('USER', 'ADMIN')),
                       updated_at     varchar(255),
                       uuid           varchar(255),
                       PRIMARY KEY (id)
);
