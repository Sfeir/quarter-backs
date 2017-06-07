FROM openjdk:8-jre

ENV SCM_VERSION 1.54
ENV SCM_URL https://maven.scm-manager.org/nexus/content/repositories/releases/sonia/scm/scm-server


WORKDIR /opt/scm-server/
RUN curl -SL ${SCM_URL}/${SCM_VERSION}/scm-server-${SCM_VERSION}-app.tar.gz | tar -xz -C /opt/

RUN useradd -m scm && chown -R scm:scm /opt/scm-server/work

USER scm
EXPOSE 8080
VOLUME /home/scm/
CMD [ "/opt/scm-server/bin/scm-server"]
