import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RequerantService } from 'app/entities/requerant/requerant.service';
import { IRequerant, Requerant } from 'app/shared/model/requerant.model';
import { Genre } from 'app/shared/model/enumerations/genre.model';

describe('Service Tests', () => {
  describe('Requerant Service', () => {
    let injector: TestBed;
    let service: RequerantService;
    let httpMock: HttpTestingController;
    let elemDefault: IRequerant;
    let expectedResult: IRequerant | IRequerant[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RequerantService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Requerant(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', Genre.H, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Requerant', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Requerant()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Requerant', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            telephone: 'BBBBBB',
            mail: 'BBBBBB',
            localite: 'BBBBBB',
            genre: 'BBBBBB',
            age: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Requerant', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            telephone: 'BBBBBB',
            mail: 'BBBBBB',
            localite: 'BBBBBB',
            genre: 'BBBBBB',
            age: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Requerant', () => {
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
