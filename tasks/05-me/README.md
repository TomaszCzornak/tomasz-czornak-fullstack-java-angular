# Me: logged in

Stwórz branch na bazie gałęzi main: `git checkout -b feature/logged-in-user main`

## Opis

To już ostatni task wymagany do ukończenia procesu autoryzowania użytkowników.
W tym zadaniu dodasz endpoint którym klient API będzie mógł posługiwać się w celu zweryfikowania
obecnie zalogowanego użytkownika - jeżeli request zwróci błąd to znaczy, że użytkownik nie jest zalogowany.

Dodatkowo stworzysz odpowiedniego guarda, który będzie przepuszczał requesty tylko jeżeli request zawiera ważny accessToken.
Ten guard posłuży Ci w zabezpieczeniu kolejnych endpointów.

Stwórz jwt guarda, który wyciągnie token z nagłówka Authorization przychodzącego requestu.
W poprzednim zadaniu umieściłeś id użytkownika w środku tokenu - w środku guarda powinieneś wyciągnąć te id oraz
zweryfikować czy użytkownik o danym id istnieje w bazie danych.

Dopiero po spełnieniu powyższych wymogów przepuść request dalej.

Stwórz endpoint, który zwróci informację o użytkowniku identyfikującym się accessTokenem.

Response DTO:

```ts
{
  id: string;
  createdAt: string;
  updatedAt: string;
  firstName: string;
  lastName: string;
  email: string;
}
```

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
