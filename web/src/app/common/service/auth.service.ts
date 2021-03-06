﻿import { Injectable } from '@angular/core';
import { UserDTO } from './../dto';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

const authUrl: string = process.env.API_URL + 'auth';

@Injectable()
export class AuthService {

    private _user: UserDTO;
    get user(): UserDTO {
        return this._user;
    }

    private _isAuthorised: boolean;
    get isAuthorised(): boolean {
        return this._isAuthorised;
    }

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    public signIn(login: string, password: string): Promise<UserDTO> {

        let body = JSON.stringify(new UserDTO(login, password));
        return this.http.post(authUrl, body)
            .toPromise()
            .then(response => {
                this._user = response.json() as UserDTO;
                return this._user;
            })
            .catch(this.handleError);
    }
}
