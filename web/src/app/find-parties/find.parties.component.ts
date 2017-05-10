import { Component, OnInit } from '@angular/core';
import { PartyDTO, FilterDTO } from './../common/dto';
import { PartyService } from './../common/service/party.service';
import * as _ from 'lodash';

@Component({
    selector: 'my-find-parties',
    templateUrl: './find.parties.component.html',
    styleUrls: ['./find.parties.component.scss'],
    providers: [PartyService]
})
export class FindPartiesComponent implements OnInit {
    allParties: PartyDTO[];
    filters: FilterDTO[];
    currentPage: number;
    partyName: string;
    slider: number;

    constructor(private partyService: PartyService) {
        this.filters = [];
        this.allParties = [];
    }

    getParties(): void {
        this.partyService.getParties(this.filters)
            .then(p => {
                this.allParties = p;
            });
    }

    ngOnInit(): void {
        this.getParties();
    }

    setFilter(keyArg: string, valueArg: Object): void {
        console.log(this.filters);
        let filter = new FilterDTO(keyArg, valueArg);
        if (this.getFilter(keyArg, valueArg) == null) {
            this.filters.push(filter);
        } else {
            _.remove(this.filters, filter);
        }

        this.getParties();
    }

    getFilter(keyArg: string, valueArg: Object): FilterDTO {
        return _.find(this.filters, new FilterDTO(keyArg, valueArg));
    }

    onGoButtonClick(): void {
        let nameFilter = _.find(this.filters, ['key', 'name']);
        if (!nameFilter) {
            nameFilter = new FilterDTO('name', this.partyName);
            this.filters.push(nameFilter);
        } else {
            nameFilter.value = this.partyName;
        }
    }

}

