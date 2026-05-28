#!/usr/bin/env bash
set -euo pipefail

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 <path-to-java-file>"
  exit 2
fi

FILE_PATH="$1"
IMAGE_NAME="submission-runner-java:latest"

# Build image if it doesn't exist
if ! docker image inspect "$IMAGE_NAME" > /dev/null 2>&1; then
  echo "Building Java sandbox image ($IMAGE_NAME)..."
  docker build -t "$IMAGE_NAME" docker/java
fi

ABS=$(realpath "$FILE_PATH")
SUB_DIR=$(dirname "$ABS")
BASE_NAME=$(basename "$ABS")
CLASS_NAME="${BASE_NAME%.java}"

# Run container with restrictions: no network, limited memory/CPUs, no new privileges
# Mount submission dir (writable for javac to produce .class files)
docker run --rm \
  --network none \
  --pids-limit=64 \
  --memory=256m \
  --cpus=0.5 \
  --security-opt no-new-privileges \
  -v "$SUB_DIR":/submission \
  -w /submission \
  "$IMAGE_NAME" bash -lc "javac '$BASE_NAME' && timeout 5s java -cp . '$CLASS_NAME'"
