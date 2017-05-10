import { Component, OnInit, Input } from '@angular/core';
import { SlotDTO } from './../../dto';

@Component({
    selector: 'my-slot',
    templateUrl: './slot.component.html',
    styleUrls: ['./slot.component.scss']
})
export class SlotComponent implements OnInit {
    @Input() slot: SlotDTO;
    constructor() {
    }

    ngOnInit(): void {
    }
}
