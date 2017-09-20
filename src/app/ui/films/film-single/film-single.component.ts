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

  constructor(private itemSvc: FilmsService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.itemId = params['id'];
      console.log(this.itemId);
    });

    this.item = this.itemSvc.getSingleFilm(this.itemId);
    this.item.subscribe(() => this.showSpinner = false);
    console.log(this.item);
  }

}
