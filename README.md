# Kotlin + DropWizard + Postgres + Heroku demo application

This is a simple application showing how to deploy a database-backed web application written in Kotlin on Heroku.
Click on the screenshot below to run the app:

[![Landing page](https://github.com/cbeust/koolaid/blob/master/src/main/resources/assets/screenshot.png?raw=true)](https://shrouded-anchorage-65494.herokuapp.com/)

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

This app is also currently running on Heroku [here](https://shrouded-anchorage-65494.herokuapp.com/).

## Deploying on Heroku

You can deploy this application on your own Heroku instance by pressing this button:

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy)

## Development

This application will automatically detect its environment and deploy correctly:

- If run locally, it will use an in-memory persistence for the counter.
- If deployed on Heroku, it will use a Postgres database.

If you want to develop locally on your own Postgres instance, copy the file
[`local.properties.sample`](https://github.com/cbeust/koolaid/blob/master/local.properties.sample) to `local.properties` and adjust the following values
in order to connect to your local PostgreSQL instance:

```
# local.properties

# Valid: postgres | inMemory
DATABASE=postgres

DATABASE_USER=foo
DATABASE_PASSWORD=bar
```

Access to the `local.properties` file is managed by [`LocalProperties`](https://github.com/cbeust/koolaid/blob/master/src/main/kotlin/com/beust/koolaid/LocalProperties.kt).

## Architecture

Persistence is abstracted by the [`ViewsDao`](https://github.com/cbeust/koolaid/blob/master/src/main/kotlin/com/beust/koolaid/ViewsDao.kt) interface, which is implemented by both [`ViewsDaoPostgres`](https://github.com/cbeust/koolaid/blob/master/src/main/kotlin/com/beust/koolaid/ViewsDaoPostgres.kt) and [`ViewsDaoInMemory`](https://github.com/cbeust/koolaid/blob/master/src/main/kotlin/com/beust/koolaid/ViewsDaoPostgres.kt).

Which implementation to use is decided by [`DemoModule`](https://github.com/cbeust/koolaid/blob/master/src/main/kotlin/com/beust/koolaid/DemoModule.kt) based on the environment: if 
running on Heroku or locally with the Postgres coordinates defined, use Postgres, otherwise use the in-memory
DAO. The correct DAO is then injected in the [`ViewService`](https://github.com/cbeust/koolaid/blob/master/src/main/kotlin/com/beust/koolaid/ViewService.kt) by Guice.

Whenever the landing page is refreshed, the `vue.js` app defined in [`demo.js`](https://github.com/cbeust/koolaid/blob/master/src/main/resources/assets/js/demo.js) makes an HTTP call to [`/api/v0/views`](https://shrouded-anchorage-65494.herokuapp.com/api/v0/views) which returns the
number of views, increments that counter, and saves it into the `ViewsDao` object.
