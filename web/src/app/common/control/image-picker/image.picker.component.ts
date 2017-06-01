import { Component, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';


@Component({
    selector: 'my-image-picker',
    templateUrl: './image.picker.component.html',
    styleUrls: ['./image.picker.component.scss'],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => ImagePickerComponent),
            multi: true
        }
    ]
})
export class ImagePickerComponent implements ControlValueAccessor {
    emptyImage = true;
    img: string;
    propagateChange = (_: any) => { };

    constructor() {
    }

    writeValue(obj: any): void {
        this.img = obj;
    }

    registerOnChange(fn: any): void {
        this.propagateChange = fn;
    }

    registerOnTouched(): void { }

    ondragover(e): void {
        e.preventDefault();
        e.stopPropagation();
        e.target.classList.add('hover');
    }

    ondragleave(e): void {
        e.stopPropagation();
        e.target.classList.remove('hover');
    }

    ondrop(e): void {
        e.preventDefault();
        e.target.classList.remove('hover');
        e.target.classList.add('drop');

        let file = e.dataTransfer.files[0];

        let reader = new FileReader();
        reader.onload = (event: any) => {
            this.emptyImage = false;
            this.img = event.target.result;
            this.propagateChange(this.img);
        }

        reader.readAsDataURL(file);
    }
}
