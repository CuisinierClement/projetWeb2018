import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { UpvoteService } from '../../services/upvote/upvote.service';
import { sum, values } from 'lodash';
import { AngularFireAuth } from 'angularfire2/auth';
import { Observable } from 'rxjs/Observable';
import {User} from 'firebase/app';

@Component({
  selector: 'upvote-button',
  templateUrl: './upvote-button.component.html',
  styleUrls: ['./upvote-button.component.scss'],
  providers: [ UpvoteService ]
})
export class UpvoteButtonComponent implements OnInit, OnDestroy {
  @Input() userId;
  @Input() itemId;
  voteCount = 0;
  userVote = 0;
  subscription;
  name: any;
  
  user: Observable<firebase.User>;

  constructor(private upvoteService: UpvoteService, public afAuth: AngularFireAuth) { }

  ngOnInit() {
    this.subscription = this.upvoteService.getItemVotes(this.itemId)
      .subscribe(upvotes => {
          this.afAuth.auth.currentUser.displayName;
          
          this.userVote = upvotes[this.afAuth.auth.currentUser.displayName]
          this.voteCount = sum(values(upvotes))
        
      })
  }

  upvote() {
    const vote = this.userVote === 1 ? 0 : 1
    this.upvoteService.updateUserVote(this.itemId, this.afAuth.auth.currentUser.displayName, vote)
  }

  downvote() {
    const vote = this.userVote === -1 ? 0 : 0
    this.upvoteService.updateUserVote(this.itemId, this.afAuth.auth.currentUser.displayName, vote)
  }

  ngOnDestroy() {
    this.subscription.unsubscribe()
  }
}
