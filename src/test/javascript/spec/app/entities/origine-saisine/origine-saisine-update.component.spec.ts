import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { OrigineSaisineUpdateComponent } from 'app/entities/origine-saisine/origine-saisine-update.component';
import { OrigineSaisineService } from 'app/entities/origine-saisine/origine-saisine.service';
import { OrigineSaisine } from 'app/shared/model/origine-saisine.model';

describe('Component Tests', () => {
  describe('OrigineSaisine Management Update Component', () => {
    let comp: OrigineSaisineUpdateComponent;
    let fixture: ComponentFixture<OrigineSaisineUpdateComponent>;
    let service: OrigineSaisineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [OrigineSaisineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(OrigineSaisineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrigineSaisineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrigineSaisineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrigineSaisine(123);
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
        const entity = new OrigineSaisine();
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
