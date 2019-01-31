// Import contact model
var wordModel = require('./wordModel');

// Handle index actions
exports.index = function (req, res) {
    wordModel.get(function (err, words) {
        if (err) {
            res.json({
                status: "error",
                message: err,
            });
        }
        
		console.log(words);
        res.json(
            words
        );
    }, 10);
};