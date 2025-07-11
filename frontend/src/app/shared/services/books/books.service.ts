import { Injectable } from '@angular/core';
import { BaseService } from '../_base.service';
import { MaestroPersonasModel } from '../../models/loans/maestro-personas';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { BooksComponent } from '../../../views/books/books';
import { Book } from '../../models/books/book';

@Injectable({
  providedIn: 'root',
})
export class BooksService extends BaseService<Book, MaestroPersonasModel> {
  private apiurl: string;
  constructor(protected http: HttpClient) {
    super(http, environment.apiURL);
    this.apiurl = environment.apiURL;
  }
}
