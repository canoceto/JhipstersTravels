import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPhrase, Phrase } from 'app/shared/model/phrase.model';
import { PhraseService } from './phrase.service';
import { PhraseComponent } from './phrase.component';
import { PhraseDetailComponent } from './phrase-detail.component';
import { PhraseUpdateComponent } from './phrase-update.component';

@Injectable({ providedIn: 'root' })
export class PhraseResolve implements Resolve<IPhrase> {
  constructor(private service: PhraseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPhrase> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((phrase: HttpResponse<Phrase>) => {
          if (phrase.body) {
            return of(phrase.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Phrase());
  }
}

export const phraseRoute: Routes = [
  {
    path: '',
    component: PhraseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'springBootDemoApp.phrase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PhraseDetailComponent,
    resolve: {
      phrase: PhraseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'springBootDemoApp.phrase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PhraseUpdateComponent,
    resolve: {
      phrase: PhraseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'springBootDemoApp.phrase.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PhraseUpdateComponent,
    resolve: {
      phrase: PhraseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'springBootDemoApp.phrase.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
