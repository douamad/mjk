import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICreance } from 'app/shared/model/creance.model';

type EntityResponseType = HttpResponse<ICreance>;
type EntityArrayResponseType = HttpResponse<ICreance[]>;

@Injectable({ providedIn: 'root' })
export class CreanceService {
  public resourceUrl = SERVER_API_URL + 'api/creances';

  constructor(protected http: HttpClient) {}

  create(creance: ICreance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(creance);
    return this.http
      .post<ICreance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(creance: ICreance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(creance);
    return this.http
      .put<ICreance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICreance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICreance[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(creance: ICreance): ICreance {
    const copy: ICreance = Object.assign({}, creance, {
      date: creance.date && creance.date.isValid() ? creance.date.format(DATE_FORMAT) : undefined,
      datePVRec: creance.datePVRec && creance.datePVRec.isValid() ? creance.datePVRec.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
      res.body.datePVRec = res.body.datePVRec ? moment(res.body.datePVRec) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((creance: ICreance) => {
        creance.date = creance.date ? moment(creance.date) : undefined;
        creance.datePVRec = creance.datePVRec ? moment(creance.datePVRec) : undefined;
      });
    }
    return res;
  }
}
