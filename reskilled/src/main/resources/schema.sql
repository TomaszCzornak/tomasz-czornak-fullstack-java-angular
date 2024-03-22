CREATE TABLE candidate (
                           id       bigint       NOT NULL AUTO_INCREMENT,
                           recruitment_id       bigint UNIQUE,
                           user_per_candidate  bigint NOT NULL,
                           email    varchar(255) NOT NULL UNIQUE,
                           PRIMARY KEY (id)
);

CREATE TABLE candidate_job_list (
                                    candidate_id  bigint NOT NULL,
                                    job_list_id   bigint NOT NULL
);

CREATE TABLE job (
                     id             bigint       NOT NULL AUTO_INCREMENT,
                     salary         bigint,
                     city           varchar(255),
                     currency       varchar(255) NOT NULL CHECK (currency IN ('EUR', 'PLN', 'USD', 'PLN')),
                     title          varchar(255),
                     PRIMARY KEY (id)
);

CREATE TABLE job_candidates (
                                candidates_id bigint NOT NULL,
                                job_id        bigint NOT NULL
);

CREATE TABLE job_skill (
                           job_id     bigint NOT NULL,
                           skill_id   bigint NOT NULL
);

CREATE TABLE recruitment (
                             candidate_id bigint UNIQUE,
                             id           bigint       NOT NULL AUTO_INCREMENT,
                             job_id       bigint UNIQUE,
                             PRIMARY KEY (id)
);

CREATE TABLE resetoperations (
                                 users        bigint,
                                 id           uuid         NOT NULL,
                                 createdate   timestamp DEFAULT current_timestamp,
                                 uuid         varchar(255),
                                 PRIMARY KEY (id)
);

CREATE TABLE skill (
                       id        bigint       NOT NULL AUTO_INCREMENT,
                       name      varchar(255),
                       PRIMARY KEY (id)
);

CREATE TABLE user_per_candidate (
                                    id           bigint       NOT NULL AUTO_INCREMENT,
                                    email        varchar(255),
                                    PRIMARY KEY (id)
);

CREATE TABLE users (
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
