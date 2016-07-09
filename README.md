# Akka Kamon

For a detail description read [the blog post](http://mukis.de/pages/monitoring-akka-with-kamon/).

# Run it

Run the docker container with all the statsd backend/frontend stuff

```bash
docker run \
  --detach \
   --publish=80:80 \
   --publish=81:81 \
   --publish=8125:8125/udp \
   --publish=8126:8126 \
   --name kamon-grafana-dashboard \
   kamon/grafana_graphite
```

In another terminal start the application

```
sbt run
```

Go to [localhost](http://localhost) and configure your dashboard.

![Dashboard Example](https://raw.githubusercontent.com/wiki/muuki88/activator-akka-kamon/example-dashboard.png)

# Setup Activator

1. [Download Lightbend Activator](http://www.lightbend.com/activator/download) (or copy it over from a USB)
2. Extract the zip and run the `activator` or `activator.bat` script from a non-interactive shell
3. Your browser should open to the Activator UI: [http://localhost:8888](http://localhost:8888)

