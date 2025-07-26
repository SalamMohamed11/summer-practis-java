TRUNCATE TABLE questions RESTART IDENTITY;

INSERT INTO questions (title, optiona, optionb, optionc, ans, chose) VALUES
('Where is the kpfu located?', 'paris', 'Berlin', 'kazan', 3, -1),
('what is SQL stand for', 'Structured Query Language', 'Standard Query Logic', 'System Query Line', 1, -1),
('What do we use to make a web project in Java', 'Spring Boot', 'Apache Kafka', 'flask', 1, -1),
('What is the correct way to declare and initialize an integer variable in Java', 'integer num = 5;', 'int num = "5";', 'int num = 5;', 3, -1),
('Which method is the entry point of a Java program?', 'main()', 'run();', 'Start()' , 1, -1);
