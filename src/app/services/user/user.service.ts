import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../core/auth.service';

@Injectable()
export class UserService {

  constructor(public auth: AuthService,
              private router: Router) {
  }

  /// Social Login

  signInWithGithub(): void {
    this.auth.githubLogin()
      .then(() => this.afterSignIn());
  }

  signInWithGoogle(): void {
    this.auth.googleLogin()
      .then(() => this.afterSignIn());
  }

  signInWithFacebook(): void {
    this.auth.facebookLogin()
      .then(() => this.afterSignIn());
  }

  signInWithTwitter(): void {
    this.auth.twitterLogin()
      .then(() => this.afterSignIn());
  }

  /// Anonymous Sign In

  signInAnonymously() {
    this.auth.anonymousLogin()
      .then(() => this.afterSignIn());
  }


  /// Shared

  private afterSignIn(): void {
    // Do after login stuff here, such router redirects, toast messages, etc.
    this.router.navigate(['/']);
  }

  /// Signout
  private signOut(): void {
    this.auth.signOut();
  }
}
