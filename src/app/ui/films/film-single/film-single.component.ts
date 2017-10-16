import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {FilmsService} from '../../../services/films/films.service';

@Component({
  selector: 'film-single',
  templateUrl: './film-single.component.html',
  styleUrls: ['./film-single.component.css']
})
export class FilmSingleComponent implements OnInit {
  item: any;
  itemId: string;
  showSpinner = true;
  title: string;
  release_date: string;
  overview: string;
  poster_path: string;
  budget: number;
  revenue: number;
  original_title: string;
  production_companies: any[];
  genres: any[];
  backdrop_path: string;
  pourcent: number;
  runtime: string;
  vote_average: string;
  vote_count: string;

  constructor(private _itemSvc: FilmsService, private _activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this._activatedRoute.params.subscribe((params: Params) => {
      this.itemId = params['id'];
    });

    this._itemSvc.getSingleFilm(this.itemId).subscribe(item => {
      this.original_title = item.original_title;
      this.production_companies = item.production_companies;
      this.genres = item.genres;
      this.backdrop_path = item.backdrop_path;
      this.runtime = item.runtime;
      this.vote_average = item.vote_average;
      this.vote_count = item.vote_count;
      this.poster_path = item.poster_path;
      this.budget = item.budget;
      this.revenue = item.revenue;
      this.pourcent = item.revenue/item.budget*100;
      this.title = item.title;
      this.release_date = item.release_date;
      this.overview = item.overview;
      this.showSpinner = false;
    });
  }
}
