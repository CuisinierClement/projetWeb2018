import {Component, OnInit} from "@angular/core";
import { FilmsService } from "../../../services/films/films.service";
 
 @Component({
     selector: 'playing-film-list',
     templateUrl: './film-playing-list.component.html',
     styleUrls: ['./film-playing-list.component.scss'],
     providers: [ FilmsService ]  
 })
 export class FilmPlayingListComponent implements OnInit {
     _filmsArray: any[];
 
     constructor(private filmsService: FilmsService) {
     }
 
     getPlayingFilms(): void {
         this.filmsService.getPlayingFilms()
             .subscribe(
                 resultArray => this._filmsArray = resultArray.results,
                 error => console.log("Error :: " + error)
             )
     }
 
     ngOnInit(): void {
         this.getPlayingFilms();
     }
 }