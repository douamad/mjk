import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { RequerantUpdateComponent } from 'app/entities/requerant/requerant-update.component';
import { RequerantService } from 'app/entities/requerant/requerant.service';
import { Requerant } from 'app/shared/model/requerant.model';

describe('Component Tests', () => {
  describe('Requerant Management Update Component', () => {
    let comp: RequerantUpdateComponent;
    let fixture: ComponentFixture<RequerantUpdateComponent>;
    let service: RequerantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [RequerantUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RequerantUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RequerantUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RequerantService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Requerant(123);
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
        const entity = new Requerant();
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
