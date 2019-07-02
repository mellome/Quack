import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { QuacksComponent } from './quacks/quacks.component';
import { ProfileViewComponent } from './profile-view/profile-view.component';
import { PostComponent } from './post/post.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component'
 
const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: QuacksComponent },
  { path: 'profile', component: ProfileViewComponent },
  { path: 'post', component: PostComponent},
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'admin-panel', component: AdminPanelComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
