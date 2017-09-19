import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Item } from '../../../services/items/item';
import { ItemService } from '../../../services/items/item.service';

@Component({
  selector: 'item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.scss']
})
export class ItemFormComponent implements OnInit {

  item: Item = new Item();

  constructor(private itemSvc: ItemService) { }

  ngOnInit() {
  }

  createItem() {
    this.itemSvc.createItem(this.item)
    this.item = new Item() // reset item
  }

}
