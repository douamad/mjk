import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { AssistanceUpdateComponent } from 'app/entities/assistance/assistance-update.component';
import { AssistanceService } from 'app/entities/assistance/assistance.service';
import { Assistance } from 'app/shared/model/assistance.model';

describe('Component Tests', () => {
  describe('Assistance Management Update Component', () => {
    let comp: AssistanceUpdateComponent;
    let fixture: ComponentFixture<AssistanceUpdateComponent>;
    let service: AssistanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [AssistanceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AssistanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AssistanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AssistanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Assistance(123);
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
        const entity = new Assistance();
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
