FROM naveenkrsh/gradle-nodejs-12-jdk-11:v0.3

RUN apt install -y docker.io
RUN apt-get install -y docker-ce docker-ce-cli containerd.io
RUN curl -L https://github.com/docker/compose/releases/download/1.26.0/docker-compose-`uname -s`-`uname -m` -o /usr/bin/docker-compose
RUN chmod +x /usr/bin/docker-compose