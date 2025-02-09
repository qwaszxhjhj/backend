FROM ubuntu:latest
LABEL authors="natew"

ENTRYPOINT ["top", "-b"]