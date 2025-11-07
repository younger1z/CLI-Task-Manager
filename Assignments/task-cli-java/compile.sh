#!/usr/bin/env bash
set -euo pipefail
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"
mkdir -p bin
javac -d bin $(find src -name "*.java")
echo "Compiled to $SCRIPT_DIR/bin"

