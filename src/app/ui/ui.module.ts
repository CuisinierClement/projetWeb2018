import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../services/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { UserLoginComponent } from './user/user-login/user-login.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { UserFormComponent } from './user/user-form/user-form.component';
import { TopNavComponent } from './top-nav/top-nav.component';
import { FooterNavComponent } from './footer-nav/footer-nav.component';
import { ReadmePageComponent } from './readme-page/readme-page.component';
import { NavBarComponent } from './navbar/navbar.component';



@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    BrowserAnimationsModule
  ],
  declarations: [
    UserLoginComponent,
    UserProfileComponent,
    TopNavComponent,
    FooterNavComponent,
    UserFormComponent,
    ReadmePageComponent,
    NavBarComponent
  ],
  exports: [
    TopNavComponent,
    FooterNavComponent,
    UserProfileComponent,
    NavBarComponent
  ]
})
export class UiModule { }
