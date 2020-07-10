import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IObjetAssistance, ObjetAssistance } from 'app/shared/model/objet-assistance.model';
import { ObjetAssistanceService } from './objet-assistance.service';
import { ObjetAssistanceComponent } from './objet-assistance.component';
import { ObjetAssistanceDetailComponent } from './objet-assistance-detail.component';
import { ObjetAssistanceUpdateComponent } from './objet-assistance-update.component';

@Injectable({ providedIn: 'root' })
export class ObjetAssistanceResolve implements Resolve<IObjetAssistance> {
  constructor(private service: ObjetAssistanceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IObjetAssistance> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((objetAssistance: HttpResponse<ObjetAssistance>) => {
          if (objetAssistance.body) {
            return of(objetAssistance.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ObjetAssistance());
  }
}

export const objetAssistanceRoute: Routes = [
  {
    path: '',
    component: ObjetAssistanceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'mjkApp.objetAssistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ObjetAssistanceDetailComponent,
    resolve: {
      objetAssistance: ObjetAssistanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.objetAssistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ObjetAssistanceUpdateComponent,
    resolve: {
      objetAssistance: ObjetAssistanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.objetAssistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ObjetAssistanceUpdateComponent,
    resolve: {
      objetAssistance: ObjetAssistanceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mjkApp.objetAssistance.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
