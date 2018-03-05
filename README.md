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

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

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

The persistence is abstracted by the `ViewsDao` interface, which is implemented by both `ViewDaoPostgres`and `ViewsDaoInMemory`. Which implementation to use is decided by `DemoModule` based on the environment: if run
on your local machine, use the in-memory instance, otherwise (Heroku), use PostgreSQL. If you have
PostgreSQL running on your local machine, you can configure the app to use it by creating a file `local.properties`
(which is ignored by git) with the following content:

```
# Valid: postgresql | inMemory
DATABASE=postgresql

DATABASE_USER=postgres
DATABASE_PASSWORD=admin
```

Access to this file is managed by `LocalProperties`.

Whenever the landing page is refreshed, the `vue.js` app inside makes an HTTP call to `/v0/views` which returns the
number of views, increments that counter and saves it into the `ViewsDao` object.
