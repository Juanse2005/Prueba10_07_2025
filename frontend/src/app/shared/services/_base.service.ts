import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { LoanStats } from '../models/loans/LoanStats';

export abstract class BaseService<TModel, TMasterModel> {
  public headers = new HttpHeaders();

  constructor(
    protected _httpClient: HttpClient,
    protected apiURL = environment.apiURL
  ) {}

  //Metodo GET para obtener datos
  getList(endpoint: string): Observable<TModel[]> {
    return this._httpClient.get<TModel[]>(`${this.apiURL}/${endpoint}`, {
      headers: this.headers,
    });
  }

  //Metodo GetbyId para obtener dato especifico
  getById(endpoint: string, id: string | number): Observable<TModel> {
    return this._httpClient.get<TModel>(`${this.apiURL}/${endpoint}/${id}`, {
      headers: this.headers,
    });
  }

  //Metodo POST para la creacion de datos
  post(endpoint: string, data: any): Observable<TModel> {
    return this._httpClient.post<TModel>(`${this.apiURL}/${endpoint}`, data, {
      headers: this.headers,
    });
  }
  
  //Metodo PUT para la actualizacion de datos
  put(endpoint: string, id: string | number, data: any): Observable<TModel> {
    return this._httpClient.put<TModel>(
      `${this.apiURL}/${endpoint}/${id}`,
      data,
      { headers: this.headers }
    );
  }

  //Metodo Delete para eliminar datos
  delete(endpoint: string, id: string | number): Observable<TModel> {
    return this._httpClient.delete<TModel>(`${this.apiURL}/${endpoint}/${id}`, {
      headers: this.headers,
    });
  }

  //Metodo markAsReturned para marcar un prestamo como devuelto
  markAsReturned(endpoint: string, id: number): Observable<void> {
    return this._httpClient.put<void>(
      `${this.apiURL}/${endpoint}/return/${id}`,
      {}
    );
  }

  //Metodo getStat para obtener estadisticas
  getStat<T>(endpoint: string): Observable<T> {
    return this._httpClient.get<T>(`${this.apiURL}/${endpoint}`, {
      headers: this.headers,
    });
  }
}
