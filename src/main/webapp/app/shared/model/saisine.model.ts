import { Moment } from 'moment';

export interface ISaisine {
  id?: number;
  ref?: string;
  date?: Moment;
  description?: string;
  organisaiton?: number;
  objetId?: number;
  natureId?: number;
  origineId?: number;
  conclusionId?: number;
  maisonId?: number;
  demandeurId?: number;
  defendeurId?: number;
}

export class Saisine implements ISaisine {
  constructor(
    public id?: number,
    public ref?: string,
    public date?: Moment,
    public description?: string,
    public organisaiton?: number,
    public objetId?: number,
    public natureId?: number,
    public origineId?: number,
    public conclusionId?: number,
    public maisonId?: number,
    public demandeurId?: number,
    public defendeurId?: number
  ) {}
}
