import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrigineSaisine, OrigineSaisine } from 'app/shared/model/origine-saisine.model';
import { OrigineSaisineService } from './origine-saisine.service';
import { OrigineSaisineComponent } from './origine-saisine.component';
import { OrigineSaisineDetailComponent } from './origine-saisine-detail.component';
import { OrigineSaisineUpdateComponent } from './origine-saisine-update.component';

@Injectable({ providedIn: 'root' })
export class OrigineSaisineResolve implements Resolve<IOrigineSaisine> {
  constructor(private service: OrigineSaisineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrigineSaisine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((origineSaisine: HttpResponse<OrigineSaisine>) => {
          if (origineSaisine.body) {
            return of(origineSaisine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new OrigineSaisine());
  }
}

export const origineSaisineRoute: Routes = [
  {
    path: '',
    component: OrigineSaisineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.origineSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OrigineSaisineDetailComponent,
    resolve: {
      origineSaisine: OrigineSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.origineSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OrigineSaisineUpdateComponent,
    resolve: {
      origineSaisine: OrigineSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.origineSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OrigineSaisineUpdateComponent,
    resolve: {
      origineSaisine: OrigineSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.origineSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
