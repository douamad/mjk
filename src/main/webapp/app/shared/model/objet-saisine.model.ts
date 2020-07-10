import { ISaisine } from 'app/shared/model/saisine.model';

export interface IObjetSaisine {
  id?: number;
  nom?: string;
  slug?: string;
  description?: string;
  saisines?: ISaisine[];
}

export class ObjetSaisine implements IObjetSaisine {
  constructor(public id?: number, public nom?: string, public slug?: string, public description?: string, public saisines?: ISaisine[]) {}
}
