import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { NatureSaisineDetailComponent } from 'app/entities/nature-saisine/nature-saisine-detail.component';
import { NatureSaisine } from 'app/shared/model/nature-saisine.model';

describe('Component Tests', () => {
  describe('NatureSaisine Management Detail Component', () => {
    let comp: NatureSaisineDetailComponent;
    let fixture: ComponentFixture<NatureSaisineDetailComponent>;
    const route = ({ data: of({ natureSaisine: new NatureSaisine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [NatureSaisineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NatureSaisineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NatureSaisineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load natureSaisine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.natureSaisine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
