#!/bin/bash

# Start backend
function backend() {
    cd ./backend || exit
    mvn clean compile
    mvn spring-boot:run &
}

# Start frontend
function frontend() {
    cd ../frontend || exit
    npm install
    npm run dev &
}

backend
frontend

wait