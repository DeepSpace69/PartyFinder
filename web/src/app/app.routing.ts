import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { FindPartiesComponent } from './findParties/findParties.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent},
  { path: 'findParties', component: FindPartiesComponent}
];

export const routing = RouterModule.forRoot(routes);
