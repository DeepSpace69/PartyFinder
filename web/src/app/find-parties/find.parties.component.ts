import { Component, OnInit } from '@angular/core';
import { Party } from './../common/party';
import { PartyService } from './../common/service/party.service';

@Component({
    selector: 'my-find-parties',
    templateUrl: './find.parties.component.html',
    styleUrls: ['./find.parties.component.scss'],
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
