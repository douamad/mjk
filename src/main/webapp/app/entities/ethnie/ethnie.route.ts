import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEthnie, Ethnie } from 'app/shared/model/ethnie.model';
import { EthnieService } from './ethnie.service';
import { EthnieComponent } from './ethnie.component';
import { EthnieDetailComponent } from './ethnie-detail.component';
import { EthnieUpdateComponent } from './ethnie-update.component';

@Injectable({ providedIn: 'root' })
export class EthnieResolve implements Resolve<IEthnie> {
  constructor(private service: EthnieService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEthnie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ethnie: HttpResponse<Ethnie>) => {
          if (ethnie.body) {
            return of(ethnie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Ethnie());
  }
}

export const ethnieRoute: Routes = [
  {
    path: '',
    component: EthnieComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.ethnie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EthnieDetailComponent,
    resolve: {
      ethnie: EthnieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.ethnie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EthnieUpdateComponent,
    resolve: {
      ethnie: EthnieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.ethnie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EthnieUpdateComponent,
    resolve: {
      ethnie: EthnieResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.ethnie.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
