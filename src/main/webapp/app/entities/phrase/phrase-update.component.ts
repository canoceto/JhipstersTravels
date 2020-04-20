import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPhrase, Phrase } from 'app/shared/model/phrase.model';
import { PhraseService } from './phrase.service';

@Component({
  selector: 'jhi-phrase-update',
  templateUrl: './phrase-update.component.html'
})
export class PhraseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    author: [],
    text: [],
    referense: []
  });

  constructor(protected phraseService: PhraseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ phrase }) => {
      this.updateForm(phrase);
    });
  }

  updateForm(phrase: IPhrase): void {
    this.editForm.patchValue({
      id: phrase.id,
      author: phrase.author,
      text: phrase.text,
      referense: phrase.referense
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const phrase = this.createFromForm();
    if (phrase.id !== undefined) {
      this.subscribeToSaveResponse(this.phraseService.update(phrase));
    } else {
      this.subscribeToSaveResponse(this.phraseService.create(phrase));
    }
  }

  private createFromForm(): IPhrase {
    return {
      ...new Phrase(),
      id: this.editForm.get(['id'])!.value,
      author: this.editForm.get(['author'])!.value,
      text: this.editForm.get(['text'])!.value,
      referense: this.editForm.get(['referense'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPhrase>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
