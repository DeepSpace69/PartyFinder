import { Component, OnInit } from '@angular/core';
import { CharacterDTO, PartyDTO } from './../common/dto';
import { PartyService } from './../common/service/party.service';
import { CharacterService } from './../common/service/character.service';

@Component({
    selector: 'my-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    public characterList: CharacterDTO[];
    public partyList: PartyDTO[];
    constructor(private partyService: PartyService, private characterService: CharacterService) {
    }

    ngOnInit(): void {
        this.characterService.getMyCharacters().then(p => this.characterList = p);
        this.partyService.getMyParties().then(p => this.partyList = p);
    }
}
