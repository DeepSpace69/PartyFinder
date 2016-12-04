import { Component, OnInit } from '@angular/core';
import { PartyService } from './../common/service/party.service';
import { PartyDTO, PrimeTimeDTO, SlotDTO} from './../common/dto';

const CLASSTYPES: string[] = ['Select class', 'Gunslinger', 'Blade Master', 'Spiritshaper', 'Vanguard', 'Swordmage', 'Occultist', 'Any'];
const ROLES: string[] = ['Select role', 'DD', 'Tank', 'Healer', 'Any'];
const PRIMETIMESANYDAY: PrimeTimeDTO[] = [new PrimeTimeDTO('Any day')];
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
    party: PartyDTO;
    roles: string[];
    classTypes: string[];
    detailedPrimeTimeSelected: Boolean;
    partyService: PartyService;

    constructor(private partyServiceArg: PartyService) {
        this.partyService = partyServiceArg;
        this.party = new PartyDTO();
        this.detailedPrimeTimeSelected = false;
        this.roles = ROLES;
        this.classTypes = CLASSTYPES;
        this.party.primeTimes = PRIMETIMESANYDAY;

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

            if (primeTime.day === 'Any day') {
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
        this.detailedPrimeTimeSelected = !this.detailedPrimeTimeSelected;
        if (this.detailedPrimeTimeSelected) {
            this.party.primeTimes = PRIMETIMESWEEK;
        } else {
            this.party.primeTimes = PRIMETIMESANYDAY;
        }
    }
}
