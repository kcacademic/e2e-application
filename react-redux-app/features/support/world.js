var {setWorldConstructor} = require('cucumber');

const { Builder, Capabilities} = require('selenium-webdriver')
require('chromedriver')

function CustomWorld() {
    this.driver = new Builder().withCapabilities(Capabilities.chrome()).build()
}

setWorldConstructor(CustomWorld)