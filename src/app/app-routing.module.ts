import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/auth.guard';

import { UserLoginComponent } from './ui/user/user-login/user-login.component';
import { ItemsListComponent } from './ui/items/items-list/items-list.component';
// import { UploadsListComponent } from './uploads/uploads-list/uploads-list.component';
import { ReadmePageComponent } from './ui/readme-page/readme-page.component';

import { CoreModule } from './core/core.module'
import {ItemSingleComponent} from './ui/items/item-single/item-single.component';
import {ItemAddComponent} from './ui/items/item-add/item-add.component';
import {FilmSingleComponent} from './ui/films/film-single/film-single.component';
import {FilmPopularListComponent} from './ui/films/film-popular-list/film-popular-list.component';
import {FilmLatestListComponent} from './ui/films/film-latest-list/film-latest-list.component';
import {FilmPlayingListComponent} from './ui/films/film-playing-list/film-playing-list.component';
import {FilmTopRatedListComponent} from './ui/films/film-toprated-list/film-toprated-list.component';
import {FilmUpComingListComponent} from './ui/films/film-upcoming-list/film-upcoming-list.component';
import {FilmSearchListComponent} from './ui/films/film-search-list/film-search-list.component';

const routes: Routes = [
  { path: '', component: FilmPopularListComponent },
  { path: 'latest', component: FilmLatestListComponent },
  { path: 'playing', component: FilmPlayingListComponent },
  { path: 'toprated', component: FilmTopRatedListComponent },
  { path: 'upcoming', component: FilmUpComingListComponent },
  { path: 'login', component: UserLoginComponent, },
  { path: 'items', component: ItemsListComponent},
  { path: 'add', component: ItemAddComponent},
  { path: 'item/:id', component: ItemSingleComponent},
  { path: 'film/:id', component: FilmSingleComponent},
  { path: 'search', component: FilmSearchListComponent},
  { path: 'uploads', loadChildren: './services/uploads/upload.module#UploadModule' }
  // { path: 'uploads', component: UploadsListComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
