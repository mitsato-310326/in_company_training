#!/usr/bin/env bash
set -uo pipefail

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 <submission-dir>"
  exit 2
fi

SUB_DIR=$(realpath "$1")
IMAGE_NAME="submission-runner-java:latest"

if ! docker image inspect "$IMAGE_NAME" > /dev/null 2>&1; then
  docker build -t "$IMAGE_NAME" docker/java > /dev/null 2>&1
fi

docker run --rm \
  --network none \
  --pids-limit=64 \
  --memory=256m \
  --cpus=0.5 \
  --security-opt no-new-privileges \
  -v "$SUB_DIR":/submission \
  -w /submission \
  "$IMAGE_NAME" bash -c '
    if ! javac *.java 2>/tmp/ce.txt; then
      cat /tmp/ce.txt >&2
      exit 2
    fi
    timeout 5s java -cp . Tester
  '
