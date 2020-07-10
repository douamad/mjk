import { IRequerant } from 'app/shared/model/requerant.model';

export interface IProfession {
  id?: number;
  nom?: string;
  slug?: string;
  description?: string;
  professions?: IRequerant[];
}

export class Profession implements IProfession {
  constructor(
    public id?: number,
    public nom?: string,
    public slug?: string,
    public description?: string,
    public professions?: IRequerant[]
  ) {}
}
