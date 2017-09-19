import {Component, OnInit} from "@angular/core";
import { FilmsService } from "../../../services/films/films.service";
 
 @Component({
     selector: 'popular-film-list',
     templateUrl: './film-popular-list.component.html',
     styleUrls: ['./film-popular-list.component.scss'],
     providers: [ FilmsService ]  
 })
 export class FilmPopularListComponent implements OnInit {
     _filmsArray: any[];
 
     constructor(private filmsService: FilmsService) {
     }
 
     getPopularFilms(): void {
         this.filmsService.getPopularFilms()
             .subscribe(
                 resultArray => this._filmsArray = resultArray.results,
                 error => console.log("Error :: " + error)
             )
     }
 
     ngOnInit(): void {
         this.getPopularFilms();
     }
 }