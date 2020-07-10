import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { ObjetAssistanceUpdateComponent } from 'app/entities/objet-assistance/objet-assistance-update.component';
import { ObjetAssistanceService } from 'app/entities/objet-assistance/objet-assistance.service';
import { ObjetAssistance } from 'app/shared/model/objet-assistance.model';

describe('Component Tests', () => {
  describe('ObjetAssistance Management Update Component', () => {
    let comp: ObjetAssistanceUpdateComponent;
    let fixture: ComponentFixture<ObjetAssistanceUpdateComponent>;
    let service: ObjetAssistanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [ObjetAssistanceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ObjetAssistanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ObjetAssistanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ObjetAssistanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ObjetAssistance(123);
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
        const entity = new ObjetAssistance();
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
