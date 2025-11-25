import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { TermsComponent } from './components/terms/terms.component';
import { PrivacyComponent } from './components/privacy/privacy.component';
import { AboutComponent } from './components/about/about.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
    { path: 'terms', component: TermsComponent },
    { path: 'privacy', component: PrivacyComponent },
    { path: 'about', component: AboutComponent },
    { path: '**', redirectTo: '' }
];
