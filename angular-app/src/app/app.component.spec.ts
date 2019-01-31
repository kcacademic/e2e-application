import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { TagCloudModule } from 'angular-tag-cloud-module';
import { HttpClientModule } from '@angular/common/http';
import { WordService } from './services/word.service';
import { WordServiceMock } from './mocks/word.service.mock';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        TagCloudModule,
        HttpClientModule
      ],
      declarations: [
        AppComponent   
      ],
      providers: [{provide: WordService, useClass: WordServiceMock}],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'Tag Cloud Application'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Tag Cloud Application');
  });

  it('should render title in a h1 tag', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Tag Cloud Application');
  });
});
