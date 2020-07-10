import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CreanceService } from 'app/entities/creance/creance.service';
import { ICreance, Creance } from 'app/shared/model/creance.model';

describe('Service Tests', () => {
  describe('Creance Service', () => {
    let injector: TestBed;
    let service: CreanceService;
    let httpMock: HttpTestingController;
    let elemDefault: ICreance;
    let expectedResult: ICreance | ICreance[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CreanceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Creance(0, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_FORMAT),
            datePVRec: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Creance', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            date: currentDate.format(DATE_FORMAT),
            datePVRec: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            datePVRec: currentDate,
          },
          returnedFromService
        );

        service.create(new Creance()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Creance', () => {
        const returnedFromService = Object.assign(
          {
            ref: 'BBBBBB',
            date: currentDate.format(DATE_FORMAT),
            datePVRec: currentDate.format(DATE_FORMAT),
            natureLitige: 'BBBBBB',
            montant: 1,
            nombreEcheance: 1,
            totalRecouvre: 1,
            soldeARecouvrer: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            datePVRec: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Creance', () => {
        const returnedFromService = Object.assign(
          {
            ref: 'BBBBBB',
            date: currentDate.format(DATE_FORMAT),
            datePVRec: currentDate.format(DATE_FORMAT),
            natureLitige: 'BBBBBB',
            montant: 1,
            nombreEcheance: 1,
            totalRecouvre: 1,
            soldeARecouvrer: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
            datePVRec: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Creance', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
