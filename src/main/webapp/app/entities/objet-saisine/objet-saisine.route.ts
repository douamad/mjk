import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IObjetSaisine, ObjetSaisine } from 'app/shared/model/objet-saisine.model';
import { ObjetSaisineService } from './objet-saisine.service';
import { ObjetSaisineComponent } from './objet-saisine.component';
import { ObjetSaisineDetailComponent } from './objet-saisine-detail.component';
import { ObjetSaisineUpdateComponent } from './objet-saisine-update.component';

@Injectable({ providedIn: 'root' })
export class ObjetSaisineResolve implements Resolve<IObjetSaisine> {
  constructor(private service: ObjetSaisineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IObjetSaisine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((objetSaisine: HttpResponse<ObjetSaisine>) => {
          if (objetSaisine.body) {
            return of(objetSaisine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ObjetSaisine());
  }
}

export const objetSaisineRoute: Routes = [
  {
    path: '',
    component: ObjetSaisineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.objetSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ObjetSaisineDetailComponent,
    resolve: {
      objetSaisine: ObjetSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.objetSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ObjetSaisineUpdateComponent,
    resolve: {
      objetSaisine: ObjetSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.objetSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ObjetSaisineUpdateComponent,
    resolve: {
      objetSaisine: ObjetSaisineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.objetSaisine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
