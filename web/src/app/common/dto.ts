export class PartyDTO {
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
}

export class PrimeTimeDTO {
    start: any;
    end: any;
    totalHours: number;
    day: string;
    constructor(day: string) {
        this.day = day;
        this.totalHours = 0;
    }
}

export class SlotDTO {
    isFree: Boolean;
    isPartyLeader: Boolean;
    index: number;
    role: string;
    classType: string;
    constructor(private indexArg: number, private roleArg: string, private classTypeArg: string) {
        this.isFree = true;
        this.isPartyLeader = false;
        this.index = indexArg;
        this.role = roleArg;
        this.classType = classTypeArg;
    }
}

export class FilterDTO {
    constructor(public key: string, public value: Object) { }
}

export class CharacterDTO {
    id: number;
    name: string;
    level: number;
    classType: string;
    serverName: string;
    gearScore: number;
    primeTimes: PrimeTimeDTO[];
constructor(public role: string)
{
}
}
