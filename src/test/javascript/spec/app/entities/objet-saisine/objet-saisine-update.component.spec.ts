import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { ObjetSaisineUpdateComponent } from 'app/entities/objet-saisine/objet-saisine-update.component';
import { ObjetSaisineService } from 'app/entities/objet-saisine/objet-saisine.service';
import { ObjetSaisine } from 'app/shared/model/objet-saisine.model';

describe('Component Tests', () => {
  describe('ObjetSaisine Management Update Component', () => {
    let comp: ObjetSaisineUpdateComponent;
    let fixture: ComponentFixture<ObjetSaisineUpdateComponent>;
    let service: ObjetSaisineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [ObjetSaisineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ObjetSaisineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ObjetSaisineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ObjetSaisineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ObjetSaisine(123);
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
        const entity = new ObjetSaisine();
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
