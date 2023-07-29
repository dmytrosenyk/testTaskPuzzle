FROM ubuntu:latest
RUN apt-get update && apt-get install -y software-properties-common
RUN add-apt-repository universe
RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y openjdk-17-jdk x11vnc xvfb
RUN mkdir ~/.vnc /app
COPY . /app
RUN x11vnc -storepasswd 1234 ~/.vnc/passwd
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
