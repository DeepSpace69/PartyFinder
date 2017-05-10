import { Component, OnInit, Input  } from '@angular/core';
import { CharacterDTO } from './../../dto';

@Component({
    selector: 'my-character',
    templateUrl: './character.component.html',
    styleUrls: ['./character.component.scss']
})
export class CharacterComponent implements OnInit {
    @Input() character: CharacterDTO;
    constructor() {
    }

    ngOnInit(): void {
    }
}
