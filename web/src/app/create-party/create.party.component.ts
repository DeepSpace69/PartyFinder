import { Component, OnInit } from '@angular/core';
import { PartyService } from './../common/service/party.service';
import { PartyDTO, PrimeTimeDTO, SlotDTO } from './../common/dto';

const CLASSTYPES: string[] = ['Select class', 'Gunslinger', 'Blade Master', 'Spiritshaper', 'Vanguard', 'Swordmage', 'Occultist', 'Any'];
const ROLES: string[] = ['Select role', 'DD', 'Tank', 'Healer', 'Any'];
const PRIMETIMESWEEK: PrimeTimeDTO[] = [
    new PrimeTimeDTO('Monday'),
    new PrimeTimeDTO('Tuesday'),
    new PrimeTimeDTO('Wednesday'),
    new PrimeTimeDTO('Thursday'),
    new PrimeTimeDTO('Friday'),
    new PrimeTimeDTO('Saturday'),
    new PrimeTimeDTO('Sunday')];

@Component({
    selector: 'my-create-party',
    templateUrl: './create.party.component.html',
    styleUrls: ['./create.party.component.scss'],
    providers: [PartyService]
})
export class CreatePartyComponent implements OnInit {
    maxFileSize = 1000000;
    party: PartyDTO;
    roles: string[];
    classTypes: string[];
    detailedPrimeTimeSelected: Boolean;

    constructor(private partyService: PartyService) {
        this.party = new PartyDTO();
        this.party.image = null;
        this.party.user = 2;
        this.detailedPrimeTimeSelected = false;
        this.roles = ROLES;
        this.classTypes = CLASSTYPES;
        this.party.primeTimes = PRIMETIMESWEEK;

        this.party.primeTimes[0].start.hour = 1;
        this.party.primeTimes[0].start.minute = 30;

        this.party.primeTimes[0].end.hour = 2;
        this.party.primeTimes[0].end.minute = 30;

        this.party.slots = [];
        for (let i = 1; i < 6; i++) {
            let slot = new SlotDTO(i, this.roles[0], this.classTypes[0]);
            this.party.slots.push(slot);
        }
    }

    onPrimeTimeChanged(primeTime: PrimeTimeDTO) {
        if (primeTime.end && primeTime.start) {
            primeTime.totalHours = primeTime.end.hour - primeTime.start.hour;
            if (primeTime.totalHours < 0) {
                primeTime.totalHours = primeTime.totalHours + 24;
            }

            if (primeTime.day === 'Any') {
                primeTime.totalHours = primeTime.totalHours * 7;
            }

        }
    }

    ngOnInit(): void {
    }

    submit(): void {
        console.log(this.party);
        this.partyService.createParty(this.party);
    }

    selectPartyLeader(slotArg: SlotDTO): void {
        for (let slot of this.party.slots) {
            slot.isPartyLeader = false;
        }
        slotArg.isPartyLeader = true;
    }

    detailedPrimeTimeSelectedChanged(): void {
    }
}
