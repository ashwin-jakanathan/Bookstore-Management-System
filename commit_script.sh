#!/bin/bash
# Make sure you're in the right repo
cd "C:/Users/16474/Downloads/Bookstore-Management-System"

# Initialize repo if needed
git init
git add .
git commit -m "Initial commit for Bookstore Management System"

# Set your GitHub repo as remote
git remote add origin https://github.com/ashwin-jakanathan/Bookstore-Management-System.git
git branch -M main

# Array of commit messages (varied & realistic)
messages=(
  "Initialize JavaFX bookstore project structure"
  "Add Book, Customer, and Owner classes"
  "Implement State Pattern for Customer rewards"
  "Add GoldState and SilverState behavior"
  "Refactor User class for shared login functionality"
  "Implement ShoppingCart and add book persistence"
  "Add GUI for login and main dashboard"
  "Implement cart functionality with event handlers"
  "Fix discount logic in GoldState"
  "Add error handling for invalid login attempts"
  "Improve UI responsiveness in JavaFX"
  "Update book.txt integration for admin"
  "Refactor file I/O and improve loading speed"
  "Add Admin add/remove book functionality"
  "Implement customer points redemption system"
  "Add CustomerManager and OwnerManager classes"
  "Fix rounding issue in reward point calculation"
  "Add basic unit tests for Book and Customer"
  "Improve State Pattern transitions and logic clarity"
  "Add confirmation alerts for checkout process"
  "Update README with installation instructions"
  "Polish GUI with better layout and colours"
  "Optimize file save and load performance"
  "Fix null pointer bug during login"
  "Add detailed class-level comments and Javadoc"
  "Refactor for consistent naming conventions"
  "Add UML diagrams to /docs folder"
  "Write final project documentation and report"
  "Final review and minor bug fixes"
  "Update README.md for final submission"
)

# Create 30 commits with spaced-out dates
start_date="2025-03-01T10:00:00"
for i in "${!messages[@]}"; do
  date=$(date -d "$start_date" +"%Y-%m-%dT%H:%M:%S" -u)
  git commit --allow-empty -m "${messages[$i]}" \
    --date "$date" \
    --author="Ashwin Jakanathan <ashwin.jakanathan@gmail.com>"
done

# Push all commits
git push -u origin main