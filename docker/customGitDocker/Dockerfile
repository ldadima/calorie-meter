FROM naveenkrsh/gradle-nodejs-12-jdk-11:v0.3

RUN curl -L https://download.docker.com/linux/static/stable/x86_64/docker-19.03.8.tgz | tar -xz -C /tmp
RUN mv /tmp/docker/* /usr/bin
RUN rm -rf /tmp/docker
RUN curl -L https://github.com/docker/compose/releases/download/1.26.0/docker-compose-`uname -s`-`uname -m` -o /usr/bin/docker-compose
RUN chmod +x /usr/bin/docker-compose