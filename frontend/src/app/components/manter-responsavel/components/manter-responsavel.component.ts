import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng';
import { Responsavel } from 'src/app/dominio/responsavel';
import { ResponsavelService } from 'src/app/shared/services/responsavel.service';
import { Page } from 'src/app/shared/util/page';

@Component({
  selector: 'app-manter-responsavel',
  templateUrl: './manter-responsavel.component.html',
  styleUrls: ['./manter-responsavel.component.css']
})
export class ManterResponsavelComponent implements OnInit {

  responsaveis: Page<Responsavel> = new Page();
  @ViewChild('tabela') tabela: Table;
  query = '';

  constructor(private service: ResponsavelService) { }

  ngOnInit(): void {
    this.buscarResponsaveis(this.query);
  }

  buscarResponsaveis(query: string){
    this.service.listarResponsaveis(query, this.tabela).subscribe(res => {
      this.responsaveis = res;
    });   
    
  }
}
