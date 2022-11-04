import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { GetPlayComponent } from './get-play/get-play.component';
import { HomeComponent } from './home/home.component';
import { LoginGuard } from './login.guard';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PlaylistComponent } from './playlist/playlist.component';
import { RegisterComponent } from './register/register.component';
import { SpotyfyRegisterComponent } from './spotyfy-register/spotyfy-register.component';
import { UserDataComponent } from './user-data/user-data.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path:'navbar',
    component: NavbarComponent,
    pathMatch:'full'
  },
  {
    path:"home",
    component: HomeComponent
  },
  {
    path:"dashboard",
    component: DashboardComponent,
    pathMatch: 'full',
    canActivate: [LoginGuard]
  },
  {
    path:"register",
    component: RegisterComponent
  },
  {
    path:"login",
    component: LoginComponent
  },
  {
    path:"playlist",
    component:PlaylistComponent,
    pathMatch:"full"
  },
  {
    path:"userdata",
    component: UserDataComponent,
    pathMatch:"full"
  },
  {
    path:"spotyfyregistry",
    component: SpotyfyRegisterComponent,
    pathMatch:"full"
  },
  {
    path:"getplay",
    component: GetPlayComponent,
    pathMatch:"full"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
