import { Component } from '@angular/core';
import { AngularFireDatabase, FirebaseListObservable } from 'angularfire2/database';
import { AngularFireAuth } from 'angularfire2/auth';
import { Observable } from 'rxjs/Observable';
import * as firebase from 'firebase/app';
import { AngularFireAuthProvider } from 'angularfire2/auth';
import {User} from 'firebase/app';
// import { AngularFire, AuthProviders, AuthMethods } from 'angularfire2';
import {AfterViewChecked, ElementRef, ViewChild, OnInit} from '@angular/core';
import { generateColor } from '../../util/color';

export class Thread {
  messages: Message[];
  userA: Observable<User>;
  userB: Observable<User>;
}
export class Message {
  text: string;
  author: string;
  color: string;
}
const MSG: Message[] = [
  { text: 'Hola', author: 'Alice', color: 'orange' },
  { text: 'Hello', author: 'Bob', color: 'purple' },
];
@Component({
  selector: 'app-chat',
  templateUrl: './chat-app.component.html',
  styleUrls: ['./chat-app.component.css']
})
export class ChatAppComponent implements OnInit, AfterViewChecked  {
  @ViewChild('windowChat') private myScrollContainer: ElementRef;

  user: Observable<firebase.User>;
  // allUsers: FirebaseListObservable<any[]>;
  items: FirebaseListObservable<any[]>;
  name: any;
  msgVal: string = '';
  userEmail: string = '';

  constructor(public afAuth: AngularFireAuth, public af: AngularFireDatabase) {
    this.items = af.list('/messages', {
      query: {
        limitToLast: 200
      }});
    this.user = this.afAuth.authState;
    // this.allUsers = af.list('/users');
    // console.log(this.user);
    // console.log(this.afAuth.auth);
    // this.authUser = this.afAuth.auth;
    // console.log(this.afAuth.auth.currentUser.email);
    if (this.afAuth.auth.currentUser != null) {
      this.userEmail = this.afAuth.auth.currentUser.email;
      this.name = this.afAuth.auth.currentUser.displayName;
    }else {
      this.userEmail = 'anon@tech.com';
      this.name = 'Anonymous';
    }
    // this.userColor = generateColor(this.name);
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
    if (message === '') {
      console.log('empty message');
    }else {
      this.name = 'Anonymous';
      this.userEmail = 'anon@tech.com';
      if (this.afAuth.auth.currentUser.displayName) {
        this.name = this.afAuth.auth.currentUser.displayName;
        this.userEmail = this.afAuth.auth.currentUser.email;
      }
      this.items.push({message: message, name: this.name, email: this.userEmail, color: generateColor(this.name)});
      // this.items.push({ message: message, name: this.name, color: generateColor(this.name)});
      // this.items.push({ message: message, name: this.name});
      this.msgVal = null;
    }
  }
}
