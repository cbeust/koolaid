# Kotlin + DropWizard + Postgres + Heroku demo application

This is a simple application showing how to deploy a database-backed web application written in Kotlin on Heroku.

## Stack used

- Kotlin
- DropWizard
- PostgreSQL
- Guice
- vue.js

## Building and running

```
./gradlew run
```

Then open `http://localhost`. You should see a page telling you how many times it's been accessed (this counter is saved in PostgreSQL).

## Deploying on Heroku

You can deploy this application on your own Heroku instance by pressing this button:

___

This application can be deployed on the free tier ("hobby") of Heroku.

## Development

This application will automatically detect its environment and deploy correctly:

- If run locally, it will use an in-memory persistence for the counter.
- If deployed on Heroku, it will use a Postgres database.

If you want to develop locally on your own Postgres instance, copy the file
`local.properties.sample` to `local.properties` and adjust the following values
in order to connect to your local PostgreSQL instance:

```
# local.properties

# Valid: postgres | inMemory
DATABASE=postgres

DATABASE_USER=foo
DATABASE_PASSWORD=bar
```

## Architecture

