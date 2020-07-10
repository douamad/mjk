import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { OrigineSaisineDetailComponent } from 'app/entities/origine-saisine/origine-saisine-detail.component';
import { OrigineSaisine } from 'app/shared/model/origine-saisine.model';

describe('Component Tests', () => {
  describe('OrigineSaisine Management Detail Component', () => {
    let comp: OrigineSaisineDetailComponent;
    let fixture: ComponentFixture<OrigineSaisineDetailComponent>;
    const route = ({ data: of({ origineSaisine: new OrigineSaisine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [OrigineSaisineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(OrigineSaisineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OrigineSaisineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load origineSaisine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.origineSaisine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
