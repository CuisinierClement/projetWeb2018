import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import * as Constantes from '../../constantes';
import 'rxjs/Rx';

@Injectable()
export class FilmsService {

  constructor(private http: Http) {
  }

  getPopularFilms(): Observable<any> {
    return this.http
      .get(Constantes.URL_GET_POPULAR_FILMS)
      .map((response: Response) => {
        return <any[]>response.json();
      })
      .catch(this.handleError);
  }

  getLatestFilms(): Observable<any> {
    return this.http
      .get(Constantes.URL_GET_LATEST_FILMS)
      .map((response: Response) => {
        return <any[]>response.json();
      })
      .catch(this.handleError);
  }

  getPlayingFilms(): Observable<any> {
    return this.http
      .get(Constantes.URL_GET_PLAYING_FILMS)
      .map((response: Response) => {
        return <any[]>response.json();
      })
      .catch(this.handleError);
  }

  getUpcomingFilms(): Observable<any> {
    return this.http
      .get(Constantes.URL_GET_UPCOMING_FILMS)
      .map((response: Response) => {
        return <any[]>response.json();
      })
      .catch(this.handleError);
  }

  getTopRatedFilms(): Observable<any> {
    return this.http
      .get(Constantes.URL_GET_TOP_RATED_FILMS)
      .map((response: Response) => {
        return <any[]>response.json();
      })
      .catch(this.handleError);
  }

  getSingleFilm(key: string): Observable<any> {
    return this.http
      .get(`https://api.themoviedb.org/3/movie/${key}?api_key=107bde7a19539e697a8e9bdd3a4a44af&language=en-US`)
      .map((response: Response) => {
        return response.json();
      })
      .catch(this.handleError);
  }


  private handleError(error: Response) {
    return Observable.throw(error.statusText);
  }
}
