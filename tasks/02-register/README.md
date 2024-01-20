# Register

Stwórz branch na bazie gałęzi main: `git checkout -b feature/register main`

## Opis

Pierwszym zadaniem jest stworzenie endpointu do rejestracji użytkowników jak i samego modelu użytkownika w bazie.
Pamiętaj o tym, że hasło przetrzymywane w bazie danych powinno być hashowane.
Jeżeli w systemie istnieje już użytkownik o takim adresie mailowym api powinno zwrócić odpowiedni kod i wiadomość błędu.

Dodatkowo ten endpoint powinien zwrócić dane o nowo zarejestrowanym użytkowniku ale bez hasła.

Payload DTO:

```ts
{
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  passwordRepeat: string;
}
```

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

Walidacja:

```
  firstName: minimum 3 znaki, maximum 15
  lastName: minimum 3 znaki, maximum 15
  email: poprawny adres e-mail
  password: minimum 5 maximum 15 znaków
  passwordRepeat: minimum 5 maximum 15 znaków, taki sam jak password
```

## Nice to have

- Wymaganie skomplikowanego hasła np. minimum 1 mała i duża litera, 1 cyfra, 1 znak specjalny.

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
