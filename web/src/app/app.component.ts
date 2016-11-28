import { Component } from '@angular/core';

import { ApiService } from './shared';

import '../style/app.scss';

@Component({
    selector: 'my-app', // <my-app></my-app>
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent {
    url = 'https://github.com/preboot/angular2-webpack';
    findParties: boolean;
    createParty: boolean;
    constructor(private api: ApiService) {
        // Do something with api
        this.onCreateParty();
    }

    onfindParties(): void {
        this.findParties = true;
        this.createParty = false;
    }

    onCreateParty(): void {
        this.findParties = false;
        this.createParty = true;
    }
}
