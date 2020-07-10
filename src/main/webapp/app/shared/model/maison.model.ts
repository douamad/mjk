import { IAssistance } from 'app/shared/model/assistance.model';
import { ICreance } from 'app/shared/model/creance.model';
import { ISaisine } from 'app/shared/model/saisine.model';

export interface IMaison {
  id?: number;
  nom?: string;
  slug?: string;
  description?: string;
  assistances?: IAssistance[];
  creances?: ICreance[];
  saisines?: ISaisine[];
}

export class Maison implements IMaison {
  constructor(
    public id?: number,
    public nom?: string,
    public slug?: string,
    public description?: string,
    public assistances?: IAssistance[],
    public creances?: ICreance[],
    public saisines?: ISaisine[]
  ) {}
}
