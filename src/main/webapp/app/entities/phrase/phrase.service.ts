import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPhrase } from 'app/shared/model/phrase.model';

type EntityResponseType = HttpResponse<IPhrase>;
type EntityArrayResponseType = HttpResponse<IPhrase[]>;

@Injectable({ providedIn: 'root' })
export class PhraseService {
  public resourceUrl = SERVER_API_URL + 'api/phrases';

  constructor(protected http: HttpClient) {}

  create(phrase: IPhrase): Observable<EntityResponseType> {
    return this.http.post<IPhrase>(this.resourceUrl, phrase, { observe: 'response' });
  }

  update(phrase: IPhrase): Observable<EntityResponseType> {
    return this.http.put<IPhrase>(this.resourceUrl, phrase, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPhrase>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPhrase[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
