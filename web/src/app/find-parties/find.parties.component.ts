import { Component, OnInit } from '@angular/core';
import { PartyDTO } from './../common/dto';
import { PartyService } from './../common/service/party.service';

@Component({
    selector: 'my-find-parties',
    templateUrl: './find.parties.component.html',
    styleUrls: ['./find.parties.component.scss'],
    providers: [PartyService]
})
export class FindPartiesComponent implements OnInit {
    parties: PartyDTO[];

    constructor(private partyService: PartyService) { }

    getParties(): void {
        this.partyService.getParties().then(parties => this.parties = parties);
    }
    ngOnInit(): void {
        this.getParties();
    }
}
