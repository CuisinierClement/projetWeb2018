import {Injectable} from "@angular/core";
 import {Http, Response} from "@angular/http";
 import {Observable} from "rxjs/Observable";
 import "rxjs/Rx";
 
 @Injectable()
 export class FilmsService {
 
     private _URLGetPopularFilms = "https://api.themoviedb.org/3/movie/popular?api_key=107bde7a19539e697a8e9bdd3a4a44af&language=en-US&page=1";
      
     constructor(private http: Http) {
     }
 
     getPopularFilms(): Observable<any> {
         return this.http
             .get(this._URLGetPopularFilms)
             .map((response: Response) => {
                 return <any[]>response.json();
             })
             .catch(this.handleError);
     }
 
     private handleError(error: Response) {
         return Observable.throw(error.statusText);
     }
 }