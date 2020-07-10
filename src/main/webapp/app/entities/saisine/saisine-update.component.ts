import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISaisine, Saisine } from 'app/shared/model/saisine.model';
import { SaisineService } from './saisine.service';
import { IObjetSaisine } from 'app/shared/model/objet-saisine.model';
import { ObjetSaisineService } from 'app/entities/objet-saisine/objet-saisine.service';
import { INatureSaisine } from 'app/shared/model/nature-saisine.model';
import { NatureSaisineService } from 'app/entities/nature-saisine/nature-saisine.service';
import { IOrigineSaisine } from 'app/shared/model/origine-saisine.model';
import { OrigineSaisineService } from 'app/entities/origine-saisine/origine-saisine.service';
import { IConclusion } from 'app/shared/model/conclusion.model';
import { ConclusionService } from 'app/entities/conclusion/conclusion.service';
import { IMaison } from 'app/shared/model/maison.model';
import { MaisonService } from 'app/entities/maison/maison.service';
import { IRequerant } from 'app/shared/model/requerant.model';
import { RequerantService } from 'app/entities/requerant/requerant.service';

type SelectableEntity = IObjetSaisine | INatureSaisine | IOrigineSaisine | IConclusion | IMaison | IRequerant;

@Component({
  selector: 'jhi-saisine-update',
  templateUrl: './saisine-update.component.html',
})
export class SaisineUpdateComponent implements OnInit {
  isSaving = false;
  objetsaisines: IObjetSaisine[] = [];
  naturesaisines: INatureSaisine[] = [];
  originesaisines: IOrigineSaisine[] = [];
  conclusions: IConclusion[] = [];
  maisons: IMaison[] = [];
  requerants: IRequerant[] = [];
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    ref: [],
    date: [],
    description: [],
    organisaiton: [],
    objetId: [],
    natureId: [],
    origineId: [],
    conclusionId: [],
    maisonId: [],
    demandeurId: [],
    defendeurId: [],
  });

  constructor(
    protected saisineService: SaisineService,
    protected objetSaisineService: ObjetSaisineService,
    protected natureSaisineService: NatureSaisineService,
    protected origineSaisineService: OrigineSaisineService,
    protected conclusionService: ConclusionService,
    protected maisonService: MaisonService,
    protected requerantService: RequerantService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ saisine }) => {
      this.updateForm(saisine);

      this.objetSaisineService.query().subscribe((res: HttpResponse<IObjetSaisine[]>) => (this.objetsaisines = res.body || []));

      this.natureSaisineService.query().subscribe((res: HttpResponse<INatureSaisine[]>) => (this.naturesaisines = res.body || []));

      this.origineSaisineService.query().subscribe((res: HttpResponse<IOrigineSaisine[]>) => (this.originesaisines = res.body || []));

      this.conclusionService.query().subscribe((res: HttpResponse<IConclusion[]>) => (this.conclusions = res.body || []));

      this.maisonService.query().subscribe((res: HttpResponse<IMaison[]>) => (this.maisons = res.body || []));

      this.requerantService.query().subscribe((res: HttpResponse<IRequerant[]>) => (this.requerants = res.body || []));
    });
  }

  updateForm(saisine: ISaisine): void {
    this.editForm.patchValue({
      id: saisine.id,
      ref: saisine.ref,
      date: saisine.date,
      description: saisine.description,
      organisaiton: saisine.organisaiton,
      objetId: saisine.objetId,
      natureId: saisine.natureId,
      origineId: saisine.origineId,
      conclusionId: saisine.conclusionId,
      maisonId: saisine.maisonId,
      demandeurId: saisine.demandeurId,
      defendeurId: saisine.defendeurId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const saisine = this.createFromForm();
    if (saisine.id !== undefined) {
      this.subscribeToSaveResponse(this.saisineService.update(saisine));
    } else {
      this.subscribeToSaveResponse(this.saisineService.create(saisine));
    }
  }

  private createFromForm(): ISaisine {
    return {
      ...new Saisine(),
      id: this.editForm.get(['id'])!.value,
      ref: this.editForm.get(['ref'])!.value,
      date: this.editForm.get(['date'])!.value,
      description: this.editForm.get(['description'])!.value,
      organisaiton: this.editForm.get(['organisaiton'])!.value,
      objetId: this.editForm.get(['objetId'])!.value,
      natureId: this.editForm.get(['natureId'])!.value,
      origineId: this.editForm.get(['origineId'])!.value,
      conclusionId: this.editForm.get(['conclusionId'])!.value,
      maisonId: this.editForm.get(['maisonId'])!.value,
      demandeurId: this.editForm.get(['demandeurId'])!.value,
      defendeurId: this.editForm.get(['defendeurId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISaisine>>): void {
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
