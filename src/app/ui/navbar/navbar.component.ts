import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalService} from 'ngx-bootstrap/modal';
import {BsModalRef} from 'ngx-bootstrap/modal/modal-options.class';
import {UserService} from '../../services/user/user.service';
import {AuthService} from '../../core/auth.service';
import { generateColor } from '../../util/color';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavBarComponent implements OnInit {

  modalRef: BsModalRef;
  avatarColor: string;
  constructor(public auth: AuthService, private modalService: BsModalService, private _userSvc: UserService) {
    if (this.auth.currentUser !== null) {
      this.avatarColor = generateColor(this.auth.currentUser.displayName);
    }else {
      this.avatarColor = 'white';
    }
  }

  public openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  logout() {
    this._userSvc.auth.signOut();
  }

  ngOnInit() {
  }
}
