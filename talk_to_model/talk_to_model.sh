#!/bin/bash

# Function to check if a command exists
command_exists() {
  command -v "$1" >/dev/null 2>&1
}

# Function to install required commands using package managers
install_command() {
  local package_manager=$1
  local package_name=$2

  if command_exists "$package_manager"; then
    sudo "$package_manager" install -y "$package_name"
  else
    echo "Unsupported package manager. Please install '$package_name' manually." >&2
    exit 1
  fi
}

# Function to check and install dependencies
install_dependencies() {
  local dependencies=("curl" "jq")
  local installed_deps=""

  echo "Checking and installing dependencies..."

  for dep in "${dependencies[@]}"; do
    if ! command_exists "$dep"; then
      installed_deps+=" $dep"
    fi
  done

  if [ -n "$installed_deps" ]; then
    if command_exists "apt-get"; then
      sudo apt-get update
      install_command "apt-get" "$installed_deps"
    elif command_exists "yum"; then
      install_command "yum" "$installed_deps"
    elif command_exists "brew"; then
      install_command "brew" "$installed_deps"
    else
      echo "Unsupported package manager. Please install the following dependencies manually: $installed_deps" >&2
      exit 1
    fi
  fi
}

# Function to perform system cleaning
clean_system() {
  echo "Cleaning system..."
  read -p "Enter the program name to delete: " program_name
  if sudo snap remove "$program_name"; then
    echo "$program_name has been successfully removed."
  else
    echo "Error: Failed to remove $program_name." >&2
  fi
  sleep 2
}

# Authorship details
show_authorship_info() {
  echo "🤖 AI Chat Terminal Interface by Kevin Marville"
  echo "Author's GitHub: https://github.com/kvnbbg"
}

# Main script
install_dependencies

# Chat with AI function
chat_with_ai() {
  local MODEL_ENDPOINT="https://api-inference.huggingface.co/models/t5-small"
  local HF_API_TOKEN=""

  echo "Welcome to the AI Chat! Type 'exit' to end the conversation."

  while true; do
    read -p "You: " user_input

    if [[ "$user_input" == "exit" ]]; then
      echo "Exiting the conversation."
      break
    fi

    if [ -z "$HF_API_TOKEN" ]; then
      echo "Please set your Hugging Face API token." >&2
      exit 1
    fi

    local curl_response
    curl_response=$(curl -s -X POST \
      -H "Authorization: Bearer $HF_API_TOKEN" \
      -H "Content-Type: application/json" \
      -d "{\"inputs\": \"$user_input\"}" \
      "$MODEL_ENDPOINT")

    if [ $? -ne 0 ]; then
      echo "An error occurred. Please check your connection or try again later." >&2
      exit 1
    fi

    local bot_response
    bot_response=$(echo "$curl_response" | jq -r '.[0].generated_text')
    echo "Bot: $bot_response"
  done
}

# Main loop to restart conversation or perform system cleaning
while true; do
  show_authorship_info

  # Display menu
  echo "Select an option:"
  echo "1. Chat with AI"
  echo "2. Clean System"
  echo "3. Exit"

  read -p "Enter your choice: " choice

  case $choice in
    1)
      chat_with_ai
      ;;
    2)
      clean_system
      ;;
    3)
      echo "Goodbye! Hope to chat again soon. 😊"
      break
      ;;
    *)
      echo "Invalid choice. Please enter a valid number." >&2
      sleep 2
      ;;
  esac
done
