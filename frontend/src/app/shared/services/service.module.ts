import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ResponsavelService } from './responsavel.service';
import { TarefaService } from './tarefa.service';

@NgModule({
    declarations: [],
    imports: [
      CommonModule,
    ],
    providers: [
      ResponsavelService,
      TarefaService
    ]
  })
  export class ServiceModule { }
  