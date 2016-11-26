import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'my-about',
  templateUrl: './findParties.component.html',
  styleUrls: ['./findParties.component.scss']
})
export class FindPartiesComponent implements OnInit {
  parties = PARTIES;
  constructor() {
    // Do stuff
  }

  ngOnInit() {
    console.log('Hello About');
  }

}

export class Party {
  id: number;
  name: string;
}

const PARTIES: Party[] = [
  { id: 11, name: 'Mr. Nice' },
  { id: 12, name: 'Narco' },
  { id: 13, name: 'Bombasto' },
  { id: 14, name: 'Celeritas' },
  { id: 15, name: 'Magneta' },
  { id: 16, name: 'RubberMan' },
  { id: 17, name: 'Dynama' },
  { id: 18, name: 'Dr IQ' },
  { id: 19, name: 'Magma' },
  { id: 20, name: 'Tornado' }
];