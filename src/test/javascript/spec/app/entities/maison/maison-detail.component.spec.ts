import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { MaisonDetailComponent } from 'app/entities/maison/maison-detail.component';
import { Maison } from 'app/shared/model/maison.model';

describe('Component Tests', () => {
  describe('Maison Management Detail Component', () => {
    let comp: MaisonDetailComponent;
    let fixture: ComponentFixture<MaisonDetailComponent>;
    const route = ({ data: of({ maison: new Maison(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [MaisonDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MaisonDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MaisonDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load maison on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.maison).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
