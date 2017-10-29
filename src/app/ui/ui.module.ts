import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../services/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { UserLoginComponent } from './user/user-login/user-login.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserFormComponent } from './user/user-form/user-form.component';
import { AvatarModule } from 'ngx-avatar';

import { TopNavComponent } from './top-nav/top-nav.component';
import { FooterNavComponent } from './footer-nav/footer-nav.component';
import { ReadmePageComponent } from './readme-page/readme-page.component';
import { NavBarComponent } from './navbar/navbar.component';
import { FilmPopularListComponent } from './films/film-popular-list/film-popular-list.component';
import { FilmTopRatedListComponent } from './films/film-toprated-list/film-toprated-list.component';
import { FilmUpComingListComponent } from './films/film-upcoming-list/film-upcoming-list.component';
import { FilmPlayingListComponent } from './films/film-playing-list/film-playing-list.component';
import { FilmLatestListComponent } from './films/film-latest-list/film-latest-list.component';
import { FilmSingleComponent } from './films/film-single/film-single.component';
import { FilmSearchListComponent } from './films/film-search-list/film-search-list.component';
import { UpvoteButtonComponent } from './upvote/upvote-button.component';


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    AvatarModule,
    BrowserAnimationsModule
  ],
  declarations: [
    UserLoginComponent,
    UserProfileComponent,
    TopNavComponent,
    FooterNavComponent,
    UserFormComponent,
    ReadmePageComponent,
    NavBarComponent,
    FilmPopularListComponent,
    FilmTopRatedListComponent,
    FilmUpComingListComponent,
    FilmPlayingListComponent,
    FilmLatestListComponent,
    FilmSingleComponent,
    FilmSearchListComponent,
    UpvoteButtonComponent
  ],
  exports: [
    TopNavComponent,
    FooterNavComponent,
    UserProfileComponent,
    NavBarComponent,
    FilmPopularListComponent,
    FilmTopRatedListComponent,
    FilmUpComingListComponent,
    FilmPlayingListComponent,
    FilmLatestListComponent,
    FilmSearchListComponent,
    UpvoteButtonComponent
  ]
})
export class UiModule { }
