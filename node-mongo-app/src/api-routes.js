// Initialize express router
let router = require('express').Router();

// Initialize promethus client for monitoring
let prometheus = require('prom-client')
prometheus.collectDefaultMetrics()

// Set default API response
router.get('/', function (req, res) {
    res.json({
        status: 'API Its Working',
        message: 'Welcome to RESTHub crafted with love!',
    });
});

router.get('/metrics', function (req, res) {
    res.set('Content-Type', prometheus.register.contentType)
    res.end(prometheus.register.metrics())
});

// Import wordController and route
var wordController = require('./wordController');
router.route('/words').get(wordController.index);

// Import hystrixStream and route
var hystrixStream = require('./hystrix-stream');
router.route('/hystrix.stream').get(hystrixStream.index);
	
// Export API routes
module.exports = router;