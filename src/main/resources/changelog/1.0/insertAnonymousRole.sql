--liquibase formatted sql
--changeset ART:1.0-03

INSERT INTO user_roles
(roledescr)
VALUES
     ('anonymous');