import { ISaisine } from 'app/shared/model/saisine.model';

export interface INatureSaisine {
  id?: number;
  nom?: string;
  slug?: string;
  description?: string;
  saisines?: ISaisine[];
}

export class NatureSaisine implements INatureSaisine {
  constructor(public id?: number, public nom?: string, public slug?: string, public description?: string, public saisines?: ISaisine[]) {}
}
