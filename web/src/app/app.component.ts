import { Component } from '@angular/core';
import '../style/app.scss';
import { AuthService } from './common/service/auth.service';
import { PartyService } from './common/service/party.service';
import { CharacterService } from './common/service/character.service';

@Component({
    selector: 'my-app',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
    providers: [AuthService, PartyService, CharacterService]
})
export class AppComponent {
    public isAuthorised: boolean;
    constructor(private authService: AuthService) {
        this.authService.authAnnounced$.subscribe(p => this.onAuthorised(p));
        this.isAuthorised = false;
    }

    private onAuthorised(isAuthorised: boolean): void {
        this.isAuthorised = isAuthorised;
    }
}
