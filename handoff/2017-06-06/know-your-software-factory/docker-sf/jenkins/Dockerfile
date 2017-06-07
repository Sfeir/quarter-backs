FROM jenkins:2.46.3-alpine

# install some plugins for the demo
RUN /usr/local/bin/install-plugins.sh \
  pipeline-stage-view \
  workflow-job:2.10 \
  workflow-multibranch \
  git \
  maven \
  sonar \
  blueocean \
  credentials-binding \
  matrix-auth

# init user admin/admin
ENV JENKINS_USER admin
ENV JENKINS_PASS admin
COPY default-user.groovy /usr/share/jenkins/ref/init.groovy.d/

# Skip initial setup
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Use context-path behind nginx
ENV JENKINS_OPTS --prefix=/jenkins
