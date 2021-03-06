import { Component, OnInit } from '@angular/core';
import {Item} from "../../../services/items/item";
import {ItemService} from "../../../services/items/item.service";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'item-add',
  templateUrl: './item-add.component.html',
  styleUrls: ['./item-add.component.css']
})
export class ItemAddComponent implements OnInit {

  item: Item = new Item();

  constructor(private itemSvc: ItemService) { }

  ngOnInit() {
  }

  createItem() {
    this.itemSvc.createItem(this.item);
    this.item = new Item(); // reset item
  }

}
