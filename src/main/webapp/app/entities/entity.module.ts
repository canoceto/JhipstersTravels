import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'phrase',
        loadChildren: () => import('./phrase/phrase.module').then(m => m.SpringBootDemoPhraseModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class SpringBootDemoEntityModule {}
