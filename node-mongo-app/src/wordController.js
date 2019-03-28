//Import predict model
var commandsBuilder = require('./predictModel')

// Import word model
var wordModel = require('./wordModel')

// Import and define logger
var log4js = require('log4js')
log4js.configure( "./config/log4js.json" )
var logger = log4js.getLogger("app")

// Handle index actions
exports.index = function (req, res) {
    wordModel.get(function (err, words) {
        if (err) {
            logger.error(err)
            res.json({
                status: "error",
                message: err,
            });
        }
        logger.debug(words)
        console.log(words)
        commandsBuilder.createMyCommand().execute().then((e)=>{console.log(e)})
        res.json(
            words
        );
    }, 10);
};