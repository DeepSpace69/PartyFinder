import { Injectable } from '@angular/core';
import { PartyDTO } from './../dto';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class PartyService {
    private findPartyUrl = 'http://groupfinder.herokuapp.com/findParties';  // URL to web api
    private createPartyUrl = 'http://groupfinder.herokuapp.com/createParty';  // URL to web api

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getParties(): Promise<PartyDTO[]> {

        return this.http.get(this.findPartyUrl)
            .toPromise()
            .then(response => response.json() as PartyDTO[])
            .catch(this.handleError);
    }

    createParty(party: PartyDTO): void {
        console.log('Hello');
        this.http.post(this.createPartyUrl, JSON.stringify(party))
            .toPromise()
            .catch(this.handleError);
    }
}
