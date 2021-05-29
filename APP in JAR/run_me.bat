::Michał Ryszka 
::05.2021

@ECHO OFF
::Run app
START cmd /k java -jar ticketBooking.jar

@ECHO ON

TIMEOUT 8

::Add test data to database
CURL -i -X POST -H "Content-Type:application/json" -d "{\"title\": \"Iron Man\"}" http://localhost:8080/cinema/movie/add
CURL -i -X POST -H "Content-Type:application/json" -d "{\"title\": \"Wladca Pierscieni Druzyna Pierscienia\"}" http://localhost:8080/cinema/movie/add
CURL -i -X POST -H "Content-Type:application/json" -d "{\"title\": \"K-PAX\"}" http://localhost:8080/cinema/movie/add
CURL -i -X POST -H "Content-Type:application/json" -d "{\"title\": \"Przebudzenie\"}" http://localhost:8080/cinema/movie/add

CURL -i -X POST -H "Content-Type:application/json" -d "{\"roomName\": \"Sala 1\",\"numberOfSeats\": 8}" http://localhost:8080/cinema/room/add
CURL -i -X POST -H "Content-Type:application/json" -d "{\"roomName\": \"Sala 2\",\"numberOfSeats\": 12}" http://localhost:8080/cinema/room/add
CURL -i -X POST -H "Content-Type:application/json" -d "{\"roomName\": \"Sala 3\",\"numberOfSeats\": 10}" http://localhost:8080/cinema/room/add

CURL -i -X POST -H "Content-Type:application/json" -d "{\"sessionTime\": \"09:30\",\"sessionDate\": \"2021-06-02\"}"  http://localhost:8080/cinema/timetable/add/1/3
CURL -i -X POST -H "Content-Type:application/json" -d "{\"sessionTime\": \"12:00\",\"sessionDate\": \"2021-06-02\"}" http://localhost:8080/cinema/timetable/add/2/1
CURL -i -X POST -H "Content-Type:application/json" -d "{\"sessionTime\": \"15:00\",\"sessionDate\": \"2021-06-03\"}" http://localhost:8080/cinema/timetable/add/3/2

::Test use case
::To show repertoire on chosen date
CURL http://localhost:8080/repertoire/2021-06-02/09:00
::To show details about room for chosen movie
CURL http://localhost:8080/repertoire/movie/2
::To buy ticket
CURL -i -X POST -H "Content-Type:application/json" -d "{\"name\": \"Janek\",\"surname\": \"Kowalski\", \"tickets\": [{\"ticketType\": \"Student\",\"seatNumber\": 1}]}" http://localhost:8080/repertoire/movie/2/buy

@ECHO OFF

CMD /k
