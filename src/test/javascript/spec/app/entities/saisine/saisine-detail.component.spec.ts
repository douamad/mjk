import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { SaisineDetailComponent } from 'app/entities/saisine/saisine-detail.component';
import { Saisine } from 'app/shared/model/saisine.model';

describe('Component Tests', () => {
  describe('Saisine Management Detail Component', () => {
    let comp: SaisineDetailComponent;
    let fixture: ComponentFixture<SaisineDetailComponent>;
    const route = ({ data: of({ saisine: new Saisine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [SaisineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SaisineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SaisineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load saisine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.saisine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
