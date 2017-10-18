import {Component, OnInit} from "@angular/core";
import { FilmsService } from "../../../services/films/films.service";

 @Component({
     selector: 'toprated-film-list',
     templateUrl: '../film-toprated-list/film-toprated-list.component.html',
     styleUrls: ['./film-toprated-list.component.scss'],
     providers: [ FilmsService ]
 })
 export class FilmTopRatedListComponent implements OnInit {
     _filmsArray: any[];

     constructor(private filmsService: FilmsService) {
     }

     getTopRatedFilms(): void {
         this.filmsService.getTopRatedFilms()
             .subscribe(
                 resultArray => this._filmsArray = resultArray.results,
                 error => console.log("Error :: " + error)
             )
     }

     ngOnInit(): void {
         this.getTopRatedFilms();
     }
 }
