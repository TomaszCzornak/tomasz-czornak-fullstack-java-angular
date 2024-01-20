# Jobs

Stwórz branch na bazie gałęzi main: `git checkout -b feature/jobs main`

## Opis

Pora na stworzenie całego modułu dotyczącego ofert pracy.
Stwórz cały zestaw CRUD-owych endpointów dla modułu jobs.

Model oferty pracy powinien zawierać:

```
title: string;
shortDescription: string;
longDescription: string;
logo: string;
companyName: string;
status: OPEN lub CLOSED;
createdBy: string; (id użytkownika, który stworzył ofertę pracy)
```

Request i Response DTO dla każdego endpointu wymyśl sam.

Endpointy jakie powinieneś dodać to:

- getAll który zwraca wszystkie oferty
- getById który zwraca konkretną ofertę
- create który tworzy nową ofertę
- update który modyfikuje dowolne pole danej oferty pracy
- delete który usuwa ofertę pracy
- close który zmienia status oferty pracy na CLOSED

Nowo utworzone oferty pracy powinny mieć status OPEN

Przy tworzeniu/edycji zastosuj następującą walidację:

```
title - od 3 do 50 znaków
shortDescription - od 5 do 15 znaków
longDescription - od 5 do 250 znaków
logo - walidacja dla url
companyName - od 5 do 50 znaków
```

Get nie powinine zwracać informacji o użytkowniku który stworzył ofertę pracy (nie chcemy tu wykonywać joina).
getAll i getById powinien pobierać tylko kandydatów stworzonych przez obecnie zalogowanego użytkownika.
update i delete powinien pozwalać tylko na działania w obrębie kandydatów stworzonych przez obecnie zalogowanego użytkownika.

## Nice to have

- Zastąp usuwanie poprzez funkcjonalność softDelete (zrób research jak to powinno działać na własną rękę).

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
