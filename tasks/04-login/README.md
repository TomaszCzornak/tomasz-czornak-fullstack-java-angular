# Login

Stwórz branch na bazie gałęzi main: `git checkout -b feature/login main`

## Opis

Pora dokończyć autoryzację, w tym celu dodaj endpoint do logowania się.
Sprawdź czy użytkownik o podanym mailu i haśle istnieje w bazie.
Jeżeli dane są prawidłowe wygeneruj token JWT w którym umieścisz id tego użytkownika.

Token powinien być podpisany odpowiednim secretem - pamiętaj, że secret nie może się pojawić w historii gita.
Token powinien mieć ważność 3 dni.

Payload DTO:

```ts
{
  email: string;
  password: string;
}
```

Response DTO:

```ts
{
  accessToken: string;
  user: {
    id: string;
    createdAt: string;
    updatedAt: string;
    firstName: string;
    lastName: string;
    email: string;
  }
}
```

Walidacja:

```
  email: tak samo jak przy rejestracji
  password: tak samo jak przy rejestracji
```

## Nice to have

- Dodaj funkcjonalność blokowania użytkownika - jeżeli w ciągu ostatnich 5 minut użytkownik wywołał błąd autoryzacji zablokuj możliwość logowania się na 5 minut.

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
