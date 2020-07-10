import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { AssistanceDetailComponent } from 'app/entities/assistance/assistance-detail.component';
import { Assistance } from 'app/shared/model/assistance.model';

describe('Component Tests', () => {
  describe('Assistance Management Detail Component', () => {
    let comp: AssistanceDetailComponent;
    let fixture: ComponentFixture<AssistanceDetailComponent>;
    const route = ({ data: of({ assistance: new Assistance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [AssistanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AssistanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AssistanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load assistance on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.assistance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
