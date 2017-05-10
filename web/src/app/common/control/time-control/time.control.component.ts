import { Component, forwardRef, EventEmitter } from '@angular/core';
import { TimeOfDayDTO } from './../../dto';
import { ControlValueAccessor, NG_VALUE_ACCESSOR, NG_VALIDATORS, Validator, ValidationErrors } from '@angular/forms';

@Component({
    selector: 'my-time-control',
    templateUrl: './time.control.component.html',
    styleUrls: ['./time.control.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            // tslint:disable-next-line
            useExisting: forwardRef(() => TimeControlComponent),
            multi: true,
        },
        {
            provide: NG_VALIDATORS,
            // tslint:disable-next-line
            useExisting: forwardRef(() => TimeControlComponent),
            multi: true,
        }]
})
export class TimeControlComponent implements ControlValueAccessor, Validator {
    public myFocusTriggeringEventEmitter = new EventEmitter<boolean>();

    private time: TimeOfDayDTO;
    private propagateChange = (_: any) => { };

    constructor() {
    }

    onHoursChange(e) {
        if (this.time) {
            this.time.hour = e;
            this.propagateChange(this.time);
        }
    }

    onMinutesChange(e) {
        if (this.time) {
            this.time.minute = e;
            this.propagateChange(this.time);
        }
    }

    writeValue(obj: any): void {
        this.time = obj;
    }

    registerOnChange(fn: any): void {
        this.propagateChange = fn;
    }

    registerOnTouched(): void { }

    focusOutFunction(e): void {
        if (Number(e.target.value) < 10 && e.target.value.length === 1) {
            e.target.value = '0' + e.target.value;
        }
    }
    onKeyDownHour(e): void {
        if (e.target.selectionStart === 2) {
            this.myFocusTriggeringEventEmitter.emit(true);
        }
    }

    public validate(): ValidationErrors {
        let result = new Object();

        if (!this.time) {
            return null;
        }

        if (!(Number(this.time.hour) >= 0 && Number(this.time.hour) < 24)) {
            result['timeError'] = 'Incorrect hours';
        }

        if (!(Number(this.time.minute) >= 0 && Number(this.time.minute) < 60)) {
            result['timeError'] = 'Incorrect hours';
        }

        return result;
    }
}
