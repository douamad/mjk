import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequerant, Requerant } from 'app/shared/model/requerant.model';
import { RequerantService } from './requerant.service';
import { RequerantComponent } from './requerant.component';
import { RequerantDetailComponent } from './requerant-detail.component';
import { RequerantUpdateComponent } from './requerant-update.component';

@Injectable({ providedIn: 'root' })
export class RequerantResolve implements Resolve<IRequerant> {
  constructor(private service: RequerantService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequerant> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requerant: HttpResponse<Requerant>) => {
          if (requerant.body) {
            return of(requerant.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Requerant());
  }
}

export const requerantRoute: Routes = [
  {
    path: '',
    component: RequerantComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.requerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequerantDetailComponent,
    resolve: {
      requerant: RequerantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.requerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequerantUpdateComponent,
    resolve: {
      requerant: RequerantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.requerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequerantUpdateComponent,
    resolve: {
      requerant: RequerantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.requerant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
