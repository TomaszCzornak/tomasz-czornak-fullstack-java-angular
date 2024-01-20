# Pre-Code Review Checklist

Przed stworzeniem merge requestu, upewnij się, że Twój kod spełnia poniższe wytyczne. Dzięki temu będziesz lepiej przygotowany do stworzenia merge requesta i przesłania go do code review.

## Ogólne
- DRY (Don't Repeat Yourself): Czy ten sam kod jest powielany więcej niż dwa razy?
- Upewnij się, że usunięto nieużywane biblioteki i zależności
- Upewnij się, że nie ma żadnych niepożądanych błędów w logach aplikacji
- Upewnij się, że wszystkie zależności są zainstalowane i działają poprawnie

## Podatność na błędy
- Upewnij się, że nie ma żadnych błędów statycznej analizy kodu
- Upewnij się, że nie ma błędów z narzędzi do lintowania kodu (np. ESLint, pylint)

## Code Style
- Sprawdź, czy kod jest sformatowany zgodnie z narzędziami do formatowania
- Upewnij się, że nazwy zmiennych i funkcji są zrozumiałe i adekwatne do kontekstu

## Best Practice
- Sprawdź, czy używasz struktury folderów i architektury zgodnej z logiką biznesową
- Upewnij się, że moduły i funkcje są małe i mają jedno zadanie
- Sprawdź, czy używasz zagnieżdżonych warunków i pętli z umiarem

## Safety
- Upewnij się, że nie ma błędów związanych z typami danych
- Upewnij się, że wszelkie dane wejściowe są walidowane
- Upewnij się, że zmiany w schemacie bazy danych są objęte odpowiednią migracją

## Security
- Upewnij się, że nie ma danych wrażliwych w kodzie (takich jak klucze API)
- Upewnij się, że zapytania do baz danych i API są autoryzowane jeśli jest to wymagane

## Design
- Upewnij się, że API jest zgodne z wytycznymi RESTful lub GraphQL
- Upewnij się, że struktura danych jest zrozumiała i zgodna z wymaganiami biznesowymi

## Performance
- Upewnij się, że zapytania do baz danych są zoptymalizowane
- Sprawdź wydajność aplikacji za pomocą dedykowanych dla danego języka programowania narzędzi

## Dokumentacja
- Upewnij się, że dokumentacja API jest jasna i czytelna
- Upewnij się, że komentarze w kodzie są aktualne i pomagają w zrozumieniu kodu
- Upewnij się, że README jest aktualne i zawiera wszystkie potrzebne informacje dla nowych developerów
