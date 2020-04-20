import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SpringBootDemoTestModule } from '../../../test.module';
import { PhraseUpdateComponent } from 'app/entities/phrase/phrase-update.component';
import { PhraseService } from 'app/entities/phrase/phrase.service';
import { Phrase } from 'app/shared/model/phrase.model';

describe('Component Tests', () => {
  describe('Phrase Management Update Component', () => {
    let comp: PhraseUpdateComponent;
    let fixture: ComponentFixture<PhraseUpdateComponent>;
    let service: PhraseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SpringBootDemoTestModule],
        declarations: [PhraseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PhraseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PhraseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PhraseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Phrase(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Phrase();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
