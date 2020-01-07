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

## Running Application
You can directly run the FuberApplication or can use sprinboot run

## Important libraries used
Following libraries and technologies have been uses
<table>
    <tr>
        <td><b>Documentation:</b></td>
        <td><a href="https://swagger.io/">swagger</a></td>
        </tr>
    <tr>
        <td><b>Mapstruct:</b></td>
        <td><a href="https://mapstruct.org/">Mapping DTO to entity and vice versa</a></td>
    </tr>
    <tr>
      <td><b>Database Migration:</b></td>
      <td><a href="https://flywaydb.org/">Used flyway</a></td>
    </tr>
    <tr>
      <td><b>Lombok plugins:</b></td>
      <td><a href="https://projectlombok.org/">For building models etc. </a></td>
    </tr>
    <tr>
      <td><b>Integration test:</b></td>
      <td><a href="https://projectlombok.org/">Spock framework </a></td>
    </tr>
</table>
