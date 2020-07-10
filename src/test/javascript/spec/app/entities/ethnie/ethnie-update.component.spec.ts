import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { EthnieUpdateComponent } from 'app/entities/ethnie/ethnie-update.component';
import { EthnieService } from 'app/entities/ethnie/ethnie.service';
import { Ethnie } from 'app/shared/model/ethnie.model';

describe('Component Tests', () => {
  describe('Ethnie Management Update Component', () => {
    let comp: EthnieUpdateComponent;
    let fixture: ComponentFixture<EthnieUpdateComponent>;
    let service: EthnieService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [EthnieUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EthnieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EthnieUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EthnieService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Ethnie(123);
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
        const entity = new Ethnie();
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
