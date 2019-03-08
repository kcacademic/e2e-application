import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable()
export class WordService {
	
  private endpoint = environment.apiUrl

  constructor(private http: HttpClient) { 
  }
  

  private extractData(res: Response) {
    let body = res
    return body || { }
  }
 
  get(): Observable<any> { 
    let headers = new HttpHeaders()
    headers = headers.append("Authorization", "Basic " + btoa("admin:admin"))
    headers = headers.append("Content-Type", "application/x-www-form-urlencoded")

    let response = this.http.get(this.endpoint + `/api/words`, {headers: headers}) 
    console.log(response)
    return response.pipe(map(this.extractData))
  }

}