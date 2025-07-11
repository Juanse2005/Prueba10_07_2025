import { Injectable } from '@angular/core';
import { BaseService } from '../_base.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { MaestroPersonasModel } from '../../models/loans/maestro-personas';
import { UsersComponent } from '../../../views/users/users';
import { User } from '../../models/users/user';

@Injectable({
  providedIn: 'root',
})
export class UserService extends BaseService<User, MaestroPersonasModel> {
  private apiurl: string;
  constructor(protected http: HttpClient) {
    super(http, environment.apiURL);
    this.apiurl = environment.apiURL;
  }
}
