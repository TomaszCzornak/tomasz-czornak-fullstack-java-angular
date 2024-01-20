# Gitflow

![alt text](./../tasks/gitflow/gitflow-mentoring.PNG)

Będąc na gałęzi `main` upewnij się, że masz pobrane ostatnie zmiany:

`git pull`

Stwórz branch pod konkretne zadanie:

`git checkout -b feature/nazwa-zadania main`

Sprawdź jakie zmiany zostały wprowadzone:

`git status`

Przed wysłaniem zmian warto odpalić eslinta, który wyłapie dodatkowo potencjalne
błędy, problemy:

`npm run lint`

Dodaj zmiany do commita:

`git add .`

Zacommituj zmiany:

`git commit -m "What effect have my changes made?"`

Wyślij lokalne zmiany na repozytorium:

`git push origin feature/nazwa-zadania`

# Praca na wielu branczach jednocześnie

Jest to trochę bardziej skomplikowane. Najpierw musimy zadać pytanie czy do
pracy z kolejnym branchem potrzebujemy coś z tego.

Jeżeli nie - utwórz branch z mastera.

Jeżeli tak - utwórz branch z tego.

Natomiast czekają nas komplikacje w kilku miejscach a dokładniej trzeba będzie
nauczyć się rebasów i ewentualnego rozwiązywania konfliktów, aby opis był
czytelniejszy przyjmijmy, że obecny branch nad którym skończyłeś pracę nazwiemy
A a nowy branch który tworzysz na jego podstawie nazwiemy B. Jeżeli zmergujesz
branch A do mastera to musisz wejść na branch B i zrobić rebase do mastera, jest
to opisałem to tym diagramem:

![alt text](./../tasks/gitflow/parallel-branches.png)

![alt text](./../tasks/gitflow/parallel-branches-2.png)

A co jeśli dojdzie do sytuacji w której branch A targetuje master brancha ale
jeszcze nie został zmergowany, branch B wyszedł z brancha A i też jest
już gotowy do zmergowania, jeżeli wystawisz pull requesta z brancha B do master
to Pull request będzie zawierać zmiany z brancha B jak i również A w jednym pull
requeście. Myślę, że taka sytuacja u nas nie nastąpi więc nie ma co się tym
przejmować na tę chwilę, w związku z tym pominę szczegółowy opis jak temu
zaradzić 😅 jako ciekawostke powiem, że musiałbyś zmienić target żeby B nie
chciał mergować się do maina tylko do A, wtedy na PR będzie widać tylko zmiany z
brancha B. To wszystko może wydawać się skomplikowane natomiast w projektach
gdzie codziennie wlatuje dużo PR-ek i code review takie kombinacje to norma,
warto mieć to opanowane i rozumieć jak to działa 🙂

# Sesje z mentorem

- domyślny czas trwania spotkania online to 1 godzina
- możliwe jest przedłużenie o 30 minut za zgodą mentora
- spotkanie trwające dłużej niż 1h 30 min nie jest rekomendowane (dla obu stron)
- spotkania z mentorem nie mogą być nagrywane w żadnej formie
- mentor nie może bezpośrednio realizować za ucznia zadań rekrutacyjnych oraz
  zadań z jego pracy, może natomiast pomagać zrozumieć techniczne aspekty
  analogicznych problemów, które pomogą w rozwiązywaniu zadań rekrutacyjnych i
  zadań z pracy
- punktualność i szanowanie czasu: jeśli uczeń zmienia ustalony już termin zajęć
  wówczas prosimy o informacje o przełożeniu (calendly/mail/telefon) co najmniej
  12h przed umówionymi zajęciami. W przeciwnym przypadku zajęcia będą przepadać
- zajęcia przepadają również w sytuacji gdy osoba nie pojawi się na zajęciach, a
  nie przekazała z min. 12h wyprzedzeniem informacji o nieobecności, nie
  odwołała spotkania
- w przypadku, gdy mentor odwoła zajęcia w ostatniej chwili (mniej niż 12h przed
  umówionym terminem), otrzymujesz darmową dodatkową godzinę zajęć

# Dobre praktyki Code Review

- https://docs.gitlab.com/ee/development/code_review.html#the-responsibility-of-the-reviewer
- https://docs.gitlab.com/ee/development/code_review.html#the-responsibility-of-the-maintainer
