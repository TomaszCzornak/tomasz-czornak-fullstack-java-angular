# Readers

Stwórz branch na bazie gałęzi main: `git checkout -b feature/readers main`

## Opis

W naszej firmie zostały zainstalowane czytniki kart identyfikujących pracowników.
Każdy pracownik odbija się swoją kartą przy wejściu do budynku oraz przy wyjściu.
Ochroniarz będzie posiadać interfejs w którym będzie dostawał powiadomienia o tym jaki pracownik właśnie wszedł lub wyszedł z budynku.

W związku z tym stwórz endpoint dla websocketów (na przykład z wykorzystaniem socket.io).
Server powinien nasłuchiwać na wiadomość `READ`, w treści wiadomości będzie informacja o imieniu i nazwisku pracownika
oraz rodzaju eventu (wejśćie lub wyjśćie).

Po otrzymaniu takiej wiadomości serwer powinien zemitować konkrenty event zawierający imię i nazwisko pracownika.

Payload dla otrzymywanej wiadomości `READ`:

```
  employeeName: string;
  eventName: ENTRY lub EXIT
```

Payload dla wysyłanej wiadomości `ENTRY` lub `EXIT`:

```
employeeName: string
```

Oznacza to, że w momencie kiedy server otrzyma event `READ` (triggerowany poprzez czytnik) z następującym payloadem:

```
  employeeName: "John Doe",
  eventName: 'ENTRY'
```

odeśle do klientów websockets (klientem będzie ochroniarz) event `ENTRY` z payloadem:

```
  employeeName: "John Doe",
```

Analogicznie event `EXIT`.

## Nice to have

- W celach testowych stwórz logikę triggerowania randomowego eventu.
- Co 30 sekund wylosuj randomowy event (ENTRY lub EXIT), wygeneruj randomowe imie i nazwisko pracownika
i wyślij dany event do ochroniarza.
- Stwórz zmienną środowiskową która będzie umożliwiała wyłączenie lub włączenie generowania randomowych eventów.

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
