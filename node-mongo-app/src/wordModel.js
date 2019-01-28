// Import mongoose
var mongoose = require('mongoose');

// Setup schema
var wordSchema = mongoose.Schema({
    word: {
        type: String,
        required: true
    },
    count: {
        type: Number,
        required: true
    }
}, { collection: 'words' });

// Export Word model
var mysort = { count: -1 };
var Word = module.exports = mongoose.model('word', wordSchema);
module.exports.get = function (callback, limit) {
    Word.find(callback).sort(mysort).limit(25);
}