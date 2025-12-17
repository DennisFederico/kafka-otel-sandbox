#!/usr/bin/env bash
set -euo pipefail

# Check if a file name was provided as an argument
if [ $# -eq 0 ]; then
    echo "Usage: $0 <filename>"
    exit 1
fi

# Check if the file exists
if [ ! -f "$1" ]; then
    echo "File '$1' does not exist."
    exit 1
fi

# Determine the properties file relative to this script so it works from anywhere
script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROPS="$script_dir/ccloud.properties"

if [ ! -f "$PROPS" ]; then
    echo "Properties file '$PROPS' not found."
    exit 1
fi

# Extract the first non-comment, non-empty line from the properties file
first_line="$(awk '/^[[:space:]]*#/ {next} /^[[:space:]]*$/ {next} {print; exit}' "$PROPS")"

if [ -z "$first_line" ]; then
    echo "No key=value line found in $PROPS"
    exit 1
fi

# Ensure the line contains an '=' and extract the value after the first '='
if ! printf '%s' "$first_line" | grep -q '='; then
    echo "First non-comment line does not contain '=': $first_line"
    exit 1
fi

# Remove everything up to the first '=' and trim whitespace
BOOTSTRAP_SERVER="$(printf '%s' "$first_line" | awk -F'=' '{ $1=""; sub(/^=/,""); print }' | sed 's/^[[:space:]]*//; s/[[:space:]]*$//')"

if [ -z "$BOOTSTRAP_SERVER" ]; then
    echo "Failed to parse bootstrap server from: $first_line"
    exit 1
fi

# Read the file line by line and create topics
while IFS= read -r line; do
    echo "Creating topic: $line"

    kafka-topics --bootstrap-server "$BOOTSTRAP_SERVER" \
    --command-config "$PROPS" \
    --delete \
    --topic $line

done < "$1"