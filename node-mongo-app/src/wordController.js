// Import contact model
Word = require('./wordModel');

// Handle index actions
exports.index = function (req, res) {
    Word.get(function (err, words) {
        if (err) {
            res.json({
                status: "error",
                message: err,
            });
        }
		console.log(words)
        res.json(
            words
        );
    });
};