# Datenbank

## Produktives System

Für das produktive System wird eine MariDB vorausgesetzt. 
Die Datenbank kann mit dem SQL-Script `create-database.sql` initial mit
den Benutzern angelegt werden.

Die Tabellen und Daten werden automatisch durch **Flyway** beim Start angelegt. 

## Test - und Entwicklungssystem

Für Test und Entwicklung wird eine InMemory-Datenbank (H2) verwendet.
Dabei werden beim Start automatisch mittels **Flyway** die
Tabellen angelegt.
