import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { ObjetAssistanceDetailComponent } from 'app/entities/objet-assistance/objet-assistance-detail.component';
import { ObjetAssistance } from 'app/shared/model/objet-assistance.model';

describe('Component Tests', () => {
  describe('ObjetAssistance Management Detail Component', () => {
    let comp: ObjetAssistanceDetailComponent;
    let fixture: ComponentFixture<ObjetAssistanceDetailComponent>;
    const route = ({ data: of({ objetAssistance: new ObjetAssistance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [ObjetAssistanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ObjetAssistanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ObjetAssistanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load objetAssistance on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.objetAssistance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
