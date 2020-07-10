import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INatureSaisine, NatureSaisine } from 'app/shared/model/nature-saisine.model';
import { NatureSaisineService } from './nature-saisine.service';
import { NatureSaisineComponent } from './nature-saisine.component';
import { NatureSaisineDetailComponent } from './nature-saisine-detail.component';
import { NatureSaisineUpdateComponent } from './nature-saisine-update.component';

@Injectable({ providedIn: 'root' })
export class NatureSaisineResolve implements Resolve<INatureSaisine> {
  constructor(private service: NatureSaisineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INatureSaisine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((natureSaisine: HttpResponse<NatureSaisine>) => {
          if (natureSaisine.body) {
            return of(natureSaisine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NatureSaisine());
  }
}

export const natureSaisineRoute: Routes = [
  {
    path: '',
    component: NatureSaisineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.natureSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NatureSaisineDetailComponent,
    resolve: {
      natureSaisine: NatureSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.natureSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NatureSaisineUpdateComponent,
    resolve: {
      natureSaisine: NatureSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.natureSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NatureSaisineUpdateComponent,
    resolve: {
      natureSaisine: NatureSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.natureSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
