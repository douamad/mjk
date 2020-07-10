import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAssistance, Assistance } from 'app/shared/model/assistance.model';
import { AssistanceService } from './assistance.service';
import { IObjetAssistance } from 'app/shared/model/objet-assistance.model';
import { ObjetAssistanceService } from 'app/entities/objet-assistance/objet-assistance.service';
import { IMaison } from 'app/shared/model/maison.model';
import { MaisonService } from 'app/entities/maison/maison.service';
import { IRequerant } from 'app/shared/model/requerant.model';
import { RequerantService } from 'app/entities/requerant/requerant.service';

type SelectableEntity = IObjetAssistance | IMaison | IRequerant;

@Component({
  selector: 'jhi-assistance-update',
  templateUrl: './assistance-update.component.html',
})
export class AssistanceUpdateComponent implements OnInit {
  isSaving = false;
  objetassistances: IObjetAssistance[] = [];
  maisons: IMaison[] = [];
  requerants: IRequerant[] = [];

  editForm = this.fb.group({
    id: [],
    reference: [],
    date: [],
    cout: [],
    objetAssistanceId: [],
    maisonId: [],
    demandeurId: [],
    defendeurId: [],
  });

  constructor(
    protected assistanceService: AssistanceService,
    protected objetAssistanceService: ObjetAssistanceService,
    protected maisonService: MaisonService,
    protected requerantService: RequerantService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assistance }) => {
      this.updateForm(assistance);

      this.objetAssistanceService.query().subscribe((res: HttpResponse<IObjetAssistance[]>) => (this.objetassistances = res.body || []));

      this.maisonService.query().subscribe((res: HttpResponse<IMaison[]>) => (this.maisons = res.body || []));

      this.requerantService.query().subscribe((res: HttpResponse<IRequerant[]>) => (this.requerants = res.body || []));
    });
  }

  updateForm(assistance: IAssistance): void {
    this.editForm.patchValue({
      id: assistance.id,
      reference: assistance.reference,
      date: assistance.date,
      cout: assistance.cout,
      objetAssistanceId: assistance.objetAssistanceId,
      maisonId: assistance.maisonId,
      demandeurId: assistance.demandeurId,
      defendeurId: assistance.defendeurId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const assistance = this.createFromForm();
    if (assistance.id !== undefined) {
      this.subscribeToSaveResponse(this.assistanceService.update(assistance));
    } else {
      this.subscribeToSaveResponse(this.assistanceService.create(assistance));
    }
  }

  private createFromForm(): IAssistance {
    return {
      ...new Assistance(),
      id: this.editForm.get(['id'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      date: this.editForm.get(['date'])!.value,
      cout: this.editForm.get(['cout'])!.value,
      objetAssistanceId: this.editForm.get(['objetAssistanceId'])!.value,
      maisonId: this.editForm.get(['maisonId'])!.value,
      demandeurId: this.editForm.get(['demandeurId'])!.value,
      defendeurId: this.editForm.get(['defendeurId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssistance>>): void {
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
