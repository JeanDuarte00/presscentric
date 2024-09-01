# PressCentric Assessment - Backend application

### API should expose operations related to user


#### To run application

- Please, clone this project into your favorite folder
- Make sure to be running on Unix based OS
- In root folder, execute: `bash run.sh`
- Once our application is running locally, click: [Play Ground](http://localhost:8081/graphiql?path=/graphql)

#### Some queries and mutation for testing
`mutation{update(id:"39cdbd92-bb13-4a93-bc0a-b504c3c84bf2",input:{name:"nome",email:"jean@gmail.com"}){id name email}}`

`mutation{create(input:{name:"nome",email:"jean@gmail.com"}){id name email}}`

`mutation{delete(id:"39cdbd92-bb13-4a93-bc0a-b504c3c84bf2"){id name email}}`

`query{getAll{id name email}}`

`query{get(id:"39cdbd92-bb13-4a93-bc0a-b504c3c84bf2"){id name email}}`


#### Improvements I would do

- I would add more testing, especially, integration with test containers (which is quite different to work in graphql compared to rest api)# presscentric

More information about PressCentric can be found [here](https://www.presscentric.com/)
