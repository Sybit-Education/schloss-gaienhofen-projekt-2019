[![Build Status](https://travis-ci.org/Sybit-Education/schloss-gaienhofen-projekt-2019.svg?branch=master)](https://travis-ci.org/Sybit-Education/schloss-gaienhofen-projekt-2019)


# Schloss Gaienhofen - Projekt 2019

Projekt mit Schülern der Schule Schloss Gaienhofen

# Projekt bauen

Maven ist ein Build-Management-Tool der Apache Software Foundation und basiert auf Java. 
Dieses Tool setzen wir auch für dieses Projekt ein.

## Maven Befehle

Mit ```mvnw install``` werden die Projektabhängigkeiten aus dem Internet geladen:

```bash
> cd <Projektverzeichnis>

> mvnw install

```

### Projekt bauen und starten

Mit ```mvnw package``` wird das komplette Projekt zusammengebaut und kann dann
mit dem Befehl ```java -jar target/project2019-0.0.1-SNAPSHOT.jar``` gestartet werden:

```bash
> cd <Projektverzeichnis>
> mvnw package
> java -jar target/project2019-0.0.1-SNAPSHOT.jar

```

Man kann das Projekt auch direkt aus der Entwicklungsumgebung starten.

