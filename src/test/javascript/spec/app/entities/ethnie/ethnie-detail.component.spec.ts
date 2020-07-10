import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MjkTestModule } from '../../../test.module';
import { EthnieDetailComponent } from 'app/entities/ethnie/ethnie-detail.component';
import { Ethnie } from 'app/shared/model/ethnie.model';

describe('Component Tests', () => {
  describe('Ethnie Management Detail Component', () => {
    let comp: EthnieDetailComponent;
    let fixture: ComponentFixture<EthnieDetailComponent>;
    const route = ({ data: of({ ethnie: new Ethnie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MjkTestModule],
        declarations: [EthnieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EthnieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EthnieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ethnie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ethnie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
