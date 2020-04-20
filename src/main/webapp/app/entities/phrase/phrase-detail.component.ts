import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPhrase } from 'app/shared/model/phrase.model';

@Component({
  selector: 'jhi-phrase-detail',
  templateUrl: './phrase-detail.component.html'
})
export class PhraseDetailComponent implements OnInit {
  phrase: IPhrase | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ phrase }) => {
      this.phrase = phrase;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
