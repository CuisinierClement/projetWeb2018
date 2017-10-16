import {Component, OnInit} from "@angular/core";
import { FilmsService } from "../../../services/films/films.service";

 @Component({
     selector: 'latest-film-list',
     templateUrl: '../film-popular-list/film-popular-list.component.html',
     styleUrls: ['./film-latest-list.component.scss'],
     providers: [ FilmsService ]
 })
 export class FilmLatestListComponent implements OnInit {
     _filmsArray: any[];

     constructor(private filmsService: FilmsService) {
     }

     getLatestFilms(): void {
         this.filmsService.getLatestFilms()
             .subscribe(
                 resultArray => this._filmsArray = resultArray,
                 error => console.log("Error :: " + error)
             )
     }

     ngOnInit(): void {
         this.getLatestFilms();
     }
 }
