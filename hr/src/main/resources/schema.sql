-- User Table
CREATE TABLE if not exists users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       uuid VARCHAR(255) NOT NULL,
                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       islock BOOLEAN DEFAULT FALSE,
                       isenabled BOOLEAN DEFAULT TRUE,
                       role VARCHAR(255) -- Replace ... with your actual role options
);


-- Candidate Table
CREATE TABLE if not exists candidate (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           users BIGINT NOT NULL,
                           FOREIGN KEY (users) REFERENCES users(id),
                           CONSTRAINT fk_candidate_user FOREIGN KEY (users) REFERENCES users(id) ON DELETE RESTRICT ON UPDATE CASCADE
);


-- Job Table
CREATE TABLE if not exists job (
                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                     title VARCHAR(255) NOT NULL,
                     city VARCHAR(255) NOT NULL,
                     salary BIGINT NOT NULL,
                     currency ENUM('USD', 'EUR', 'PLN')
                                        NOT NULL,
                     CONSTRAINT check_salary CHECK (salary > 0)
);

-- Skill Table
CREATE TABLE if not exists skill (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL
);


-- Job-Skill Relationship Table
CREATE TABLE IF NOT EXISTS job_skill (
                                         job_id BIGINT,
                                         skill_id BIGINT,
                                         PRIMARY KEY (job_id, skill_id),
                                         FOREIGN KEY (job_id) REFERENCES job(id),
                                         FOREIGN KEY (skill_id) REFERENCES skill(id)
);

-- ResetOperations Table
CREATE TABLE IF NOT EXISTS resetoperations (
                                  id UUID PRIMARY KEY,
                                  users BIGINT,
                                  createdate DATETIME,
                                  uuid VARCHAR(255),
                                  FOREIGN KEY (users) REFERENCES users(id)
);

-- Recruitment Table
CREATE TABLE IF NOT EXISTS recruitment (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             job_id BIGINT NOT NULL,
                             candidate_id BIGINT NOT NULL,
                             FOREIGN KEY (job_id) REFERENCES job(id) ON DELETE CASCADE,
                             FOREIGN KEY (candidate_id) REFERENCES candidate(id) ON DELETE CASCADE
);

