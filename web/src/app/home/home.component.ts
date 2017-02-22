import { Component, OnInit } from '@angular/core';
import { CharacterDTO } from './../common/dto';

@Component({
    selector: 'my-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    public characterList: CharacterDTO[];
    constructor() {
        this.characterList = [
            new CharacterDTO('priest'),
            new CharacterDTO('priest'),
            new CharacterDTO('priest'),
            new CharacterDTO('priest'),
            new CharacterDTO('priest')
        ];
    }

    ngOnInit(): void {
    }

    onClick(): void {
        console.log('click');
    }

}
