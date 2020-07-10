import { ICreance } from 'app/shared/model/creance.model';
import { ISaisine } from 'app/shared/model/saisine.model';

export interface IConclusion {
  id?: number;
  nom?: string;
  slug?: string;
  description?: string;
  creances?: ICreance[];
  saisines?: ISaisine[];
}

export class Conclusion implements IConclusion {
  constructor(
    public id?: number,
    public nom?: string,
    public slug?: string,
    public description?: string,
    public creances?: ICreance[],
    public saisines?: ISaisine[]
  ) {}
}
