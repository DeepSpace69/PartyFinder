import { Component, OnInit } from '@angular/core';
import { Party } from './party';
import { PartyService } from './partyService';

@Component({
    selector: 'my-find-parties',
    templateUrl: './findParties.component.html',
    styleUrls: ['./findParties.component.scss'],
    providers: [PartyService]
})
export class FindPartiesComponent implements OnInit {
    parties: Party[];

    constructor(private partyService: PartyService) { }

    getParties(): void {
        this.partyService.getParties().then(parties => this.parties = parties);
    }
    ngOnInit(): void {
        this.getParties();
    }
}
