import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { CreanceDetailComponent } from 'app/entities/creance/creance-detail.component';
import { Creance } from 'app/shared/model/creance.model';

describe('Component Tests', () => {
  describe('Creance Management Detail Component', () => {
    let comp: CreanceDetailComponent;
    let fixture: ComponentFixture<CreanceDetailComponent>;
    const route = ({ data: of({ creance: new Creance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [CreanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CreanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CreanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load creance on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.creance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
