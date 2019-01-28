import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { TagCloudModule } from 'angular-tag-cloud-module';

import { AppComponent } from './app.component';
import { WordService } from './services/word.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    TagCloudModule
  ],
  providers: [WordService],
  bootstrap: [AppComponent]
})
export class AppModule { }
