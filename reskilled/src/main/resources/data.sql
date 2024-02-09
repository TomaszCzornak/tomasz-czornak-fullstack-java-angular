insert into Job(title, city, salary, currency)
values('Software Engineer', 'Warsaw', 12000, 'PLN');

insert into Job(title, city, salary, currency)
values('Data Scientist', 'Kraków', 15000, 'PLN');

insert into Job(title, city, salary, currency)
values ('Front-end Developer', 'Wroclaw', 11000, 'PLN');

insert into Job(title, city, salary, currency)
values('DevOps Engineer', 'Gdansk', 13000, 'PLN');



INSERT INTO Skill(name) VALUES ('Python');
INSERT INTO Skill(name) VALUES ('SQL');
INSERT INTO Skill(name) VALUES ('Docker');
INSERT INTO Skill(name) VALUES ('R');
INSERT INTO Skill(name) VALUES ('Machine Learning');
INSERT INTO Skill(name) VALUES ('JavaScript');
INSERT INTO Skill(name) VALUES ('React');
INSERT INTO Skill(name) VALUES ('HTML/CSS');
INSERT INTO Skill(name) VALUES ('AWS');
INSERT INTO Skill(name) VALUES ('Kubernetes');
INSERT INTO Skill(name) VALUES ('Terraform');

INSERT INTO job_skill(job_id, skill_id) VALUES ('1', 1);
INSERT INTO job_skill(job_id, skill_id) VALUES ('1', 2);
INSERT INTO job_skill(job_id, skill_id) VALUES ('1', 3);

INSERT INTO job_skill(job_id, skill_id) VALUES ('2', 1);
INSERT INTO job_skill(job_id, skill_id) VALUES ('2', 4);
INSERT INTO job_skill(job_id, skill_id) VALUES ('2', 5);

INSERT INTO job_skill(job_id, skill_id) VALUES ('3', 6);
INSERT INTO job_skill(job_id, skill_id) VALUES ('3', 7);
INSERT INTO job_skill(job_id, skill_id) VALUES ('3', 8);

INSERT INTO job_skill(job_id, skill_id) VALUES ('4', 9);
INSERT INTO job_skill(job_id, skill_id) VALUES ('4', 10);
INSERT INTO job_skill(job_id, skill_id) VALUES ('4', 11);


