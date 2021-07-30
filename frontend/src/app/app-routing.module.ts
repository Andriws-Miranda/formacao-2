import { ReponsaveisModule } from './components/manter-responsavel/reponsaveis.module';
import { TarefaModule } from './components/manter-tarefa/tarefa.module';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';

const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'tarefas', loadChildren: () => TarefaModule },
    { path: 'responsaveis', loadChildren: () => ReponsaveisModule}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
