import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPhrase } from 'app/shared/model/phrase.model';
import { PhraseService } from './phrase.service';

@Component({
  templateUrl: './phrase-delete-dialog.component.html'
})
export class PhraseDeleteDialogComponent {
  phrase?: IPhrase;

  constructor(protected phraseService: PhraseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.phraseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('phraseListModification');
      this.activeModal.close();
    });
  }
}
