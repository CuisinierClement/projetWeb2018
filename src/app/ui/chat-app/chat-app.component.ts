import { Component } from '@angular/core';
import { AngularFireDatabase, FirebaseListObservable } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';
import { AngularFireAuthProvider } from 'angularfire2/auth';
import {User} from 'firebase/app';
// import { AngularFire, AuthProviders, AuthMethods } from 'angularfire2';
import {AfterViewChecked, ElementRef, ViewChild, OnInit} from '@angular/core';

export class Thread {
  messages: Message[];
  userA: Observable<User>;
  userB: Observable<User>;
}
export class Message {
  text: string;
  author: string;
}
const MSG: Message[] = [
  { text: 'Hola', author: 'Alice' },
  { text: 'Hello', author: 'Bob' },
];
@Component({
  selector: 'app-chat',
  templateUrl: './chat-app.component.html',
  styleUrls: ['./chat-app.component.css']
})
export class ChatAppComponent implements OnInit, AfterViewChecked  {
  @ViewChild('windowChat') private myScrollContainer: ElementRef;

  user: Observable<firebase.User>;
  allUsers: FirebaseListObservable<any[]>;
  items: FirebaseListObservable<any[]>;
  name: any;
  msgVal: string = '';

  constructor(public afAuth: AngularFireAuth, public af: AngularFireDatabase) {
    this.items = af.list('/messages', {
      query: {
        limitToLast: 200
      }});
    this.allUsers = af.list('/users');
    this.user = this.afAuth.authState;
    this.name = 'Anonymous';
  }
  ngOnInit() {
    this.scrollToBottom();
  }
  ngAfterViewChecked() {
    this.scrollToBottom();
  }
  scrollToBottom(): void {
    try {
      this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    } catch (err) { }
  }
  chatSend(message: string) {
    // this.name = 'Anonymous';
    if (this.afAuth.auth.currentUser.displayName) {
      this.name = this.afAuth.auth.currentUser.displayName;
    }
    this.items.push({ message: message, name: this.name});
    this.msgVal = null;
  }
}
