import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IObjetAssistance } from 'app/shared/model/objet-assistance.model';

type EntityResponseType = HttpResponse<IObjetAssistance>;
type EntityArrayResponseType = HttpResponse<IObjetAssistance[]>;

@Injectable({ providedIn: 'root' })
export class ObjetAssistanceService {
  public resourceUrl = SERVER_API_URL + 'api/objet-assistances';

  constructor(protected http: HttpClient) {}

  create(objetAssistance: IObjetAssistance): Observable<EntityResponseType> {
    return this.http.post<IObjetAssistance>(this.resourceUrl, objetAssistance, { observe: 'response' });
  }

  update(objetAssistance: IObjetAssistance): Observable<EntityResponseType> {
    return this.http.put<IObjetAssistance>(this.resourceUrl, objetAssistance, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IObjetAssistance>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IObjetAssistance[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
