import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared.module';
import { AngularFireDatabaseModule } from 'angularfire2/database';

import { UploadService } from './upload.service';
import { UploadFormComponent } from '../../ui/uploads/upload-form/upload-form.component';
import { UploadsListComponent } from '../../ui/uploads/uploads-list/uploads-list.component';
import { UploadDetailComponent } from '../../ui/uploads/upload-detail/upload-detail.component';

import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', component: UploadsListComponent }
];

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    AngularFireDatabaseModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    UploadFormComponent,
    UploadsListComponent,
    UploadDetailComponent,
  ],
  providers: [
    UploadService
  ]
})
export class UploadModule { }
