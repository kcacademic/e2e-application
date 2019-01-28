import { Component, OnInit, Input } from '@angular/core';
import { Word } from './models/word';
import { WordService } from './services/word.service';

import { CloudData, CloudOptions } from 'angular-tag-cloud-module';
import { CompileNgModuleMetadata } from '@angular/compiler';
import { jsonpCallbackContext } from '@angular/common/http/src/module';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public words = Array<Word>();

  data: CloudData[] = []

  options: CloudOptions = {
    width : 1000,
    height : 400,
    overflow: false,
  };
  
  constructor(public wordService: WordService) {
    
  }
  
  ngOnInit() {  
	this.wordService.get().subscribe( 
	  (data: {}) => {
        let keys = Object.keys(data);
        let values = Object.values(data);
        let cloud = Array<CloudData>();
        for (let key of keys) { 
          this.words.push(new Word(values[key].word, values[key].count))
          const a :CloudData = { text: values[key].word, weight: (values[key].count) }
          cloud.push(a)
        }
        this.data = cloud;
        console.log(data)
      }
	);
  }
}
