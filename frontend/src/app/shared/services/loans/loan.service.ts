import { Injectable } from '@angular/core';
import { BaseService } from '../_base.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { MaestroPersonasModel } from '../../models/loans/maestro-personas';
import { LoanRequest } from '../../models/loans/LoanRequest';
import { Observable } from 'rxjs';
import { LoanStats } from '../../models/loans/LoanStats';

@Injectable({
  providedIn: 'root',
})
export class LoansService extends BaseService<
  LoanRequest,
  MaestroPersonasModel
> {
  private apiurl: string;
  constructor(protected http: HttpClient) {
    super(http, environment.apiURL);
    this.apiurl = environment.apiURL;
  }
}
