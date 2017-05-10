﻿export class PartyDTO {
    user: number;
    name: string;
    age: number;
    timeZone: number; // not done
    language: string;
    strongLanguage: boolean;
    groupServer: string;
    serverName: string;
    chatListening: boolean;
    chatSpeaking: boolean;
    chatType: string; // not done
    pvp: boolean;
    pve: boolean;
    slots: SlotDTO[];
    primeTimes: PrimeTimeDTO[];
    comment: string;
    createDate: Date;
    updateDate: Date;
    image: string;
}

export class PrimeTimeDTO {
    start: TimeOfDayDTO;
    end: TimeOfDayDTO;
    totalHours: number;
    day: string;
    constructor(day: string) {
        this.day = day;
        this.totalHours = 0;
        this.start = new TimeOfDayDTO();
        this.end = new TimeOfDayDTO();
    }
}

export class TimeOfDayDTO {
    public hour: number;
    public minute: number;

    clone(): TimeOfDayDTO {
        let result = new TimeOfDayDTO();
        result.hour = this.hour;
        result.minute = this.minute;
        return result;
    }

}

export class SlotDTO {
    isFree: Boolean;
    isPartyLeader: Boolean;
    character: CharacterDTO;
    constructor(public index: number, public role: string, public classType: string) {
        this.isFree = true;
        this.isPartyLeader = false;
    }
}

export class FilterDTO {
    constructor(public key: string, public value: Object) { }
}

export class CharacterDTO {
    id: number;
    name: string;
    classType: string;
    serverName: string;
    level: number;
    charClass: string;
    charRole: string;
    server: string;
    gearScore: number;
    image: string;
    primeTimes: PrimeTimeDTO[];
    constructor(public role: string) {
    }
}

export class UserDTO {
    constructor(public login: string, public password: string) { }
}
