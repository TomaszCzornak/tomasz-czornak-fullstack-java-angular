# Tests

Stwórz branch na bazie gałęzi main: `git checkout -b feature/tests main`

## Opis

Stwórz boilerplate dla testów oraz napisz testy dla obecnie stworzonych endpointów.
Testy powinny testować endpointy, przykład testu:

1. Zarejestruj się za pomocą endpointu register
2. Zaloguj się za pomocą endpointu login
3. Używając accessTokena użyj endpointu me
4. Endpoint me powinien zwrócić informację na temat Twojego użytkownika

Testy powinny korzystać z pradziwej bazy danych.
Po każdym teście baza danych powinna zostać wyczyszczona.

## Nice to have

- Testy uruchamiane są w dockerze

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
