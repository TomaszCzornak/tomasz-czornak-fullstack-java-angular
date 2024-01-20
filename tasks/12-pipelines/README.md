## Pipelines

Stwórz branch na bazie gałęzi main:
`git checkout -b feature/pipelines`

## Subtaski
- Dodaj CI pipelines za pomocą gitlab-ci
- Wymagane pipeline-y to: 
    - `type-check` - sprawdzenie zgodności typów w kodzie.
    - `lint` - analiza kodu w celu identyfikacji i naprawy problemów dotyczących stylu kodowania i potencjalnych błędów (np. ESLint dla JavaScript, pylint dla Pythona)
    - `test` - automatyczne uruchomienie zestawu testów jednostkowych i integracyjnych
    - `build` - kompilacja i budowa aplikacji w formie, która jest gotowa do wdrożenia na serwerze produkcyjnym.
- Komenda lint powinna być ustawiona tak aby nie tolerowała ani jednego warningu

## Nice to have
- Postaraj się jak najlepiej zoptymalizować pipeliny
- Dodaj pipeline który sprawdzi ciruclar-dependencies w projekcie (możesz użyć do tego biblioteki `dependency-cruiser`)
- Jeżeli dodałeś translacje do projektu to napisz skrypt CI który upewni się, że nie ma różnicy w kluczach translacji między różnymi językami
- Popularnym serwisem do trzymania repozytoriów jest github, napisz CI dla githuba które zrobi to samo

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
