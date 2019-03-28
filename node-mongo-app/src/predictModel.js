var commandFactory = require("hystrixjs").commandFactory

module.exports =    class CommandsBuilder {
    static createMyCommand(){
        return commandFactory.getOrCreate("my-command-name")
            .run(runFn)
            .fallbackTo(fallbackFn)
            .build()
    }
};

var request = require("request");

function runFn() {
    return new Promise(function(resolve, reject) {
        request.post("http://localhost:5000/predict", (error, response, body) => {
            if (error) {
                reject(error)
            } else {
                resolve(JSON.parse(body))
            }
        })
    })
}

function fallbackFn() {
    return Promise.resolve("Hello World!")
}