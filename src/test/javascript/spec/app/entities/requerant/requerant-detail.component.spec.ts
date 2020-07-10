import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { RequerantDetailComponent } from 'app/entities/requerant/requerant-detail.component';
import { Requerant } from 'app/shared/model/requerant.model';

describe('Component Tests', () => {
  describe('Requerant Management Detail Component', () => {
    let comp: RequerantDetailComponent;
    let fixture: ComponentFixture<RequerantDetailComponent>;
    const route = ({ data: of({ requerant: new Requerant(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [RequerantDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RequerantDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequerantDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load requerant on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requerant).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
