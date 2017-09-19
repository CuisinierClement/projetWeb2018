import { Component } from '@angular/core';
import { AngularFireDatabase, FirebaseListObservable } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';
import { AngularFireAuthProvider } from 'angularfire2/auth';
// import { AngularFire, AuthProviders, AuthMethods } from 'angularfire2';

@Component({
  selector: 'app-chat',
  templateUrl: './chat-app.component.html',
  styleUrls: ['./chat-app.component.css']
})
export class ChatAppComponent {
  title = 'app';
  user: Observable<firebase.User>;
  items: FirebaseListObservable<any[]>;
  name: any;
  msgVal: string;

  // constructor(public afAuth: AngularFireAuth, public af: AngularFireDatabase) {
  //   this.items = af.list('/messages', {
  //     query: {
  //       limitToLast: 50
  //     }
  //   });
  //
  //   this.user = this.afAuth.authState;
  // }
  constructor(public afAuth: AngularFireAuth, public af: AngularFireDatabase) {
    this.items = af.list('/messages', {
      query: {
        limitToLast: 200
      }
    });
    this.user = this.afAuth.authState;
    // this.name = this.afAuth.auth.currentUser.displayName;
    this.name = 'name';
    // this.afAuth.auth.suscribe(auth => {
    //   if (auth) {
    //     this.name = auth;
    //   }
    // });
  }
  login() {
     this.afAuth.auth.signInAnonymously(); //ok
    // this.af.auth.login({ //not ok
    //   provider: AuthProviders.Facebook,
    //   method: AuthMethods.Popup,
    // });
    // this.afAuth.auth.signInWithPopup(new firebase.auth.GoogleAuthProvider());//ok
    // this.name = 'user';
    // this.name = this.afAuth.auth.currentUser.displayName;
  }

  logout() {
    // this.af.auth.signOut();
    this.afAuth.auth.signOut();
  }

  Send(desc: string) {
    this.items.push({ message: desc});
    this.msgVal = '';
  }
  chatSend(theirMessage: string) {
    // this.items.push({ message: theirMessage, name: this.name.facebook.displayName});
    // this.name = this.afAuth.auth.currentUser.displayName;
    this.name = this.afAuth.auth.currentUser.displayName;
    this.items.push({ message: theirMessage, name: this.name});
    this.msgVal = '';
  }
}
