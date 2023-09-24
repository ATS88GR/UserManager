--liquibase formatted sql
--changeset ART:1.0-01

INSERT INTO user_roles
(roledescr)
VALUES
     ('system admin'),
     ('moderator'),
     ('user');