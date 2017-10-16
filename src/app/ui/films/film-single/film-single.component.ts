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

  constructor(private _itemSvc: FilmsService, private _activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this._activatedRoute.params.subscribe((params: Params) => {
      this.itemId = params['id'];
    });

    this._itemSvc.getSingleFilm(this.itemId).subscribe(item => {
      this.title = item.title;
      this.release_date = item.release_date;
      this.overview = item.overview;
      this.showSpinner = false;
    });
  }
}
