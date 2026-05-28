FROM eclipse-temurin:17-jdk-jammy

RUN apt-get update && apt-get install -y --no-install-recommends ca-certificates && rm -rf /var/lib/apt/lists/*
RUN useradd -m runner
WORKDIR /home/runner
USER runner

# Image will be used to compile/run Java submissions mounted at /submission
