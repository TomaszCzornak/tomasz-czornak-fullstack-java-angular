## Init

Stwórz branch na bazie gałęzi main: `git checkout -b feature/init-hr-api-setup main`

## Opis

Będziesz pracować nad api, którego celem jest zarządzanie ofertami pracy, kandydatami oraz procesem rekrutacji w firmie HR.

Pamiętaj, że podczas pracy z projektem:

- każdy nowy endpoint powinien zostać udokumentowany w swaggerze
- powinieneś użyć relacyjnej bazy danych
- wszelkie zmiany w schemie bazy danych powinno zostać odzwierciedlone przez migrację (o ile użyte narzędzia na to pozwalają)
- wszelkiego rodzaju błędy powinny zostać obsłużone poprzez odpowiedni kod HTTP
- pamiętaj o odpowiednich metodach http GET/POST/PUT/PATCH itd.
- każdy model w bazie danych powinien mieć takie pola jak: id, createdAt, updatedAt
- każda modyfikacja encji w bazie danych powinna modyfikować automatycznie pole updatedAt
- każdy payload powinien posiadać odpowiednią walidację (np. jeżeli spodziewasz się adres e-mail to zastosuj odpowiednią walidajcę dla adresu e-mail itd.)
- warstwą odpowiedzialną za routing oraz walidację powinny być controllery
- warstwą odpowiedzialną za logikę biznesową powinny być serwisy
- warstwą odpowiedzialną za komunikację z bazą powinny być repozytoria (Twoje narzędzie może Ci udostępniać od razu takie repozytoria ale nie musi)
- dobrą praktyką jest wersjonowanie api od samego początku, w związku z tym Twoje endpointy powinny mieć prefix /v1/, np. endpoint do rejestracji do `/auth/register` ale w prefixie umieszczamy wersję api więc url do endpointu to finalnie `/v1/auth/register`

Pierwszym zadaniem jest stworzenie repozytorium z boilerplatem dla wybranej technologii i wypchnięcie tego na zdalne repozytorium.

## Pre code review checklist

W folderze `/tasks/pre-code-review` znajdziesz listę z punktami, które należy sprawdzić przed stworzeniem każdego merge requestu. To pozwoli Ci wychwycić błędy lub dodatkowe miejsca do usprawnienia przed procesem code review.
