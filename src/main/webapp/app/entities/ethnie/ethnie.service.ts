import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEthnie } from 'app/shared/model/ethnie.model';

type EntityResponseType = HttpResponse<IEthnie>;
type EntityArrayResponseType = HttpResponse<IEthnie[]>;

@Injectable({ providedIn: 'root' })
export class EthnieService {
  public resourceUrl = SERVER_API_URL + 'api/ethnies';

  constructor(protected http: HttpClient) {}

  create(ethnie: IEthnie): Observable<EntityResponseType> {
    return this.http.post<IEthnie>(this.resourceUrl, ethnie, { observe: 'response' });
  }

  update(ethnie: IEthnie): Observable<EntityResponseType> {
    return this.http.put<IEthnie>(this.resourceUrl, ethnie, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEthnie>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEthnie[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
