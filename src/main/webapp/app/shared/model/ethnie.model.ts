import { IRequerant } from 'app/shared/model/requerant.model';

export interface IEthnie {
  id?: number;
  nom?: string;
  slug?: string;
  description?: string;
  ethnis?: IRequerant[];
}

export class Ethnie implements IEthnie {
  constructor(public id?: number, public nom?: string, public slug?: string, public description?: string, public ethnis?: IRequerant[]) {}
}
