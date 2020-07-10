import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICreance, Creance } from 'app/shared/model/creance.model';
import { CreanceService } from './creance.service';
import { CreanceComponent } from './creance.component';
import { CreanceDetailComponent } from './creance-detail.component';
import { CreanceUpdateComponent } from './creance-update.component';

@Injectable({ providedIn: 'root' })
export class CreanceResolve implements Resolve<ICreance> {
  constructor(private service: CreanceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICreance> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((creance: HttpResponse<Creance>) => {
          if (creance.body) {
            return of(creance.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Creance());
  }
}

export const creanceRoute: Routes = [
  {
    path: '',
    component: CreanceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.creance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CreanceDetailComponent,
    resolve: {
      creance: CreanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.creance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CreanceUpdateComponent,
    resolve: {
      creance: CreanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.creance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CreanceUpdateComponent,
    resolve: {
      creance: CreanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.creance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
