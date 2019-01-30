var assert = require('assert');
var sinon = require('sinon');
var expect = require('chai').expect;
var should = require('chai').should();

var wordController = require('../src/wordController');
var wordModel = require('../src/wordModel');

describe('Basic Tests for Node-Mongo App', function () {

    it('should be invalid if word or count is empty', function(done) {
        var word = new wordModel();
        word.validate(function(err) {
            expect(err.errors.word).to.exist;
            expect(err.errors.count).to.exist;
            done();
        });
    });

    beforeEach(function() {
        var sort = { sort: sinon.stub().callsArgWith(0, new Error('error'))  };
        var limit = { limit: sinon.stub().callsArg(0) };
        sinon.stub(wordModel, 'find')
            .returns({sort: sinon.stub().returnsThis(), limit: sinon.stub().returns()});
    });

    afterEach(function() {
        wordModel.find.restore();
    });

    it('should send all words when requested through get', function() {
        var a = { word: 'a', count: 10 };
        var b = { word: 'b', count: 10 };
        var expectedModels = [a, b];
        wordModel.find.yields(null, expectedModels);
        var req = { params: { } };
        var res = {
            json: sinon.stub()
        };
        wordController.index(req, res);
        sinon.assert.calledWith(res.json, expectedModels);
    });

});
