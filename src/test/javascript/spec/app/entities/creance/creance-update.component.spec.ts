import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { CreanceUpdateComponent } from 'app/entities/creance/creance-update.component';
import { CreanceService } from 'app/entities/creance/creance.service';
import { Creance } from 'app/shared/model/creance.model';

describe('Component Tests', () => {
  describe('Creance Management Update Component', () => {
    let comp: CreanceUpdateComponent;
    let fixture: ComponentFixture<CreanceUpdateComponent>;
    let service: CreanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [CreanceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CreanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CreanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CreanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Creance(123);
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
        const entity = new Creance();
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
