--liquibase formatted sql
--changeset ART:0001-01

INSERT INTO user_roles
(roledescr)
VALUES
     ('system admin'),
     ('moderator'),
     ('user');