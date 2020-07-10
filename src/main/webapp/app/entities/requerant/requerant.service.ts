import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRequerant } from 'app/shared/model/requerant.model';

type EntityResponseType = HttpResponse<IRequerant>;
type EntityArrayResponseType = HttpResponse<IRequerant[]>;

@Injectable({ providedIn: 'root' })
export class RequerantService {
  public resourceUrl = SERVER_API_URL + 'api/requerants';

  constructor(protected http: HttpClient) {}

  create(requerant: IRequerant): Observable<EntityResponseType> {
    return this.http.post<IRequerant>(this.resourceUrl, requerant, { observe: 'response' });
  }

  update(requerant: IRequerant): Observable<EntityResponseType> {
    return this.http.put<IRequerant>(this.resourceUrl, requerant, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRequerant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRequerant[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
