import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { MaisonUpdateComponent } from 'app/entities/maison/maison-update.component';
import { MaisonService } from 'app/entities/maison/maison.service';
import { Maison } from 'app/shared/model/maison.model';

describe('Component Tests', () => {
  describe('Maison Management Update Component', () => {
    let comp: MaisonUpdateComponent;
    let fixture: ComponentFixture<MaisonUpdateComponent>;
    let service: MaisonService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [MaisonUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MaisonUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MaisonUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaisonService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Maison(123);
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
        const entity = new Maison();
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
