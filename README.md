# BIP---AdaptSort-project

## How to start the server & database

### Prerequisites
- Ensure you have Docker installed and started

### Step 1
- Navigate to /server and run the following command to start the database and the server: **docker compose up --build**   

### Step 3
- Once the database and the server are running, open Windows CMD or an alternative on another OS and send a curl request to the server with the identified object (the command might be different on other OS):    
  curl -X POST http://localhost:8080/api/v1/items -H "Content-Type: application/json" -d "{\"name\":\"Banana\"}"    

  As a response, you should receive the object's id, name and current amount:
  {
	"id": 1,
	"name": "Banana",
	"amount": 1
  }  
