export interface IAssistance {
  id?: number;
  reference?: string;
  date?: string;
  cout?: string;
  objetAssistanceId?: number;
  maisonId?: number;
  demandeurId?: number;
  defendeurId?: number;
}

export class Assistance implements IAssistance {
  constructor(
    public id?: number,
    public reference?: string,
    public date?: string,
    public cout?: string,
    public objetAssistanceId?: number,
    public maisonId?: number,
    public demandeurId?: number,
    public defendeurId?: number
  ) {}
}
