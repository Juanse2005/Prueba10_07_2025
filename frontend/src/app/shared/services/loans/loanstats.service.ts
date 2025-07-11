import { Injectable } from '@angular/core';
import { BaseService } from '../_base.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { MaestroPersonasModel } from '../../models/loans/maestro-personas';
import { LoanRequest } from '../../models/loans/LoanRequest';
import { LoanStats } from '../../models/loans/LoanStats';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoanStatsService extends BaseService<
  LoanStats,
  MaestroPersonasModel
> {
  private apiurl: string;
  constructor(protected http: HttpClient) {
    super(http, environment.apiURL);
    this.apiurl = environment.apiURL;
  }

  getUsersWithLoans(): Observable<LoanStats> {
    return this.getStat<LoanStats>('loans/stats/users-with-loans');
  }
}
