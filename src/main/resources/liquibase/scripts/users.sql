--liquibase formatted sql

--changeset myakovlev:1
CREATE INDEX student_name_index ON student(name);
CREATE INDEX faculty_colour_index ON faculty(colour);


