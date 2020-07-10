import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISaisine } from 'app/shared/model/saisine.model';

type EntityResponseType = HttpResponse<ISaisine>;
type EntityArrayResponseType = HttpResponse<ISaisine[]>;

@Injectable({ providedIn: 'root' })
export class SaisineService {
  public resourceUrl = SERVER_API_URL + 'api/saisines';

  constructor(protected http: HttpClient) {}

  create(saisine: ISaisine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(saisine);
    return this.http
      .post<ISaisine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(saisine: ISaisine): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(saisine);
    return this.http
      .put<ISaisine>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISaisine>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISaisine[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(saisine: ISaisine): ISaisine {
    const copy: ISaisine = Object.assign({}, saisine, {
      date: saisine.date && saisine.date.isValid() ? saisine.date.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((saisine: ISaisine) => {
        saisine.date = saisine.date ? moment(saisine.date) : undefined;
      });
    }
    return res;
  }
}
