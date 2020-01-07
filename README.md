# fuber

fuber-api, REST API

IMPORTANT! You must edit required files
as per your requirements. Please see below list of files you need to go through
before start using application.

## Folder structure + important files

```bash
├── README.md                                   # Important! Read before changing configuration
├── build.gradle
├── settings.gradle
└── src
    ├── main
    │   ├── java                                # Sample REST Controller
    │   └── resources
    │       ├── application.yaml                # Overriding configuration specifc to postgresDB
    │       ├── ...
    │       └── logback-spring.xml              # Logging support with rollbar integration
    └── test
        ├── java                                # Sample Testcase
        └── resources
            └── application-TEST.yaml
```

## Database Migration
To make sure the database is up to date, checkout the latest version of the project and from the root folder run:
```
$ flyway -configFile=./doc/flyway/flyway.conf migrate
```
