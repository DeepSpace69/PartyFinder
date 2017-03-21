import { Injectable } from '@angular/core';
import { PartyDTO, FilterDTO } from './../dto';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class PartyService {
    private findPartyUrl = process.env.API_URL + 'findParties';  // URL to web api
    private createPartyUrl = process.env.API_URL + 'createParty';  // URL to web api

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getParties(filters: FilterDTO[]): Promise<PartyDTO[]> {

        let body = JSON.stringify(filters);
        return this.http.post(this.findPartyUrl, body)
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
