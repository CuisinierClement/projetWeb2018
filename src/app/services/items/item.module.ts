import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularFireDatabaseModule } from 'angularfire2/database';

import { SharedModule } from '../shared.module';

import { ItemService } from './item.service';
import { ItemsListComponent } from '../../ui/items/items-list/items-list.component';
import { ItemFormComponent } from '../../ui/items/item-form/item-form.component';
import { ItemDetailComponent } from '../../ui/items/item-detail/item-detail.component';
import { ItemAddComponent } from '../../ui/items/item-add/item-add.component';
import {RouterModule} from "@angular/router";
import {ItemSingleComponent} from "../../ui/items/item-single/item-single.component";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
    AngularFireDatabaseModule,
    RouterModule
  ],
  declarations: [
    ItemsListComponent,
    ItemFormComponent,
    ItemAddComponent,
    ItemSingleComponent,
    ItemDetailComponent,
  ],
  providers: [
    ItemService
  ]
})
export class ItemModule { }
