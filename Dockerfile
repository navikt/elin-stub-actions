FROM navikt/java:8
LABEL maintainer="bidrag" \
      email="nav.ikt.prosjekt.og.forvaltning.bidrag@nav.no"

ADD ./target/elin-stub-ws.jar app.jar

EXPOSE 8085
