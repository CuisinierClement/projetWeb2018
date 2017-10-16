import {Component, OnInit} from '@angular/core';
import { FilmsService } from '../../../services/films/films.service';

 @Component({
     selector: 'upcoming-film-list',
     templateUrl: '../film-popular-list/film-popular-list.component.html',
     styleUrls: ['./film-upcoming-list.component.scss'],
     providers: [ FilmsService ]
 })
 export class FilmUpComingListComponent implements OnInit {
     _filmsArray: any[];

     constructor(private filmsService: FilmsService) {
     }

     getUpComingFilms(): void {
         this.filmsService.getUpcomingFilms()
             .subscribe(
                 resultArray => this._filmsArray = resultArray.results,
                 error => console.log('Error :: ' + error)
             )
     }

     ngOnInit(): void {
         this.getUpComingFilms();
     }
 }
