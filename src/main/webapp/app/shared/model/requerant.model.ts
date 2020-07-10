import { IAssistance } from 'app/shared/model/assistance.model';
import { ICreance } from 'app/shared/model/creance.model';
import { ISaisine } from 'app/shared/model/saisine.model';
import { Genre } from 'app/shared/model/enumerations/genre.model';

export interface IRequerant {
  id?: number;
  prenom?: string;
  nom?: string;
  telephone?: string;
  mail?: string;
  localite?: string;
  genre?: Genre;
  age?: number;
  demandeAssistances?: IAssistance[];
  defenseAssistances?: IAssistance[];
  demandeCreances?: ICreance[];
  defenseCreances?: ICreance[];
  demandeSaisines?: ISaisine[];
  defenseSaisines?: ISaisine[];
  professionId?: number;
  ethnieId?: number;
}

export class Requerant implements IRequerant {
  constructor(
    public id?: number,
    public prenom?: string,
    public nom?: string,
    public telephone?: string,
    public mail?: string,
    public localite?: string,
    public genre?: Genre,
    public age?: number,
    public demandeAssistances?: IAssistance[],
    public defenseAssistances?: IAssistance[],
    public demandeCreances?: ICreance[],
    public defenseCreances?: ICreance[],
    public demandeSaisines?: ISaisine[],
    public defenseSaisines?: ISaisine[],
    public professionId?: number,
    public ethnieId?: number
  ) {}
}
