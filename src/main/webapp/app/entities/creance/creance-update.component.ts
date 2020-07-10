import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICreance, Creance } from 'app/shared/model/creance.model';
import { CreanceService } from './creance.service';
import { IOrigineSaisine } from 'app/shared/model/origine-saisine.model';
import { OrigineSaisineService } from 'app/entities/origine-saisine/origine-saisine.service';
import { IConclusion } from 'app/shared/model/conclusion.model';
import { ConclusionService } from 'app/entities/conclusion/conclusion.service';
import { IMaison } from 'app/shared/model/maison.model';
import { MaisonService } from 'app/entities/maison/maison.service';
import { IRequerant } from 'app/shared/model/requerant.model';
import { RequerantService } from 'app/entities/requerant/requerant.service';

type SelectableEntity = IOrigineSaisine | IConclusion | IMaison | IRequerant;

@Component({
  selector: 'jhi-creance-update',
  templateUrl: './creance-update.component.html',
})
export class CreanceUpdateComponent implements OnInit {
  isSaving = false;
  originesaisines: IOrigineSaisine[] = [];
  conclusions: IConclusion[] = [];
  maisons: IMaison[] = [];
  requerants: IRequerant[] = [];
  dateDp: any;
  datePVRecDp: any;

  editForm = this.fb.group({
    id: [],
    ref: [],
    date: [],
    datePVRec: [],
    natureLitige: [],
    montant: [],
    nombreEcheance: [],
    totalRecouvre: [],
    soldeARecouvrer: [],
    origineId: [],
    conclusionsId: [],
    maisonId: [],
    demandeurId: [],
    defendeurId: [],
  });

  constructor(
    protected creanceService: CreanceService,
    protected origineSaisineService: OrigineSaisineService,
    protected conclusionService: ConclusionService,
    protected maisonService: MaisonService,
    protected requerantService: RequerantService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ creance }) => {
      this.updateForm(creance);

      this.origineSaisineService.query().subscribe((res: HttpResponse<IOrigineSaisine[]>) => (this.originesaisines = res.body || []));

      this.conclusionService.query().subscribe((res: HttpResponse<IConclusion[]>) => (this.conclusions = res.body || []));

      this.maisonService.query().subscribe((res: HttpResponse<IMaison[]>) => (this.maisons = res.body || []));

      this.requerantService.query().subscribe((res: HttpResponse<IRequerant[]>) => (this.requerants = res.body || []));
    });
  }

  updateForm(creance: ICreance): void {
    this.editForm.patchValue({
      id: creance.id,
      ref: creance.ref,
      date: creance.date,
      datePVRec: creance.datePVRec,
      natureLitige: creance.natureLitige,
      montant: creance.montant,
      nombreEcheance: creance.nombreEcheance,
      totalRecouvre: creance.totalRecouvre,
      soldeARecouvrer: creance.soldeARecouvrer,
      origineId: creance.origineId,
      conclusionsId: creance.conclusionsId,
      maisonId: creance.maisonId,
      demandeurId: creance.demandeurId,
      defendeurId: creance.defendeurId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const creance = this.createFromForm();
    if (creance.id !== undefined) {
      this.subscribeToSaveResponse(this.creanceService.update(creance));
    } else {
      this.subscribeToSaveResponse(this.creanceService.create(creance));
    }
  }

  private createFromForm(): ICreance {
    return {
      ...new Creance(),
      id: this.editForm.get(['id'])!.value,
      ref: this.editForm.get(['ref'])!.value,
      date: this.editForm.get(['date'])!.value,
      datePVRec: this.editForm.get(['datePVRec'])!.value,
      natureLitige: this.editForm.get(['natureLitige'])!.value,
      montant: this.editForm.get(['montant'])!.value,
      nombreEcheance: this.editForm.get(['nombreEcheance'])!.value,
      totalRecouvre: this.editForm.get(['totalRecouvre'])!.value,
      soldeARecouvrer: this.editForm.get(['soldeARecouvrer'])!.value,
      origineId: this.editForm.get(['origineId'])!.value,
      conclusionsId: this.editForm.get(['conclusionsId'])!.value,
      maisonId: this.editForm.get(['maisonId'])!.value,
      demandeurId: this.editForm.get(['demandeurId'])!.value,
      defendeurId: this.editForm.get(['defendeurId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICreance>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
