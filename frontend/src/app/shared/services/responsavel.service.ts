import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';
import { FiltroBase } from 'src/app/dominio/filters/filter-base';
import { ListagemBase } from 'src/app/dominio/listagem/listagem-base';
import { ResponsavelListagem } from 'src/app/dominio/listagem/responsavel-listagem';
import { Responsavel } from 'src/app/dominio/responsavel';
import { environment } from 'src/environments/environment';
import { Page } from '../util/page';
import { RequestUtil } from '../util/request-util';

@Injectable()
export class ResponsavelService {
    
    path =  environment.apiUrl + '/responsaveis/';
    constructor(private http: HttpClient) { }

    listarResponsaveis(query: string, tabela: Table): Observable<Page<Responsavel>> {
        return this.http.post<Page<Responsavel>>(`${this.path}search`, {'query': query}, {
            params: RequestUtil.getRequestParamsTable(tabela)
        });
    }

}
