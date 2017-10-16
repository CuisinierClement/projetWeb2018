import {Component, OnInit} from "@angular/core";
import { FilmsService } from "../../../services/films/films.service";
 
 @Component({
     selector: 'upcoming-film-list',
     templateUrl: './film-search-list.component.html',
     styleUrls: ['./film-search-list.component.scss'],
     providers: [ FilmsService ]  
 })
 export class FilmSearchListComponent implements OnInit {
     _filmsArray: any[];
     _query: string;
 
     constructor(private filmsService: FilmsService) {
     }
 
     getSearchFilm(query: string): void {
         this.filmsService.getSearchFilm(query)
             .subscribe(
                 resultArray => this._filmsArray = resultArray,
                 error => console.log("Error :: " + error)
             )
        console.log("resultArray "+this._filmsArray);
     }
 
     ngOnInit(): void {
     }
 }