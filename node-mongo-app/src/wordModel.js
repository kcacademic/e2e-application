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
var wordModel = module.exports = mongoose.model('word', wordSchema);

// Export Function to Get all Words
var mysort = { count: -1 };
module.exports.get = function (callback, limit) {
    console.log(limit);
    wordModel.find(callback).sort(mysort).limit(limit);
    //wordModel.find(callback)
}