import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { SaisineUpdateComponent } from 'app/entities/saisine/saisine-update.component';
import { SaisineService } from 'app/entities/saisine/saisine.service';
import { Saisine } from 'app/shared/model/saisine.model';

describe('Component Tests', () => {
  describe('Saisine Management Update Component', () => {
    let comp: SaisineUpdateComponent;
    let fixture: ComponentFixture<SaisineUpdateComponent>;
    let service: SaisineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [SaisineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SaisineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SaisineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SaisineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Saisine(123);
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
        const entity = new Saisine();
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
