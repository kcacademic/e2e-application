import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('Tag Cloud Application', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should have correct page title', () => {
    page.navigateTo();
    page.getPageTitle()
      .then((title: string) => {
        expect(title).toEqual('MyAngularApp');
      });
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});
