Aby przetestować websockety należy odpalić całego spring-boot'a, następnie odpalić z
pakietu gate plik stomp.html
Następnie w pliku jest formularz należy wpisać dane i wysłać.
Jako udany test uznaje się zwrócnony reponse pod formularzem.
Jeżeli zostanie znaleziony pracownik w bazie po mailu zostaną podane jego wartości w json'ie.
Jeżeli nie zostanie znaleziony, server zwróci json obiekt event z polem isUserExist jako wartość
false