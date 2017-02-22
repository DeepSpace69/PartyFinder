import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { FindPartiesComponent } from './find-parties/find.parties.component';
import { CreatePartyComponent } from './create-party/create.party.component';
import { CreateCharacterComponent } from './create-character/create.character.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    { path: 'findParty', component: FindPartiesComponent },
    { path: 'createParty', component: CreatePartyComponent },
    { path: 'createCharacter', component: CreateCharacterComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' }
];

export const routing = RouterModule.forRoot(routes);
