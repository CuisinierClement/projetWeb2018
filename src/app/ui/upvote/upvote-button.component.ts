import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { UpvoteService } from '../../services/upvote/upvote.service';
import { sum, values } from 'lodash';
@Component({
  selector: 'upvote-button',
  templateUrl: './upvote-button.component.html',
  styleUrls: ['./upvote-button.component.scss']
})
export class UpvoteButtonComponent implements OnInit, OnDestroy {
  @Input() userId;
  @Input() itemId;
  voteCount = 0;
  userVote = 0;
  subscription;
  constructor(private upvoteService: UpvoteService) { }
  ngOnInit() {
    this.subscription = this.upvoteService.getItemVotes(this.itemId)
      .subscribe(upvotes => {
        if (this.userId) {
          this.userVote = upvotes[this.userId]
          this.voteCount = sum(values(upvotes))
        }
      })
  }
  upvote() {
    const vote = this.userVote === 1 ? 0 : 1
    this.upvoteService.updateUserVote(this.itemId, this.userId, vote)
  }
  downvote() {
    const vote = this.userVote === -1 ? 0 : -1
    this.upvoteService.updateUserVote(this.itemId, this.userId, vote)
  }
  ngOnDestroy() {
    this.subscription.unsubscribe()
  }
}
