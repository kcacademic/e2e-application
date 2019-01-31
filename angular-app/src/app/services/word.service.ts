import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable()
export class WordService {
	
  private endpoint = environment.apiUrl;

  constructor(private http: HttpClient) { 
  }
  

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }
 
  get(): Observable<any> {  
	  console.log(this.http.get(this.endpoint + `/api/words`));
    return this.http.get(this.endpoint + `/api/words`).pipe(
      map(this.extractData));
  }

}