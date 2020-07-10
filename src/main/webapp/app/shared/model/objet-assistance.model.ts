import { IAssistance } from 'app/shared/model/assistance.model';

export interface IObjetAssistance {
  id?: number;
  nom?: string;
  slug?: string;
  description?: string;
  objets?: IAssistance[];
}

export class ObjetAssistance implements IObjetAssistance {
  constructor(public id?: number, public nom?: string, public slug?: string, public description?: string, public objets?: IAssistance[]) {}
}
