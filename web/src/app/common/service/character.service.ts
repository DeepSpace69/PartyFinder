import { Injectable } from '@angular/core';
import { CharacterDTO } from './../dto';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class CharacterService {
    // private createCharacterUrl = '123';

    constructor(private http: Http) { }

    createCharacter(character: CharacterDTO): void {
        // this.http.post(this.createCharacterUrl, JSON.stringify(character))
        //    .toPromise()
        //    .catch(this.handleError);
    }

    handleError(): void {
    }
}
