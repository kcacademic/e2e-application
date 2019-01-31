import { Observable, of } from 'rxjs';

export class WordServiceMock {

    get(): Observable<any> {  
        console.log("The mock for WordService has been invoked.");
        return new Observable<any>();
    }
    
}