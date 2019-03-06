var {After, Before} = require('cucumber');

After(function () {
  return this.driver.quit();
});