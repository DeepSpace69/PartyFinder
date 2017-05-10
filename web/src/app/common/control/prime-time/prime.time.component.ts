import { Component, OnInit, Input } from '@angular/core';
import { PrimeTimeDTO } from './../../dto';
import * as _ from 'lodash';
const WEEKDAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'];
const WEEKENDS = ['Saturday', 'Sunday'];

@Component({
    selector: 'my-prime-time',
    templateUrl: './prime.time.component.html',
    styleUrls: ['./prime.time.component.scss']
})
export class PrimeTimeComponent implements OnInit {
    @Input() primeTime: PrimeTimeDTO[];
    private anyDayIsChanging: boolean;
    private weekDayIsChanging: boolean;
    private weekendDayIsChanging: boolean;

    expandDays: boolean;
    expandWeekday: boolean;
    expandWeekend: boolean;

    weekdays = WEEKDAYS;
    weekends = WEEKENDS;

    anyDay: PrimeTimeDTO;
    weekDay: PrimeTimeDTO;
    weekendDay: PrimeTimeDTO;

    constructor() {
        this.anyDay = new PrimeTimeDTO('Anyday');
        this.weekDay = new PrimeTimeDTO('Weekday');
        this.weekendDay = new PrimeTimeDTO('Weekend');
    }

    anyDayChanged(): void {
        if (this.weekDayIsChanging || this.weekendDayIsChanging) {
            return;
        }

        this.anyDayIsChanging = true;
        this.weekDay.start = this.anyDay.start.clone();
        this.weekDay.end = this.anyDay.end.clone();
        this.weekendDay.start = this.anyDay.start.clone();
        this.weekendDay.end = this.anyDay.end.clone();
        this.weekDayChanged();
        this.weekendChanged();
        this.anyDayIsChanging = false;
    }

    calcTotalHours(primeTime: PrimeTimeDTO): number {

        let result = ((Number(primeTime.end.hour) * 60 +
                Number(primeTime.end.minute)) - (Number(primeTime.start.hour) * 60 +
                Number(primeTime.start.minute))) / 60;

        if (result < 0) {
            result = 24 + result;
        }
        return result;
    }

    weekDayChanged(): void {
        this.weekDayIsChanging = true;

        if (!this.anyDayIsChanging) {
            this.anyDay = new PrimeTimeDTO('Anyday');
        }


        _.forEach(this.weekdays, (p: string) => {
            let primeTime = this.getPrime(p);
            primeTime.start = this.weekDay.start.clone();
            primeTime.end = this.weekDay.end.clone();
        });

        this.weekDay.totalHours = this.calcTotalHours(this.weekDay) * 5;
        this.anyDay.totalHours = this.weekDay.totalHours + this.weekendDay.totalHours;

        this.weekDayIsChanging = false;
    }

    weekendChanged(): void {
        this.weekendDayIsChanging = true;

        if (!this.anyDayIsChanging) {
            this.anyDay = new PrimeTimeDTO('Anyday');
        }

        _.forEach(this.weekends, (p: string) => {
            let primeTime = this.getPrime(p);
            primeTime.start = this.weekendDay.start.clone();
            primeTime.end = this.weekendDay.end.clone();
        });

        this.weekendDay.totalHours = this.calcTotalHours(this.weekendDay) * 2;
        this.anyDay.totalHours = this.weekDay.totalHours + this.weekendDay.totalHours;

        this.weekendDayIsChanging = false;
    }

    someWeekendChanged(): void {
        if (!this.weekendDayIsChanging) {
            this.weekendDay = new PrimeTimeDTO('WeekendDay');
        }

        let hours = 0;
        _.forEach(this.weekends, (p: string) => {
            let primeTime = this.getPrime(p);

            let x = this.calcTotalHours(primeTime);
            if (x) {
                hours = hours + x;
            }

        });

        this.weekendDay.totalHours = hours;
        this.anyDay.totalHours = this.weekDay.totalHours + this.weekendDay.totalHours;


    }

    someWeekDayChanged(): void {
        if (!this.weekDayIsChanging) {
            this.weekDay = new PrimeTimeDTO('WeekDay');
        }
        let hours = 0;
        _.forEach(this.weekdays, (p: string) => {
            let primeTime = this.getPrime(p);

            let x = this.calcTotalHours(primeTime);
            if (x) {
                hours = hours + x;
            }

        });

        this.weekDay.totalHours = hours;
        this.anyDay.totalHours = this.weekDay.totalHours + this.weekendDay.totalHours;

    }


    ngOnInit(): void {
        if (!this.primeTime) {
            this.primeTime = [];
        }
    }

    collapseDays(e): void {
        if (!e) {
            this.expandWeekday = false;
            this.expandWeekend = false;
        }
    }

    getPrime(day: string): PrimeTimeDTO {
        let primeDay: PrimeTimeDTO = _.find(this.primeTime, function (p) { return p.day === day; });

        if (!primeDay) {
            primeDay = new PrimeTimeDTO(day);
            this.primeTime.push(primeDay);
        }

        return primeDay;
    }
}
