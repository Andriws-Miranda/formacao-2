import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng';
import { Tarefa } from 'src/app/dominio/tarefa';
import { TarefaService } from 'src/app/shared/services/tarefa.service';
import { Page } from 'src/app/shared/util/page';

@Component({
  selector: 'app-manter-tarefa',
  templateUrl: './manter-tarefa.component.html',
  styleUrls: ['./manter-tarefa.component.css']
})
export class ManterTarefaComponent implements OnInit {

  tarefas: Page<Tarefa> = new Page();
  @ViewChild('tabela') tabela: Table;
  query: string = '';

  constructor(private service: TarefaService) { }

  ngOnInit(): void {
    this.buscarTarefas(this.query);
  }

  buscarTarefas(query: string){
    this.service.listarResponsaveis(query, this.tabela).subscribe(res => {
      this.tarefas = res;
    });    
  }
}
