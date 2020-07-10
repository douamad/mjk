import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMaison } from 'app/shared/model/maison.model';

type EntityResponseType = HttpResponse<IMaison>;
type EntityArrayResponseType = HttpResponse<IMaison[]>;

@Injectable({ providedIn: 'root' })
export class MaisonService {
  public resourceUrl = SERVER_API_URL + 'api/maisons';

  constructor(protected http: HttpClient) {}

  create(maison: IMaison): Observable<EntityResponseType> {
    return this.http.post<IMaison>(this.resourceUrl, maison, { observe: 'response' });
  }

  update(maison: IMaison): Observable<EntityResponseType> {
    return this.http.put<IMaison>(this.resourceUrl, maison, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMaison>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMaison[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
