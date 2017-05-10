import { Directive, Input, EventEmitter, ElementRef, Renderer, Inject, OnInit } from '@angular/core';

@Directive({
    selector: '[myFocusEvent]'
})
export class FocusDirective implements OnInit {
    @Input() focusEvent: EventEmitter<boolean>;

    constructor( @Inject(ElementRef) private element: ElementRef, private renderer: Renderer) {
    }

    ngOnInit() {
        this.focusEvent.subscribe(() => {
            this.renderer.invokeElementMethod(this.element.nativeElement, 'focus', []);
        });
    }
}
