[![Build Status](https://travis-ci.org/Sybit-Education/schloss-gaienhofen-projekt-2019.svg?branch=master)](https://travis-ci.org/Sybit-Education/schloss-gaienhofen-projekt-2019)


# Schloss Gaienhofen - Projekt 2019

Projekt mit Schülern der Schule Schloss Gaienhofen

# Proxy Einstellungen unter Windows setzen

Begebt euch in das Menü 'Systemumgebungsvariablen bearbeiten' und betätigt nun den Knopf 'Umgebungsvariablen..'.
Unter Systemvariablen erstellt ihr nun 2 neue Variablen: 

``Name: http_proxy``
``Wert der Variable: http://<Proxy Server IP>:8080`` 

``Name: https_proxy``
``Wert der Variable: http://<Proxy Server IP>:8080`` 


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

# Entwicklungsumgebungen

## Eclipse

Um das Projekt optimal in Eclipse nutzen zu können benötigt man das Plugin ```Spring Tools 4```

Um das Plugin zu downloaden müsst ihr zunächst die Proxy Einstellungen in Eclipse setzen: Geht dazu in die Preferences (Window -> Preferences)
und wählt nun unter 'General' den Punkt 'Network Connection' aus. Dort stellt ihr dann den 'Active Provider' aus Manual
und trags jeweils für 'HTTP' und 'HTTPS' die Server IP des Proxy Servers ein. Der Port bleibt stets 8080 und der Provider bleibt hier auf Manual.

Startet nun Eclipse neu. Danach solltet ihr in der Lage sein, unter Window -> Eclipse Marketplace das Plugin ```Spring Tools 4``` zu downloaden.
Im Anschluss könnt ihr unter File -> Open Project from File System.. das Projekt auswählen und importieren. 

Nun könnt ihr auf den Pfeil neben dem grünen 'Run' Knopf klicken und auswählen, als was das Projekt gestartet werden soll. Wähl hier 'Spring Boot App'.
Alternativ könnt ihr auf die Application.java Rechtsklick drücken und 'Run as' -> 'Spring Boot App' auswählen.





