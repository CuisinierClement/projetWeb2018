import { BrowserModule } from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


///// Start FireStarter
import { environment } from '../environments/environment';
import { AngularFireModule } from 'angularfire2';
export const firebaseConfig = environment.firebaseConfig;

// Core
import { CoreModule } from './core/core.module';

// Shared/Widget
import { SharedModule } from './services/shared.module'

// Feature Modules
import { ItemModule } from './services/items/item.module';
import { UploadModule } from './services/uploads/upload.module';
import { UiModule } from './ui/ui.module';

import { AlertModule } from 'ngx-bootstrap';
import { ModalModule } from 'ngx-bootstrap';
///// End FireStarter


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    AlertModule.forRoot(),
    ModalModule.forRoot(),
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    CoreModule,
    SharedModule,
    ItemModule,
    UiModule,
    AngularFireModule.initializeApp(firebaseConfig),
  ],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA]
})
export class AppModule { }
