INSERT INTO MOVIES(title) VALUES('Titanic');
INSERT INTO MOVIES(title) VALUES('Sami Swoi');
INSERT INTO MOVIES(title) VALUES('Superman');
INSERT INTO MOVIES(title) VALUES('Gwiezdne Wojny: Nowa Nadzieja');
INSERT INTO MOVIES(title) VALUES('Iron Man');
INSERT INTO MOVIES(title) VALUES('Władca Pierścieni: Powrót Króla');
INSERT INTO MOVIES(title) VALUES('Władca Pierścieni: Drużyna Pierścienia');
INSERT INTO MOVIES(title) VALUES('Władca Pierścieni: Dwie wieże');

INSERT INTO ROOMS(room_name,number_of_seats) VALUES('Sala 1',5);
INSERT INTO ROOMS(room_name,number_of_seats) VALUES('Sala 2',6);
INSERT INTO ROOMS(room_name,number_of_seats) VALUES('Sala 3',4);

INSERT INTO TIMETABLES(session_time,session_date,movie_id,room_id) VALUES('10:00','2021-05-20',5,1);
INSERT INTO TIMETABLES(session_time,session_date,movie_id,room_id) VALUES('12:00','2021-05-20',2,2);
INSERT INTO TIMETABLES(session_time,session_date,movie_id,room_id) VALUES('19:00','2021-05-20',1,3);
INSERT INTO TIMETABLES(session_time,session_date,movie_id,room_id) VALUES('00:20','2021-05-25',1,3);
INSERT INTO TIMETABLES(session_time,session_date,movie_id,room_id) VALUES('11:00','2021-05-20',5,3);
INSERT INTO TIMETABLES(session_time,session_date,movie_id,room_id) VALUES('11:00','2021-05-20',4,1);

INSERT INTO SEATS(seat_number,room_id) VALUES(1,1);
INSERT INTO SEATS(seat_number,room_id) VALUES(2,1);
INSERT INTO SEATS(seat_number,room_id) VALUES(3,1);
INSERT INTO SEATS(seat_number,room_id) VALUES(4,1);
INSERT INTO SEATS(seat_number,room_id) VALUES(5,1);

INSERT INTO SEATS(seat_number,room_id) VALUES(1,2);
INSERT INTO SEATS(seat_number,room_id) VALUES(2,2);
INSERT INTO SEATS(seat_number,room_id) VALUES(3,2);
INSERT INTO SEATS(seat_number,room_id) VALUES(4,2);
INSERT INTO SEATS(seat_number,room_id) VALUES(5,2);
INSERT INTO SEATS(seat_number,room_id) VALUES(6,2);

INSERT INTO SEATS(seat_number,room_id) VALUES(1,3);
INSERT INTO SEATS(seat_number,room_id) VALUES(2,3);
INSERT INTO SEATS(seat_number,room_id) VALUES(3,3);
INSERT INTO SEATS(seat_number,room_id) VALUES(4,3);

INSERT INTO GUESTS(name,surname) VALUES('Michał','Ryszka');
INSERT INTO GUESTS(name,surname) VALUES('Kamil','Ryszka');

INSERT INTO TICKETS(type,price,seat_number,Guest_id,timetable_id) VALUES ('Student',15,3,1,2);
INSERT INTO TICKETS(type,price,seat_number,Guest_id,timetable_id) VALUES ('Student',15,2,2,2);

