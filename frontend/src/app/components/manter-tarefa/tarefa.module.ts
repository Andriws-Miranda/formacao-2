import { SharedModule } from './../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TarefaRoutingModule } from './tarefa-routing.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    TarefaRoutingModule,
    SharedModule
  ]
})
export class TarefaModule { }
