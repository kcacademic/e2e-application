const { Given, When, Then } = require('cucumber')
const expect  = require('chai').expect
var {setDefaultTimeout} = require('cucumber');

setDefaultTimeout(60 * 1000);

Given('Request is sent to homepage', function () {
  return this.driver.get("http://localhost:8080")
});

When('Response is ok', function () {
  // Write code here that turns the phrase above into concrete actions
  // return 'pending';

});

Then('There is a tag cloud page rendered', function () {
  return this.driver.getTitle().then(title => {
    console.log(title)
    expect(title).to.be.eql("Tag Cloud Application")
  })
});
