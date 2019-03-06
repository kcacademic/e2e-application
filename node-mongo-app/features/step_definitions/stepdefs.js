const { Given, When, Then } = require('cucumber')

let chai = require('chai')
let chaiHttp = require('chai-http')
let chaiJson = require('chai-json')
let expect = chai.expect

chai.use(chaiHttp)
chai.use(chaiJson);

let server = require('../../src/index')

Given('Request is sent to api', function () {
    this.requester = chai.request(server)
    this.request = chai.request(server).get("/api/words")
    return this.request
});

When('Response is generated', function () {
    return this.request.then((res)=>{
        console.log(res.statusCode)
        expect(res.statusCode).to.equal(200)
    })
});

Then('There is a list of {int} words or less in the response', function (int) {
    return this.request.then((res)=>{
        console.log(res.text)
        expect(res.body).to.be.a.jsonObj().and.to.be.lengthOf(int);
    })
});