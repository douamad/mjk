import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAssistance, Assistance } from 'app/shared/model/assistance.model';
import { AssistanceService } from './assistance.service';
import { AssistanceComponent } from './assistance.component';
import { AssistanceDetailComponent } from './assistance-detail.component';
import { AssistanceUpdateComponent } from './assistance-update.component';

@Injectable({ providedIn: 'root' })
export class AssistanceResolve implements Resolve<IAssistance> {
  constructor(private service: AssistanceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAssistance> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((assistance: HttpResponse<Assistance>) => {
          if (assistance.body) {
            return of(assistance.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Assistance());
  }
}

export const assistanceRoute: Routes = [
  {
    path: '',
    component: AssistanceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.assistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AssistanceDetailComponent,
    resolve: {
      assistance: AssistanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.assistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AssistanceUpdateComponent,
    resolve: {
      assistance: AssistanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.assistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AssistanceUpdateComponent,
    resolve: {
      assistance: AssistanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.assistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
