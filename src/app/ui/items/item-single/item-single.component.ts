import {Component, OnInit, Input} from '@angular/core';
import {ItemService} from '../../../services/items/item.service';
import {Item} from '../../../services/items/item';
import {FirebaseObjectObservable} from 'angularfire2/database';
import {Router, ActivatedRoute, Params} from '@angular/router';
@Component({
  selector: 'item-single',
  templateUrl: './item-single.component.html',
  styleUrls: ['./item-single.component.css']
})
export class ItemSingleComponent implements OnInit {

  item: FirebaseObjectObservable<Item>;
  itemId: string;
  showSpinner = true;

  constructor(private itemSvc: ItemService, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.itemId = params['id'];
      console.log(this.itemId);
    });

    this.item = this.itemSvc.getItem(this.itemId);
    this.item.subscribe(() => this.showSpinner = false);
    console.log(this.item);
  }

}
