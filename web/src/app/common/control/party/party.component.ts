import { Component, OnInit, Input  } from '@angular/core';
import { PartyDTO } from './../../dto';

@Component({
    selector: 'my-party',
    templateUrl: './party.component.html',
    styleUrls: ['./party.component.scss']
})
export class PartyComponent implements OnInit {
    @Input() party: PartyDTO;
    constructor() {

    }

    ngOnInit(): void {
    }


}
