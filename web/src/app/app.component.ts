import { Component } from '@angular/core';
import '../style/app.scss';
import { AuthService } from './common/service/auth.service';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
    providers: [AuthService]
})
export class AppComponent {
    public isAuthorised: boolean;
    constructor(private authService: AuthService) {
        authService.authAnnounced$.subscribe(p => this.onAuthorised(p));
        this.isAuthorised = false;
    }

    private onAuthorised(isAuthorised: boolean): void {
        this.isAuthorised = isAuthorised;
    }
}
