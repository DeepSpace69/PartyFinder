import { NgModule, ApplicationRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import { FindPartiesComponent } from './find-parties/find.parties.component';
import { CreatePartyComponent } from './create-party/create.party.component';
import { CreateCharacterComponent } from './create-character/create.character.component';
import { ApiService } from './shared';
import { routing } from './app.routing';
import { MaterialModule } from '@angular/material';

import { removeNgStyles, createNewHosts } from '@angularclass/hmr';

import {HAMMER_GESTURE_CONFIG} from '@angular/platform-browser';
import {MdGestureConfig} from '@angular/material/core';
import '@angular/material/core/theming/prebuilt/deeppurple-amber';

@NgModule({
    imports: [
        MaterialModule.forRoot(),
        NgbModule.forRoot(),
        BrowserModule,
        HttpModule,
        FormsModule,
        routing
    ],
    declarations: [
        AppComponent,
        FindPartiesComponent,
        CreatePartyComponent,
        CreateCharacterComponent
    ],
    providers: [
        ApiService,
        { provide: HAMMER_GESTURE_CONFIG, useClass: MdGestureConfig }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
    constructor(public appRef: ApplicationRef) { }
    hmrOnInit(store: any) {
        console.log('HMR store', store);
    }
    hmrOnDestroy(store: any) {
        let cmpLocation = this.appRef.components.map(cmp => cmp.location.nativeElement);
        // recreate elements
        store.disposeOldHosts = createNewHosts(cmpLocation);
        // remove styles
        removeNgStyles();
    }
    hmrAfterDestroy(store: any) {
        // display new elements
        store.disposeOldHosts();
        delete store.disposeOldHosts;
    }
}
