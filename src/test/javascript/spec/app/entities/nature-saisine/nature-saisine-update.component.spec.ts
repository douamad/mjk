import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { NatureSaisineUpdateComponent } from 'app/entities/nature-saisine/nature-saisine-update.component';
import { NatureSaisineService } from 'app/entities/nature-saisine/nature-saisine.service';
import { NatureSaisine } from 'app/shared/model/nature-saisine.model';

describe('Component Tests', () => {
  describe('NatureSaisine Management Update Component', () => {
    let comp: NatureSaisineUpdateComponent;
    let fixture: ComponentFixture<NatureSaisineUpdateComponent>;
    let service: NatureSaisineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [NatureSaisineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NatureSaisineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureSaisineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureSaisineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NatureSaisine(123);
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
        const entity = new NatureSaisine();
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
