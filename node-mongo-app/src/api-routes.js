// Initialize express router
let router = require('express').Router();

// Set default API response
router.get('/', function (req, res) {
    res.json({
        status: 'API Its Working',
        message: 'Welcome to RESTHub crafted with love!',
    });
});

// Import word controller
var wordController = require('./wordController');

// Word routes
router.route('/words').get(wordController.index);
	
// Export API routes
module.exports = router;