# Candidates

Stwórz branch na bazie gałęzi main: `git checkout -b feature/candidates main`

## Opis

Podobnie jak w przypadku ofert pracy stworzysz cały CRUD-owy moduł, tym razem dla kandydatów.

Model kandydata powinien zawierać:

```
  name: string;
  email: string;
  createdBy: string; (id użytkownika, który stworzył kandydata)
```

Kandydat powinien zostać przypisany do kolejnego modelu jakim jest `rekrutacja`.
Rekrutacja to tak naprawdę połączenie wiele do wielu kandydata z ofertami pracy.
Oznacza to, że jedna oferta pracy może mieć wielu kandydatów jak i jeden kandydat może aplikować na wiele ofert.
Rekrutacja jest czymś co te dwa światy łączy. Jedna rekrutacja może mieć jedną ofertę pracy oraz jednego kandydata - poczytaj o tym w jaki sposób robi się relacje wiele do wielu.

Walidacja tak samo jak w przypadku użytkowników.
Request i Response DTO dla każdego endpointu wymyśl sam.

Endpointy jakie powinieneś dodać to:

- getAll który zwraca wszystkich kandydatów
- getById który zwraca konkretnego kandydata
- create który tworzy nowego kandydata
- update który modyfikuje dowolne pole danego kandydata
- delete który usuwa kandydata

Email kandydata powinien być unikatowy w ramach tabeli.

Usunięcie kandydata z bazy powinno usunąć wszystkie jego rekrutację lecz nie usuwać oferty pracy.

## Nice to have

- Gety powinny zwracać informację na temat rekrutacji w których udział bierze kandydat.
- Stwórz dodatkowy endpoint dla rekrutacji, który po id rekrutacji zwróci informację który kandydat aplikuje i na jaką ofertę pracy.

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
