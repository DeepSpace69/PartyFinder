import { Component, OnInit } from '@angular/core';
import { CharacterService } from './../common/service/character.service';
import { CharacterDTO, PrimeTimeDTO} from './../common/dto';

const CLASSTYPES: string[] = ['Gunslinger', 'Blade Master', 'Spiritshaper', 'Vanguard', 'Swordmage', 'Occultist', 'Any'];
const ROLES: string[] = ['DD', 'Tank', 'Healer', 'Any'];

@Component({
    selector: 'my-create-character',
    templateUrl: './create.character.component.html',
   // styleUrls: ['./create.character.component.scss'],
    providers: [CharacterService]
})
export class CreateCharacterComponent implements OnInit {

    newCharacter: CharacterDTO;
    roles: string[];
    classTypes: string[];
    characterService: CharacterService;

    constructor(private characterServiceArg: CharacterService) {
        this.newCharacter = new CharacterDTO();
        this.roles = ROLES;
        this.classTypes = CLASSTYPES;
        //this.characterService = characterServiceArg;
//this.newCharacter.charRole = this.roles[1];
    }

    ngOnInit(): void {
    }

    submit2(): void {
        console.log(this.newCharacter.name);
        console.log(this.newCharacter.level);
        console.log(this.newCharacter.charClass);
        console.log(this.newCharacter.gearScore);
        console.log(this.newCharacter.server);
        console.log(this.newCharacter.charRole);

        this.characterService.createCharacter(this.newCharacter);
    }

    submit(): void {
        console.log(this.newCharacter.charRole);
        //this.characterService.createCharacter(this.newCharacter);
    }
}
