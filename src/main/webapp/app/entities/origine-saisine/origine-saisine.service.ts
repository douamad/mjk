import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrigineSaisine } from 'app/shared/model/origine-saisine.model';

type EntityResponseType = HttpResponse<IOrigineSaisine>;
type EntityArrayResponseType = HttpResponse<IOrigineSaisine[]>;

@Injectable({ providedIn: 'root' })
export class OrigineSaisineService {
  public resourceUrl = SERVER_API_URL + 'api/origine-saisines';

  constructor(protected http: HttpClient) {}

  create(origineSaisine: IOrigineSaisine): Observable<EntityResponseType> {
    return this.http.post<IOrigineSaisine>(this.resourceUrl, origineSaisine, { observe: 'response' });
  }

  update(origineSaisine: IOrigineSaisine): Observable<EntityResponseType> {
    return this.http.put<IOrigineSaisine>(this.resourceUrl, origineSaisine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrigineSaisine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrigineSaisine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
