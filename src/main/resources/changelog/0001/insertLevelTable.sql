--liquibase formatted sql
--changeset ART:0001-02

INSERT INTO user_levels
(leveldescr)
VALUES
     ('amatueur'),
     ('phd candidate'),
     ('phd');