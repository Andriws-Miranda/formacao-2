import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManterResponsavelComponent } from './components/manter-responsavel.component';


const routes: Routes = [
  { path: '', component: ManterResponsavelComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReponsaveisRoutingModule { }
