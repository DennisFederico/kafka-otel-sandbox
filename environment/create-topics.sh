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

# Read the file line by line
while IFS= read -r line; do
    echo "Creating topic: $line"
    
    kafka-topics --bootstrap-server pkc-mxqvx.europe-southwest1.gcp.confluent.cloud:9092 \
    --command-config ccloud.properties \
    --create \
    --topic $line \
    --partitions 3

done < "$1"