# Ticket-Booking-App
Aplikacja _Ticket Booking_ to mini systemem do rezerwacji biletów i przeglądania dostępnego repertuaru w kinie.
Została napisana w języku Java (wersja 11) z wykorzystaniem frameworku Spring.


## Instrukcja uruchomienia

Do uruchomienia wymagana jest instalacja JDK w wersji 11 lub wyższej.

Plik wykonawczy znajduje się w folderze _APP in JAR_, aby uruchomić aplikację uruchamiamy skrypt run_me.bat
(alternatywna metoda wykonujemy komende: _**java -jar ticketBooking.jar**_ lub klikamy na _ticketBooking.jar_, baza nie jest wtedy załadowana przykładowymi danymi i 
nie wykonuje się przykładowy sceniariusz).

Skrypt run_me.bat dodaje do bazy 4 filmy, 3 sale, 3 terminy do repertuaru.

Wykonuje również przykładowy scenariusz:

1) Zapytanie jakie filmy są grane w wybranym dniu od wybranej godziny do końca dnia\
http://localhost:8080/repertoire/{wybrany_dzien}/{wybrana_godzina} 
2) Zapytanie o detale dotyczące sali kinowej w której wyświetlany jest wybrany film\
http://localhost:8080/repertoire/movie/{numer_id_wybranego_filmu} 
3) Rezerwacja miejsc\
http://localhost:8080/repertoire/movie/{numer_id_wybranego_filmu}/buy 

**Przykładowe działanie:**\
Zapytanie jakie filmy są grane w wybranym dniu od wybranej godziny do końca dnia: 

GET - http://localhost:8080/repertoire/2021-06-02/09:00 

Odpowiedź na zapytanie:
```
[
    {
        "id": 1,
        "session_time": "09:30:00",
        "movie_title": "Iron Man"
    },
    {
        "id": 2,
        "session_time": "12:00:00",
        "movie_title": "Wladca Pierscieni Druzyna Pierscienia"
    }
]
```
Zapytanie o detale dotyczące sali kinowej w której wyświetlany jest wybrany film\
GET - http://localhost:8080/repertoire/movie/2

Odpowiedź na zapytanie:
```
{
    "room": "Sala 1",
    "freeSeats": 8,
    "availableSeats": [
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8
    ]
}
```
Rezerwacja miejsc\
POST - http://localhost:8080/repertoire/movie/2/buy

Dane do rezerwacji:
```
{
    "name": "Jan",
    "surname": "Kowalski",
    "tickets": [
     {
            "ticketType":"Student",
            "seatNumber": 1
        }, {
            "ticketType":"Child",
            "seatNumber": 2
        }
    ]
}
```
Odpowiedź na zapytanie:
```
{
    "totalCost": 30.5,
    "reservationExpirationTime": "10:00:00"
}
```

## Wszystkie dostępne endpointy:

1)GET - http://localhost:8080/timetable - zwraca cały dostępny repertuar\
2)GET - http://localhost:8080/repertoire/{wybrany_dzien}/{wybrana_godzina} - zwraca dostępny repertuar w wybrany dziń od wybranej godziny do końca dnia\
3)GET - http://localhost:8080/repertoire/movie/{numer_id_wybranego_filmu} - zwraca informacje o sali kinowej dla wybranej projekcji\
4)POST - http://localhost:8080/repertoire/movie/2 - pozwala wykonać rezerwację\
Dane do rezerwacji, nie wolno zostawiać wolnego miejsca np. zarezerować 1 i 3 gdy wolne jest miejsce nr 2 lub zarezerwować miejsce nr 2 gdy wolne jest nr 1!\
Typy biletów: Adult 25zł, Student 18zł, Child 12,5zł:
```
{
    "name": "Imie",
    "surname": "Nazwisko",
    "tickets": [
     {
            "ticketType":"typ biletu",
            "seatNumber": numer miejsca
        }, {
            "ticketType":"typ biletu",
            "seatNumber": numer miejsca
        }
    ]
}
```
5)POST - http://localhost:8080/cinema/movie/add - pozwala dodać film do bazy
```
{
    "title": "Tytuł filmu",
}
```
6)POST - http://localhost:8080/cinema/room/add - pozwala dodać sale kinową
```
{
     "roomName": "Nazwa sali",
     "numberOfSeats": Ilość miejsc
}
```
7)POST - http://localhost:8080/cinema/timetable/{movie_id}/{room_id} - pozwala dodać nowy pokaz\
_movie_id_ tu id filmu który będzie pokazywany\
_room_id_ tu id sali w której odbędzie się pokaz
```
{
       "sessionTime": "Godzina pokazu",
       "sessionDate": "Data pokazu"
}
```

