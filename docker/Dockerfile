FROM python:3.10-slim

RUN apt-get update && apt-get install -y --no-install-recommends coreutils ca-certificates && \
    rm -rf /var/lib/apt/lists/*

RUN useradd -m runner
WORKDIR /home/runner
USER runner

# Image will be used to run submission files mounted at /submission
# We don't set an ENTRYPOINT to keep run-time command flexible
