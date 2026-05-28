#!/usr/bin/env bash
set -euo pipefail

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 <path-to-submission-file>"
  exit 2
fi

FILE_PATH="$1"
IMAGE_NAME="submission-runner:latest"

# Build image if it doesn't exist
if ! docker image inspect "$IMAGE_NAME" > /dev/null 2>&1; then
  echo "Building sandbox image ($IMAGE_NAME)..."
  docker build -t "$IMAGE_NAME" docker
fi

ABS=$(realpath "$FILE_PATH")
SUB_DIR=$(dirname "$ABS")
BASE_NAME=$(basename "$ABS")

# Run container with restrictions: no network, limited memory/CPUs, no new privileges
# We mount submission dir read-only into /submission and run Python inside container
docker run --rm \
  --network none \
  --pids-limit=64 \
  --memory=128m \
  --cpus=0.5 \
  --security-opt no-new-privileges \
  -v "$SUB_DIR":/submission:ro \
  -w /submission \
  "$IMAGE_NAME" timeout 5s python /submission/"$BASE_NAME"
