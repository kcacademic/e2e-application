#!/usr/bin/env python

import flask


# Create the application.
app = flask.Flask(__name__)


@app.route('/')
def index():
    return flask.render_template('index.html')

@app.route("/predict", methods=["POST"])
def predict():
	# initialize the data dictionary that will be returned
	data = {"sentiment": "Undermined"}

	# ensure the post data is correct
	if flask.request.method == "POST":
		print("POST method invoked")

	# return the data dictionary as a JSON response
	return flask.jsonify(data)


if __name__ == '__main__':
    app.debug=True
    app.run(host='0.0.0.0')