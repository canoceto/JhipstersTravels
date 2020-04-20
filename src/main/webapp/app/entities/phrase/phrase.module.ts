import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpringBootDemoSharedModule } from 'app/shared/shared.module';
import { PhraseComponent } from './phrase.component';
import { PhraseDetailComponent } from './phrase-detail.component';
import { PhraseUpdateComponent } from './phrase-update.component';
import { PhraseDeleteDialogComponent } from './phrase-delete-dialog.component';
import { phraseRoute } from './phrase.route';

@NgModule({
  imports: [SpringBootDemoSharedModule, RouterModule.forChild(phraseRoute)],
  declarations: [PhraseComponent, PhraseDetailComponent, PhraseUpdateComponent, PhraseDeleteDialogComponent],
  entryComponents: [PhraseDeleteDialogComponent]
})
export class SpringBootDemoPhraseModule {}
