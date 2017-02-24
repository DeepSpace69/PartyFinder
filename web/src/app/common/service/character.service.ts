﻿import { Injectable } from '@angular/core';
import { CharacterDTO } from './../dto';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class CharacterService {
    private createCharacterUrl = '123';

    constructor(private http: Http) { }

    createCharacter(character: CharacterDTO): void {
        this.http.post(this.createCharacterUrl, JSON.stringify(character))
            .toPromise()
            .catch(this.handleError);
    }

    handleError(): void {
    }

    public getMyCharacters(): Promise<CharacterDTO[]> {
        return new Promise<CharacterDTO[]>((resolve) => {
            let characterList = [];
            for (let i = 0; i < 4; i++) {
                let c1 = new CharacterDTO('priest');
                c1.level = 11;
                c1.name = 'Ayanami Rei';
                c1.charClass = 'Priest';
                c1.image = 'data:image/jpg;base64,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';

                let c2 = new CharacterDTO('priest');
                c2.level = 128;
                c2.name = 'Motoko';
                c2.charClass = 'Tracer';
                c2.image = 'data:image/jpg;base64,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';

                characterList.push(c1);
                characterList.push(c2);
            }
            resolve(characterList);
        });
    }
}
