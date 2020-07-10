import { Moment } from 'moment';

export interface ICreance {
  id?: number;
  ref?: string;
  date?: Moment;
  datePVRec?: Moment;
  natureLitige?: string;
  montant?: number;
  nombreEcheance?: number;
  totalRecouvre?: number;
  soldeARecouvrer?: number;
  origineId?: number;
  conclusionsId?: number;
  maisonId?: number;
  demandeurId?: number;
  defendeurId?: number;
}

export class Creance implements ICreance {
  constructor(
    public id?: number,
    public ref?: string,
    public date?: Moment,
    public datePVRec?: Moment,
    public natureLitige?: string,
    public montant?: number,
    public nombreEcheance?: number,
    public totalRecouvre?: number,
    public soldeARecouvrer?: number,
    public origineId?: number,
    public conclusionsId?: number,
    public maisonId?: number,
    public demandeurId?: number,
    public defendeurId?: number
  ) {}
}
