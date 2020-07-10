import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IObjetSaisine } from 'app/shared/model/objet-saisine.model';

type EntityResponseType = HttpResponse<IObjetSaisine>;
type EntityArrayResponseType = HttpResponse<IObjetSaisine[]>;

@Injectable({ providedIn: 'root' })
export class ObjetSaisineService {
  public resourceUrl = SERVER_API_URL + 'api/objet-saisines';

  constructor(protected http: HttpClient) {}

  create(objetSaisine: IObjetSaisine): Observable<EntityResponseType> {
    return this.http.post<IObjetSaisine>(this.resourceUrl, objetSaisine, { observe: 'response' });
  }

  update(objetSaisine: IObjetSaisine): Observable<EntityResponseType> {
    return this.http.put<IObjetSaisine>(this.resourceUrl, objetSaisine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IObjetSaisine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IObjetSaisine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
