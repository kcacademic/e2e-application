// Import express
let express = require('express')

// Initialize the app
let app = express()

// Import Body parser
let bodyParser = require('body-parser')

// Import cors
var cors = require('cors')

// Import basic-auth
let basicAuth = require('express-basic-auth')

// Import routes
let apiRoutes = require("./api-routes")

// Allow CORS
app.use(cors())

// Configure bodyparser to handle post requests
app.use(bodyParser.urlencoded({
   extended: true
}))
app.use(bodyParser.json())

// Use basic HTTP auth to secure the api
app.use(basicAuth({
  users: { 'admin': 'password' },
  challenge: true
}))

// Send message for default URL
app.use('/',apiRoutes)

// Use Api routes in the App
app.use('/api', apiRoutes)

// Use Api routes in the App
app.use('/metrics', apiRoutes)

// Use Api routes in the App
app.use('/hystrix.stream', apiRoutes)

// Launch app to listen to specified port
var port = process.env.PORT || 8085;
module.exports = app.listen(port, function () {
     console.log("Running App on port " + port)
})