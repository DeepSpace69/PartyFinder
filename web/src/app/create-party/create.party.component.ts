import { Component, OnInit } from '@angular/core';
import { PartyService } from './../common/service/party.service';

@Component({
    selector: 'my-create-party',
    templateUrl: './create.party.component.html',
    styleUrls: ['./create.party.component.scss'],
    providers: [PartyService]
})
export class CreatePartyComponent implements OnInit {
    slots: Object[];
    constructor(private partyService: PartyService) {
        this.slots = [1,2,3,4,5];
    }

    ngOnInit(): void {
    }
}
