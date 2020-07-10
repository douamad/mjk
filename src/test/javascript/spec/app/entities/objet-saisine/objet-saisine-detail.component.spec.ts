import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { ObjetSaisineDetailComponent } from 'app/entities/objet-saisine/objet-saisine-detail.component';
import { ObjetSaisine } from 'app/shared/model/objet-saisine.model';

describe('Component Tests', () => {
  describe('ObjetSaisine Management Detail Component', () => {
    let comp: ObjetSaisineDetailComponent;
    let fixture: ComponentFixture<ObjetSaisineDetailComponent>;
    const route = ({ data: of({ objetSaisine: new ObjetSaisine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [ObjetSaisineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ObjetSaisineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ObjetSaisineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load objetSaisine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.objetSaisine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
