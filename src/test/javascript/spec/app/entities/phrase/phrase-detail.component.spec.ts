import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SpringBootDemoTestModule } from '../../../test.module';
import { PhraseDetailComponent } from 'app/entities/phrase/phrase-detail.component';
import { Phrase } from 'app/shared/model/phrase.model';

describe('Component Tests', () => {
  describe('Phrase Management Detail Component', () => {
    let comp: PhraseDetailComponent;
    let fixture: ComponentFixture<PhraseDetailComponent>;
    const route = ({ data: of({ phrase: new Phrase(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpringBootDemoTestModule],
        declarations: [PhraseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PhraseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PhraseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load phrase on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.phrase).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
