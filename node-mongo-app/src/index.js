// Import express
let express = require('express')

// Initialize the app
let app = express();

// Import Body parser
let bodyParser = require('body-parser');

// Import Mongoose
let mongoose = require('mongoose');

// Import routes
let apiRoutes = require("./api-routes")

// Setup server port
var port = process.env.PORT || 8085;

// Setup mongodb URL
var mongodb = process.env.MONGODB || 'mongodb://localhost/vocabulary';

// Allow CORS
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

// Configure bodyparser to handle post requests
app.use(bodyParser.urlencoded({
   extended: true
}));

app.use(bodyParser.json());

// Connect to Mongoose and set connection variable
// mongoose.connect('mongodb://host.docker.internal/vocabulary');
mongoose.connect(mongodb);

var db = mongoose.connection;

// Send message for default URL
//app.get('/', (req, res) => res.send('Hello World with Express and Nodemon'));
app.use('/',apiRoutes)

// Use Api routes in the App
app.use('/api', apiRoutes)

// Launch app to listen to specified port
app.listen(port, function () {
     console.log("Running App on port " + port);
});