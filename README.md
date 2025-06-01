# 🏢 HR Management System

## 📋 Opis projektu

Kompleksowy system zarządzania zasobami ludzkimi (HR) zbudowany w architekturze fullstack Java + Angular. System umożliwia zarządzanie kandydatami, ofertami pracy, procesami rekrutacyjnymi oraz komunikacją w czasie rzeczywistym.

## 🛠️ Stack technologiczny

### Backend (Java)
- **Java 21** - język programowania
- **Spring Boot 3.2.2** - framework aplikacyjny
- **Spring Security** - autoryzacja i autentykacja
- **Spring Data JPA** - warstwa persystencji
- **Hibernate ORM** - mapowanie obiektowo-relacyjne
- **PostgreSQL** - główna baza danych
- **Redis** - cache i zarządzanie sesjami
- **WebSocket (STOMP)** - komunikacja real-time
- **JWT** - tokeny autoryzacyjne
- **Maven** - zarządzanie zależnościami
- **JUnit 5 & Mockito** - framework testowy
- **Swagger/OpenAPI** - dokumentacja API
- **Jakarta Mail** - wysyłanie emaili
- **Jackson** - serializacja JSON

### Frontend (Angular)
- **Angular 15** - framework frontend
- **TypeScript** - język programowania
- **Angular Material** - biblioteka komponentów UI
- **RxJS** - programowanie reaktywne
- **Angular CDK** - development kit
- **Angular Forms** - reactive forms

### DevOps
- **Docker** - konteneryzacja
- **Docker Compose** - orkiestracja kontenerów

## 🏗️ Architektura projektu

```
tomasz-czornak-fullstack-java-angular/
├── hr/                          # Backend Java
│   ├── src/main/java/hr/tomek/czornak/
│   │   ├── candidate/           # Moduł kandydatów
│   │   ├── job/                 # Moduł ofert pracy
│   │   ├── recruitment/         # Moduł rekrutacji
│   │   ├── user/                # Moduł użytkowników
│   │   ├── login/               # Moduł logowania
│   │   ├── registration/        # Moduł rejestracji
│   │   ├── files/               # Moduł plików
│   │   ├── notifcations/        # Moduł powiadomień
│   │   ├── gate/                # Moduł bramki (WebSocket)
│   │   ├── security/            # Konfiguracja bezpieczeństwa
│   │   ├── email/               # Serwis email
│   │   └── utils/               # Narzędzia pomocnicze
│   └── src/test/                # Testy jednostkowe
└── frontend/hr/                 # Frontend Angular
    └── src/app/
        ├── modules/
        │   ├── auth/            # Moduł autoryzacji
        │   ├── candidate/       # Moduł kandydatów
        │   └── core/            # Moduł podstawowy
        └── ...
```

## 🚀 Funkcjonalności

### ✅ Zaimplementowane (Backend)

#### 🔐 Autoryzacja i Bezpieczeństwo
- Rejestracja użytkowników z aktywacją email
- Logowanie z tokenami JWT
- Reset hasła przez email
- Role i uprawnienia (ADMIN, HR_MANAGER, RECRUITER)
- Interceptory bezpieczeństwa

#### 👥 Zarządzanie Kandydatami
- CRUD operacje na kandydatach
- Wyszukiwanie kandydatów po email
- Przypisywanie kandydatów do użytkowników
- Sortowanie i filtrowanie
- Upload CV i dokumentów

#### 💼 Zarządzanie Ofertami Pracy
- CRUD operacje na ofertach pracy
- Przypisywanie umiejętności do ofert
- Statusy ofert (ACTIVE/CLOSED)
- Sortowanie po różnych kryteriach
- Waluty i wynagrodzenia

#### 🎯 System Rekrutacji
- Tworzenie procesów rekrutacyjnych
- Łączenie kandydatów z ofertami pracy
- Zarządzanie statusem rekrutacji
- Historia rekrutacji

#### 📁 Zarządzanie Plikami
- Upload plików (CV, dokumenty)
- Download plików
- Metadata plików
- Bezpieczne przechowywanie

#### 🔔 Powiadomienia Real-time
- WebSocket notifications
- Event-driven architecture
- Powiadomienia o zamkniętych ofertach
- System bramki wejściowej

#### 📧 System Email
- Wysyłanie emaili aktywacyjnych
- Reset hasła przez email
- Szablony HTML dla emaili
- Konfiguracja SMTP

### 🔄 W trakcie implementacji (Frontend)

#### 🎨 Interface użytkownika
- Formularz logowania ✅
- Formularz rejestracji ✅
- Wyszukiwanie kandydatów ✅
- Dashboard główny 🚧
- Zarządzanie kandydatami 🚧
- Zarządzanie ofertami pracy 🚧

## 📊 Model danych

### Główne encje:

- **User** - Użytkownicy systemu HR
- **Candidate** - Kandydaci do pracy
- **Job** - Oferty pracy z umiejętnościami
- **Skill** - Umiejętności i kompetencje
- **Recruitment** - Procesy rekrutacyjne
- **Notification** - Powiadomienia
- **FileMetadata** - Metadane uploadowanych plików

## 🚀 Jak uruchomić projekt

### Wymagania systemowe
- Java 21+
- Node.js 16+
- Docker & Docker Compose
- PostgreSQL (lub Docker)
- Redis (lub Docker)

### 1. Uruchomienie Backend (Java)

```bash
cd hr
# Uruchomienie z Docker Compose (PostgreSQL + Redis)
docker-compose -f docker-compose.dev.yml up -d

# Lub manualnie z Maven
./mvnw spring-boot:run
```

Backend będzie dostępny na: `http://localhost:8080`

### 2. Uruchomienie Frontend (Angular)

```bash
cd frontend/hr
npm install
npm start
```

Frontend będzie dostępny na: `http://localhost:4200`

### 3. Dostęp do dokumentacji API

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## 🔧 Konfiguracja

### Backend - application.properties
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/hr_db
spring.datasource.username=hr_user
spring.datasource.password=hr_password

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

# JWT
jwt.secret=your-secret-key
jwt.expiration=86400000

# Email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### Frontend - environment.ts
```typescript
export const environment = {
  production: false,
  proxyUrl: 'http://localhost:8080/v1'
};
```

## 🧪 Testowanie

### Backend Tests
```bash
cd hr
./mvnw test
```

### Frontend Tests
```bash
cd frontend/hr
npm test
```

### E2E Tests
```bash
cd frontend/hr
npm run e2e
```

## 📡 API Endpoints

### Autoryzacja
- `POST /login` - Logowanie użytkownika
- `POST /register` - Rejestracja użytkownika
- `GET /activate?token=` - Aktywacja konta
- `POST /reset-password` - Reset hasła

### Kandydaci
- `GET /v1/candidates` - Lista kandydatów
- `POST /v1/candidates` - Dodaj kandydata
- `GET /v1/candidates/{id}` - Szczegóły kandydata
- `PUT /v1/candidates/{id}` - Aktualizuj kandydata
- `DELETE /v1/candidates/delete/{id}` - Usuń kandydata
- `GET /v1/candidates/find?search=` - Wyszukaj kandydatów

### Oferty pracy
- `GET /v1/jobs` - Lista ofert pracy
- `POST /v1/jobs` - Dodaj ofertę
- `GET /v1/jobs/{id}` - Szczegóły oferty
- `PUT /v1/jobs` - Aktualizuj ofertę
- `DELETE /v1/jobs/{id}` - Usuń ofertę
- `DELETE /v1/jobs/{id}/close` - Zamknij ofertę

### Rekrutacje
- `GET /v1/recruitments` - Lista rekrutacji
- `POST /v1/recruitments` - Dodaj rekrutację
- `GET /v1/recruitments/{id}` - Szczegóły rekrutacji
- `PUT /v1/recruitments` - Aktualizuj rekrutację
- `DELETE /v1/recruitments/{id}` - Usuń rekrutację

### Pliki
- `POST /v1/files/upload` - Upload pliku
- `GET /v1/files/{fileName}` - Download pliku

### WebSocket Endpoints
- `/app/ws` - Bramka wejściowa
- `/app/notify` - Powiadomienia
- `/topic/events` - Subskrypcja zdarzeń
- `/topic/events/notifications` - Subskrypcja powiadomień

## 👥 Role użytkowników

- **ADMIN** - Pełne uprawnienia do systemu
- **HR_MANAGER** - Zarządzanie rekrutacjami i kandydatami
- **RECRUITER** - Podstawowe operacje rekrutacyjne

## 🔒 Bezpieczeństwo

- Wszystkie hasła są hashowane (BCrypt)
- Tokeny JWT z konfiguralnym czasem wygaśnięcia
- CORS skonfigurowany dla frontend
- Walidacja danych wejściowych
- Protection przeciwko CSRF
- Secure headers

## 📈 Roadmap

### Priorytet wysoki
- [ ] Kompletny frontend Angular
- [ ] Dashboard z metrykami
- [ ] Kalendarz rekrutacji
- [ ] System raportów

### Priorytet średni
- [ ] Mobile responsive design
- [ ] Bulk operations (import/export)
- [ ] Advanced search & filtering
- [ ] Email templates editor

### Priorytet niski
- [ ] Multi-language support (i18n)
- [ ] Progressive Web App (PWA)
- [ ] Integration z LinkedIn
- [ ] Advanced analytics

## 🤝 Jak kontrybuować

1. Fork projektu
2. Stwórz feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit zmian (`git commit -m 'Add some AmazingFeature'`)
4. Push do branch (`git push origin feature/AmazingFeature`)
5. Otwórz Pull Request

## 📝 Licencja

Ten projekt jest licencjonowany na zasadach MIT License - zobacz plik [LICENSE](LICENSE) dla szczegółów.

## 👨‍💻 Autor

**Tomasz Czornak**
- GitHub: [@tomasz-czornak](https://github.com/tomasz-czornak)
- LinkedIn: [Tomasz Czornak](https://linkedin.com/in/tomasz-czornak)

## 🙏 Podziękowania

- Spring Boot Team za świetny framework
- Angular Team za potężne narzędzia frontend
- Społeczność Open Source za inspirację i wsparcie

---

⭐ **Jeśli projekt Ci się podoba, zostaw gwiazdkę!** ⭐
