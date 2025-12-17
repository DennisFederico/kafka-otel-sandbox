ARG BUILD_HOME=/app

# Builder ############################################
FROM maven:latest AS builder

RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*

ARG OTEL_VERSION=2.23.0
ARG PROMETHEUS_AGENT_VERSION=1.5.0

ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

RUN wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_VERSION}/opentelemetry-javaagent.jar
RUN wget https://github.com/prometheus/jmx_exporter/releases/download/${PROMETHEUS_AGENT_VERSION}/jmx_prometheus_javaagent-${PROMETHEUS_AGENT_VERSION}.jar -O jmx_prometheus_javaagent.jar

COPY lineage-opentel-extensions-0.0.1-SNAPSHOT-all.jar $APP_HOME
#COPY tmp $APP_HOME/tmp
COPY src $APP_HOME/src
COPY config $APP_HOME/config
COPY pom.xml $APP_HOME
RUN mvn  -f $APP_HOME/pom.xml clean package

# App ############################################
FROM eclipse-temurin:17-jdk

COPY --from=builder $APP_HOME/target/fact-wordcount-kstream-jar-with-dependencies.jar app.jar
COPY --from=builder $APP_HOME/config/application-docker.yml application-docker.yml
COPY --from=builder $APP_HOME/config/connection.properties connection.properties

COPY --from=builder $APP_HOME/jmx_prometheus_javaagent.jar jmx_prometheus_javaagent.jar
COPY --from=builder $APP_HOME/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
COPY --from=builder $APP_HOME/lineage-opentel-extensions-0.0.1-SNAPSHOT-all.jar lineage-opentel-extensions-0.0.1-SNAPSHOT-all.jar

EXPOSE 38080
EXPOSE 5000

ARG COLLECTOR_ENDPOINT
ARG APP_NAME

ENV JAVA_TOOL_OPTIONS "-javaagent:opentelemetry-javaagent.jar \
  -Dotel.traces.exporter=otlp,logging \
  -Dotel.metrics.exporter=otlp \
  -Dotel.exporter.otlp.endpoint=${COLLECTOR_ENDPOINT} \
  -Dotel.resource.attributes=service.name=${APP_NAME} \
  -Dotel.javaagent.extensions=lineage-opentel-extensions-0.0.1-SNAPSHOT-all.jar \
  --add-opens=java.base/java.lang=ALL-UNNAMED \
  -Dotel.instrumentation.kafka.experimental-span-attributes=true \
  -Devent.lineage.header-capture-whitelist=kstreams_header,system_id \
  -Devent.lineage.header-propagation-whitelist=kstreams_header,system_id \
  -Devent.lineage.header-charset=UTF-8"
#-Dotel.javaagent.debug=true"

CMD ["java", "-jar", "app.jar","application-docker.yml"]
