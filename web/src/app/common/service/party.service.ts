import { Injectable } from '@angular/core';
import { Party } from './../party';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class PartyService {
    private findPartyUrl = 'http://groupfinder.herokuapp.com/findParties';  // URL to web api

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getParties(): Promise<Party[]> {
        console.log('Hello');
        return this.http.get(this.findPartyUrl)
            .toPromise()
            .then(response => response.json() as Party[])
            .catch(this.handleError);
    }
}
