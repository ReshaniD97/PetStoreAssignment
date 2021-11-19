B G R Dilhari

IndexNumber: 18000411

SCS3203/IS3108 Middleware Architecture

# Pet-Store

## 01. How to build and/or deploy the API

### 1. To build the applictaion

     ./gradlew build

### 2. To deploy and run the application

    java -jar build/petstore-runner.jar

## 02. How to run test suite

    ./gradlew test

## 03. How to run a CURL/WGET command to test the APIs

### Pets

Get all the pets

    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/pets'

Create a new pet

    curl -XPOST -H "Content-type: application/json" -d '{"petAge": 5, "petName": "Hummingbirds", "petType": "Bird"}' 'http://localhost:8080/pets/create_pet' 


Update an existing pet

    curl -XPUT -H "Content-type: application/json" -d '{"petAge": 5, "petName": "Bruna", "petType": "Dog"}' 'http://localhost:8080/pets/update_pet/1'

Delete an existing pet

    curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/pets/delete_pet/1'

Search pets with id/name/age
 
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/pets/search?id=2'
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/pets/search?name=Boola'
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/pets/search?age=2'


### Pet Type
Get all the pet types

    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/pets_type'

Create a new pet type

    curl -XPOST -H "Content-type: application/json" -d '{ "petType": "Dog" }' 'http://localhost:8080/pets_type/create_pet_type'

Update an existing pet type

    curl -XPUT -H "Content-type: application/json" 'http://localhost:8080/pets_type/update_pet_type/1'

Delete an existing pet type

    curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/pets_type/delete_pet_type/1'







