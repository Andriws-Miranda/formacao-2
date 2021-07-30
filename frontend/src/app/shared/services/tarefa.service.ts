import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';
import { Responsavel } from 'src/app/dominio/responsavel';
import { Tarefa } from 'src/app/dominio/tarefa';
import { environment } from 'src/environments/environment';
import { Page } from '../util/page';
import { RequestUtil } from '../util/request-util';

@Injectable()
export class TarefaService {
    
    path =  environment.apiUrl + '/tarefas/';
    constructor(private http: HttpClient) { }

    listarResponsaveis(query: string, tabela: Table): Observable<Page<Tarefa>> {
        return this.http.post<Page<Tarefa>>(`${this.path}search`, { 'query': query }, {
            params: RequestUtil.getRequestParamsTable(tabela)
        });
    }

}
