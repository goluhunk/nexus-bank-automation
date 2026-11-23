# Nexus Bank – Enterprise UI Automation Demo

A local, fictional banking portal plus Selenium + Java 21 + Cucumber + JUnit 5 + Maven automation framework.

> **Safety:** This is a learning demo only. It does not connect to a bank, process real payments, or store real customer data. Use only the supplied dummy credentials.

## Prerequisites
- Java 21 (`java -version`)
- Maven 3.9+
- Chrome, Firefox, and/or Edge installed
- Python 3 (only to serve the static demo application)

Selenium Manager obtains compatible browser drivers automatically. The first browser launch may require internet access.

## 1. Start the demo application
From the project root, in Terminal 1:

```bash
python3 -m http.server 8000 --directory app
```

Open http://localhost:8000. Demo login:
- Username: `gaurav`
- Password: `Bank@123`

## 2. Run tests
In Terminal 2, from the project root:

```bash
mvn clean test
```

Choose a browser:

```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

Headless mode:

```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

Choose environment config:

```bash
mvn clean test -Denvironment=qa
```

The `dev` and `qa` configs both point to localhost by default. Edit the properties to change the base URL.

## 3. Reports and screenshots
- Cucumber HTML: `target/cucumber-report.html`
- Cucumber JSON: `target/cucumber.json`
- Failure screenshots: `target/screenshots/`

## 4. Project structure
- `app/index.html`: six-page local demo portal
- `src/test/java/com/nexusbank/pages`: Page Object Model pages
- `src/test/java/com/nexusbank/steps`: Cucumber step definitions
- `src/test/java/com/nexusbank/hooks`: scenario setup, teardown, failure screenshots
- `src/test/java/com/nexusbank/utility`: ThreadLocal WebDriver and config reader
- `src/test/resources/features`: Gherkin scenarios
- `src/test/resources/config`: environment settings

## 5. Important implementation notes
- Browser selection: `-Dbrowser=chrome|firefox|edge`.
- `ThreadLocal<WebDriver>` keeps driver instances isolated by execution thread.
- Parallel execution is deliberately disabled initially. Enable it only after extending test data and state isolation; the demo uses in-memory state per browser session.
- The demo is a static front-end. API and database validation are extension exercises, not implemented backend integrations.
- The UI uses a fictional USD-denominated dataset to avoid representing a real banking service.
- No credentials should be committed for real systems. Keep secrets outside source control.

## 6. Suggested next enterprise extensions
1. Add REST Assured API tests against a mock service.
2. Add a disposable test database and JDBC assertions.
3. Add scenario-scoped test context and generated test data.
4. Add CI pipeline and browser matrix.
5. Add a transfer confirmation/reversal workflow and idempotency checks.
