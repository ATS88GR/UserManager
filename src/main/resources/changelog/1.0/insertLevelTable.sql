--liquibase formatted sql
--changeset ART:1.0-02

INSERT INTO user_levels
(leveldescr)
VALUES
     ('amatueur'),
     ('phd candidate'),
     ('phd');