ALTER TABLE student
ADD constraint age CHECK (age > 16);

ALTER TABLE student
ALTER COLUMN name SET NOT NULL;
	
ALTER TABLE faculty
ADD CONSTRAINT name_colour  UNIQUE (name,colour);
	
ALTER TABLE student
alter column age set DEFAULT (20);



CREATE TABLE people
age TEXT,
name TEXT,
car TEXT,
license TEXT PRIMARY KEY,
car_people TEXT REFERENCES car_people (model);

CREATE TABLE car_people
model TEXT PRIMARY KEY,
brand TEXT,
cost TEXT;

SELECT student.name, student.age, faculty.name FROM student INER JOIN faculty
ON student.name = faculty.name;

//avatar
SELECT student.name, FROM student INER JOIN avatar
ON avatar. = ;


	
