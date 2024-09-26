ALTER TABLE student
ADD constraint age CHECK (age > 16);

ALTER TABLE student
ALTER COLUMN name SET NOT NULL;
	
ALTER TABLE faculty
ADD CONSTRAINT name_colour  UNIQUE (name,colour);
	
ALTER TABLE student
alter column age set DEFAULT (20);



create table car(
    id int primary key,
    company varchar,
    model varchar,
    price int
)

create table driver(
    id int primary key,
    name varchar,
    age int,
    license boolean,
    car_id int references car(id)
)

SELECT student.name, student.age, faculty.name FROM student INNER JOIN faculty
ON student.name = faculty.name


SELECT student.name, FROM student INNER JOIN avatar
ON avatar s.id =a.student_id


	
